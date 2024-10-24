package com.github.fabiitch.nz.java.math.path.rectangle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.utils.ArrayContainer;
import lombok.Getter;

@Getter
public class RectanglePath extends ArrayContainer<RectanglePathStep> {


    public Array<Rectangle> compute(Vector2 start) {
        Array<Rectangle> result = new Array<>();
        Vector2 cpy = start.cpy();

        Direction lastDir = null;

        for (RectanglePathStep pathStep : array) {
            Direction direction = pathStep.getDirection();
            if (lastDir != null && lastDir == direction.getReverse())
                throw new GdxRuntimeException("Cant go in reverse direction");
            float length = pathStep.getLength();
            float size = pathStep.getSize();

            direction.addTo(cpy, length / 2);
            Rectangle rectangle = RectangleBuilder.withOrientation(direction.getOrientation(), cpy, length, size, true);
            result.add(rectangle);
            direction.addTo(cpy, length / 2);

            lastDir = direction;
        }
        return result;
    }

    public RectanglePath add(Direction direction, float length, float size) {
        array.add(new RectanglePathStep(direction, length, size));
        return this;
    }

}
