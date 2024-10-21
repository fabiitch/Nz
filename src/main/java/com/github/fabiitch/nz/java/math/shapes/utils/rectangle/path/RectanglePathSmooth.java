package com.github.fabiitch.nz.java.math.shapes.utils.rectangle.path;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;

/**
 * Next Dir rect block direct
 * lenght is from the corner
 */
public class RectanglePathSmooth extends RectanglePath {

    @Override
    public Array<Rectangle> compute(Vector2 start) {
        Array<Rectangle> result = new Array<>();
        Vector2 cpy = start.cpy();
        RectanglePathStep lastStep = null;

        for (RectanglePathStep step : array) {
            Direction direction = step.getDirection();
            if (lastStep != null && lastStep.getDirection() == direction.getReverse())
                throw new GdxRuntimeException("Cant go in reverse direction");
            float length = step.getLength();
            float size = step.getSize();

            if (lastStep != null && lastStep.getDirection() != direction) {
                lastStep.getDirection().addTo(cpy, size / 2);
                direction.addTo(cpy, length / 2);
                length += lastStep.getSize();
            } else {
                direction.addTo(cpy, length / 2);
            }

            Rectangle rectangle = RectangleBuilder.withOrientation(direction.getOrientation(), cpy, length, size, true);
            result.add(rectangle);
            direction.addTo(cpy, length / 2);

            lastStep = step;
        }
        return result;

    }
}
