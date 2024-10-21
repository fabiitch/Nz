package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;

public class CorridorPath extends RectanglePath {

    int walkSize = 100;

    public Array<Rectangle> compute(Vector2 start) {
        Array<Rectangle> result = new Array<>();
        RectanglePathSmooth path1 = new RectanglePathSmooth();
        RectanglePathSmooth path2 = new RectanglePathSmooth();


        Direction startDir = array.get(0).getDirection();

        Direction posPathOne = startDir.getOrientation().getOtherOrientation().getDirectionA();
        Direction postPathTwo = startDir.getOrientation().getOtherOrientation().getDirectionB();

        Direction lastDir = null;

        Vector2 path1Start = posPathOne.addTo(start.cpy(), walkSize);
        Vector2 path2Start = postPathTwo.addTo(start.cpy(), walkSize);

        for (int i = 0; i < array.size; i++) {
            RectanglePathStep step = array.get(i);
            Direction direction = step.getDirection();
            if (!ArrayUtils.isLast(array, i)) {
                Direction nextDir = array.get(i + 1).getDirection();
                if (nextDir != direction) {

                }
            } else {
            }
        }
        return null;
    }

    private class SubPath {
        Vector2 position;
        Direction dirFromPos;
        RectanglePathSmooth pathSmooth = new RectanglePathSmooth();

        public SubPath(Vector2 position, Direction dirFromPos) {
            this.position = position;
            this.dirFromPos = dirFromPos;
        }
    }
}
