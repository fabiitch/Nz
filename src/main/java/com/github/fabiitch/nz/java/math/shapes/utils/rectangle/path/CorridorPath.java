package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.vectors.V2;

public class CorridorPath extends RectanglePath {

    float walkSize = 100;

    public Array<Rectangle> compute(Vector2 start) {
        Direction startDir = array.get(0).getDirection();
        Direction posPathOne = startDir.getOrientation().getOtherOrientation().getDirectionA();
        Direction postPathTwo = startDir.getOrientation().getOtherOrientation().getDirectionB();

        Vector2 middle = new Vector2(start);

        SubPath path1 = new SubPath(posPathOne.addTo(start.cpy(), walkSize / 2), posPathOne);
        SubPath path2 = new SubPath(postPathTwo.addTo(start.cpy(), walkSize / 2), postPathTwo);
        float lengthToAddNext = 0;
        for (int i = 0; i < array.size; i++) {
            RectanglePathStep current = array.get(i);
            Direction currentDirection = current.getDirection();
            RectanglePathStep next = ArrayUtils.isNotLast(array, i) ? array.get(i + 1) : null;

            if (next != null && next.getDirection() != currentDirection) {
                Direction nextDir = array.get(i + 1).getDirection();
                SubPath pathOpposite = path1.dirFromPos.getReverse() == nextDir ? path1 : path2;
                SubPath other = pathOpposite == path1 ? path2 : path1;
                pathOpposite.add(currentDirection, current.getLength() + walkSize, current.getSize());
                other.add(currentDirection, current.getLength(), current.getSize());
                lengthToAddNext = walkSize;
            } else {
                path1.add(currentDirection, current.getLength(), current.getSize());
                path2.add(currentDirection, current.getLength(), current.getSize());
                lengthToAddNext = 0;
            }
            currentDirection.addTo(middle, current.getLength());
            path1.resetDir(middle);
            path2.resetDir(middle);
        }

        Array<Rectangle> compute1 = path1.compute();
        Array<Rectangle> compute2 = path2.compute();

        Array<Rectangle> result = new Array<>();
        for (int i = 0; i < compute1.size; i++) {
            result.add(compute1.get(i));
            result.add(compute2.get(i));
        }
        return result;
    }

    private class SubPath {
        Vector2 startPosition;
        Vector2 position;
        Direction dirFromPos;
        RectanglePathSmooth pathSmooth = new RectanglePathSmooth();

        public SubPath(Vector2 startPosition, Direction dirFromPos) {
            this.startPosition = startPosition;
            this.position = new Vector2(startPosition);
            this.dirFromPos = dirFromPos;
        }

        public void resetDir(Vector2 middle) {
            dirFromPos = Direction.getClosestDirection(V2.directionTo(position, middle, new Vector2()));
        }

        public Array<Rectangle> compute() {
            return pathSmooth.compute(startPosition);
        }

        public RectanglePath add(Direction direction, float length, float size) {
            direction.addTo(position, length);
            return pathSmooth.add(direction, length, size);
        }
    }
}
