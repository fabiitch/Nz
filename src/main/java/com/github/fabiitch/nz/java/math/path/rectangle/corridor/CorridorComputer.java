package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.DirectionUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CorridorComputer {

    public static Array<CorridorPart> compute(Vector2 start, Array<CorridorPathStep> steps) {
        if (steps.isEmpty())
            return new Array<>();

        Array<CorridorPart> result = new Array<>();
        start = start.cpy();
        for (int i = 0; i < steps.size; i++) {
            CorridorPathStep current = steps.get(i);
            CorridorPathStep next = ArrayUtils.getNextOf(steps, i);
            CorridorPart previousPart = ArrayUtils.getPreviousOf(result, i);


            CorridorPart part = CorridorComputer.compute(start, current, previousPart, next);
            start.set(part.getMiddleEnd());

            result.add(part);
        }
        return result;
    }

    public static CorridorPart compute(Vector2 middleStart, CorridorPathStep current, CorridorPart previous, CorridorPathStep next) {
        CorridorPart part = null;
        if (next != null && next.getDirection() != current.getDirection()) {
            Direction dirWallA = current.getOtherOrientation().getDirectionA();
            Direction dirWallB = current.getOtherOrientation().getDirectionB();

            part = init(middleStart, current);

            part.setRectWallA(computeRectangleForTurn(middleStart, dirWallA, current, next));
            part.setRectWallB(computeRectangleForTurn(middleStart, dirWallB, current, next));
        } else {
            part = basicCase(middleStart, current);
        }

        if (previous != null && previous.getOrientation() != current.getOrientation()) {
            Direction dirWallA = current.getOtherOrientation().getDirectionA();
            Direction dirWallB = current.getOtherOrientation().getDirectionB();
            addPreviousDecal(part, dirWallA, current, previous);
            addPreviousDecal(part, dirWallB, current, previous);
        }

        if (next != null && next.getDirection() == current.getDirection()) {
            Direction dirWallA = current.getOtherOrientation().getDirectionA();
            Direction dirWallB = current.getOtherOrientation().getDirectionB();
            addBlocker(dirWallA, next, part);
            addBlocker(dirWallB, next, part);
        }
        return part;
    }

    private static void addBlocker(Direction wallDir, CorridorPathStep next, CorridorPart currentPart) {
        float wallSize = currentPart.getWallSize(wallDir);
        float nextWallSize = next.getWallSize(wallDir);

        float endWallCurrent = currentPart.getDstFromCenter(wallDir);
        float endWallNext = next.getDstFromCenter(wallDir);

        if (next.getWalkSize() / 2 > endWallCurrent) { //the next wall dont block total the current
            //cause current walk size open too large
            float blockerLength = endWallNext - wallSize - currentPart.getWalkSize() / 2;
            Vector2 rectCenter = currentPart.getMiddleEnd().cpy();

            currentPart.getDirection().subTo(rectCenter, wallSize / 2);
            wallDir.addTo(rectCenter, endWallCurrent + blockerLength / 2);

            Rectangle blockerRect = RectangleBuilder.withOrientationCenter(wallDir.getOrientation(), rectCenter, blockerLength, wallSize);
            currentPart.setBlocker(wallDir, blockerRect);
        } else if (currentPart.getWalkSize() / 2 > endWallNext) {
            //reverse next walk size is too small
            float blockerLength = endWallCurrent - endWallNext;
            float blockerSize = Math.min(wallSize, next.getLength() - nextWallSize);
            Vector2 rectCenter = currentPart.getMiddleEnd().cpy();

            currentPart.getDirection().addTo(rectCenter, blockerSize / 2);
            wallDir.addTo(rectCenter, endWallNext + blockerLength / 2);
            Rectangle blockerRect = RectangleBuilder.withOrientationCenter(wallDir.getOrientation(), rectCenter, blockerLength, blockerSize);
            currentPart.setBlocker(wallDir, blockerRect);
        }


    }

    private static Rectangle computeRectangleForTurn(Vector2 middleStart, Direction wallPosition, CorridorPathStep current, CorridorPathStep next) {
        boolean isOpposite = wallPosition == next.getDirection().getReverse();
        float lengthWall;

        if (isOpposite) {
            lengthWall = current.getLength() + next.getWalkSize() / 2;
        } else {
            lengthWall = current.getLength() - next.getWalkSize() / 2;
        }

        Vector2 rectCenter = middleStart.cpy();
        current.getDirection().addTo(rectCenter, lengthWall / 2);

        wallPosition.addTo(rectCenter, current.getWallSize(wallPosition) / 2 + current.getWalkSize() / 2);

        return RectangleBuilder.withOrientationCenter(current.getOrientation(), rectCenter, lengthWall, current.getWallSize(wallPosition));
    }

    private static void addPreviousDecal(CorridorPart part, Direction wallPosition, CorridorPathStep current, CorridorPart previous) {
        boolean isOpposite = wallPosition == previous.getDirection();
        Rectangle rectangleWall = part.getWall(wallPosition);
        Vector2 center = rectangleWall.getCenter(new Vector2());

        Direction previousWallDir = DirectionUtils.getNextDirOnTurn(wallPosition, current.getDirection(), previous.getDirection());
        float rectLength = RectangleUtils.getSize(rectangleWall, current.getOrientation());
        if (isOpposite) {
            float lengthToAdd = previous.getWalkSize() / 2 + previous.getWallSize(previousWallDir);
            RectangleUtils.setSize(rectangleWall, current.getOrientation(), rectLength + lengthToAdd);

            current.getDirection().subTo(center, lengthToAdd / 2);
            RectangleUtils.setCenter(rectangleWall, center);
        } else {
            float lengthToSub = previous.getWalkSize() / 2 + previous.getWallSize(previousWallDir);
            RectangleUtils.setSize(rectangleWall, current.getOrientation(), rectLength - lengthToSub);
            current.getDirection().addTo(center, lengthToSub / 2);
            RectangleUtils.setCenter(rectangleWall, center);
        }
    }


    private static CorridorPart basicCase(Vector2 middleStart, CorridorPathStep current) {
        CorridorPart part = init(middleStart, current);
        Vector2 middleA = middleStart.cpy();
        {
            Direction directionA = current.getOtherOrientation().getDirectionA();
            current.getDirection().addTo(middleA, current.getLength() / 2);
            directionA.addTo(middleA, current.getWalkSize() / 2 + current.getWallSize(directionA) / 2);
            part.setRectWallA(RectangleBuilder.withOrientationCenter(part.getOrientation(), middleA, current.getLength(), current.getWallSizeA()));
        }
        {
            Direction directionB = current.getOtherOrientation().getDirectionB();
            Vector2 middleB = middleStart.cpy();
            current.getDirection().addTo(middleB, current.getLength() / 2);
            directionB.addTo(middleB, current.getWalkSize() / 2 + current.getWallSize(directionB) / 2);
            part.setRectWallB(RectangleBuilder.withOrientationCenter(part.getOrientation(), middleB, current.getLength(), current.getWallSizeB()));
        }
        return part;
    }

    private static CorridorPart init(Vector2 middleStart, CorridorPathStep current) {
        CorridorPart part = new CorridorPart();
        part.setDirection(current.getDirection());
        part.setMiddleStart(middleStart.cpy());
        part.setMiddleEnd(current.addTo(middleStart.cpy()));

        Vector2 centerWalk = current.getDirection().addTo(middleStart.cpy(), current.getLength() / 2);
        part.setRectWalk(RectangleBuilder.withOrientationCenter(current.getOrientation(), centerWalk, current.getLength(), current.getWalkSize()));
        return part;
    }
}
