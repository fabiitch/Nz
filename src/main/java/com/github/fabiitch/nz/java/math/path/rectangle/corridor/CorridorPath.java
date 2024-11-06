package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathSmooth;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePathStep;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.DirectionUtils;
import com.github.fabiitch.nz.java.utils.ArrayContainer;

public class CorridorPath extends ArrayContainer<CorridorPathStep> {

    public void add(Direction direction, int length, int walkSize, float wallSize) {
        array.add(new CorridorPathStep(direction, length, walkSize, wallSize));
    }


    public Array<Rectangle> compute(Vector2 start) {
        if (array.isEmpty())
            return new Array<>();

        CorridorPathStep firstStep = array.get(0);
        Direction startDir = firstStep.getDirection();
        Direction posPathOne = startDir.getOrientation().getOtherOrientation().getDirectionA();
        Direction postPathTwo = startDir.getOrientation().getOtherOrientation().getDirectionB();

        SubPath subPath1 = new SubPath(posPathOne.addTo(start.cpy(), firstStep.getWalkSize() / 2 + firstStep.getWallSize() / 2), posPathOne);
        SubPath subPath2 = new SubPath(postPathTwo.addTo(start.cpy(), firstStep.getWalkSize() / 2+ firstStep.getWallSize() / 2), postPathTwo);
        Computer computer = new Computer(start.cpy(), subPath1, subPath2);

        for (int i = 0; i < array.size; i++) {
            CorridorPathStep current = array.get(i);
            CorridorPathStep next = ArrayUtils.isNotLast(array, i) ? array.get(i + 1) : null;
            Direction nextDir = next == null ? null : next.getDirection();

            computer.add(current, nextDir);
        }

        Array<Rectangle> compute1 = subPath1.compute();
        Array<Rectangle> compute2 = subPath2.compute();

        Array<Rectangle> result = new Array<>();
        for (int i = 0; i < compute1.size; i++) {
            result.add(compute1.get(i));
            result.add(compute2.get(i));
        }
        return result;
    }

    private class Computer {
        Vector2 middle;
        SubPath path1;
        SubPath path2;

        SubPath pathOppositeLast;

        public Computer(Vector2 middle, SubPath path1, SubPath path2) {
            this.middle = middle;
            this.path1 = path1;
            this.path2 = path2;
        }

        float lengthToAddNext;

        public void add(CorridorPathStep step, Direction nextDir) {
            Direction currentDir = step.getDirection();
            float length = step.getLength();
            float walkSize = step.getWalkSize();
            float wallSize = step.getWallSize();

            if (nextDir != null && currentDir != nextDir) {
                SubPath pathOpposite = getOppositeFromTurn(nextDir);
                SubPath other = getOther(nextDir);
                pathOpposite.add(currentDir, length + walkSize, wallSize);
                other.add(currentDir, length, wallSize);

                upLastOpposite();
                pathOppositeLast = pathOpposite;
                lengthToAddNext = walkSize;

                path1.resetDir(currentDir, nextDir);
                path2.resetDir(currentDir, nextDir);
            } else {
                path1.add(currentDir, length, wallSize);
                path2.add(currentDir, length, wallSize);
                upLastOpposite();
                lengthToAddNext = 0;

            }
            currentDir.addTo(middle, length);

        }

        private void upLastOpposite() {
            if (pathOppositeLast != null) {
                RectanglePathStep last = ArrayUtils.getLast(pathOppositeLast.array);
                last.setLength(last.getLength() + lengthToAddNext);
                pathOppositeLast = null;
                lengthToAddNext = 0;
            }
        }

        private SubPath getOppositeFromTurn(Direction nextDir) {
            return path1.dirFromPos.getReverse() == nextDir ? path1 : path2;
        }

        private SubPath getOther(Direction nextDir) {
            return getOppositeFromTurn(nextDir) == path1 ? path2 : path1;
        }
    }

    private static class SubPath {
        Vector2 startPosition;
        Vector2 currPosition;
        Direction dirFromPos;
        Array<RectanglePathStep> array = new Array<>();

        public SubPath(Vector2 startPosition, Direction dirFromPos) {
            this.startPosition = startPosition;
            this.currPosition = startPosition.cpy();
            this.dirFromPos = dirFromPos;
        }

        public void resetDir(Direction previous, Direction nextDirection) {
            dirFromPos = DirectionUtils.getNextDirOnTurn(dirFromPos, previous, nextDirection);
        }

        public Array<Rectangle> compute() {
            RectanglePathSmooth smooth = new RectanglePathSmooth();
            smooth.getArray().addAll(this.array);
            return smooth.compute(startPosition);
        }

        public void add(Direction direction, float length, float size) {
            direction.addTo(currPosition, length);
            array.add(new RectanglePathStep(direction, length, size));
        }

        @Override
        public String toString() {
            return "SubPath{" +
                    "currPosition=" + currPosition +
                    ", dirFromPos=" + dirFromPos +
                    '}';
        }
    }

}
