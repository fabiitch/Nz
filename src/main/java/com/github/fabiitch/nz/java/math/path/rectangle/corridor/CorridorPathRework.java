package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.DirectionUtils;
import com.github.fabiitch.nz.java.utils.ArrayContainer;

public class CorridorPathRework extends ArrayContainer<CorridorPathStep> {

    public void add(Direction direction, int length, int walkSize, float wallSize) {
        array.add(new CorridorPathStep(direction, length, walkSize, wallSize));
    }

    private SubPath path1 = new SubPath(), path2 = new SubPath();
    private Vector2 middle = new Vector2();

    public Array<Rectangle> compute() {
        CorridorPathStep firstStep = array.get(0);
        Direction startDir = firstStep.getDirection();
        Direction posPathOne = startDir.getOrientation().getOtherOrientation().getDirectionA();
        Direction postPathTwo = startDir.getOrientation().getOtherOrientation().getDirectionB();
        path1.positionFromPath = posPathOne;
        path2.positionFromPath = postPathTwo;

        for (int i = 0; i < array.size; i++) {
            CorridorPathStep currentStep = array.get(i);
            CorridorPathStep nextStep = ArrayUtils.isNotLast(array, i) ? array.get(i + 1) : null;
            Direction nextDir = nextStep == null ? null : nextStep.getDirection();

            path1.nextPositionFromPath(currentStep.getDirection(), nextDir);
            path2.nextPositionFromPath(currentStep.getDirection(), nextDir);

            Vector2 path2 = computePos(currentStep, this.path1.positionFromPath);

            currentStep.getDirection().addTo(middle, currentStep.getLength());
        }
        return  null;
    }


    private Vector2 computePos(CorridorPathStep step, Direction dirFromPos) {
        float lenghtToAdd = step.getWalkSize() / 2 + step.getWallSize() / 2;
        return dirFromPos.addTo(middle.cpy(), lenghtToAdd);
    }

    private SubPath getOpposite(Direction nextDir) {
        return path1.positionFromPath.getReverse() == nextDir ? path1 : path2;
    }

    private SubPath getOther(Direction nextDir) {
        return getOpposite(nextDir) == path1 ? path2 : path1;
    }

    private class SubPath {
        Direction positionFromPath;
        boolean oppositeForNext;
        Array<RectanglePathStep> array = new Array<>();

        public void addRectangle(Direction direction, Vector2 startMiddle, float length, float otherSize) {
            direction.addTo(startMiddle, length / 2);
            RectangleBuilder.withOrientation(direction.getOrientation(), startMiddle, length, otherSize, true);
        }

        public void nextPositionFromPath(Direction previous, Direction nextDirection) {
            if (nextDirection != null && nextDirection != previous) {
                positionFromPath = DirectionUtils.getNextDirOnTurn(positionFromPath, previous, nextDirection);
                oppositeForNext = positionFromPath.getReverse() == nextDirection;
            }

        }
    }
}
