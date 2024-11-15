package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.path.rectangle.RectanglePath;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectanglePathTest {

    @Test
    public void simpleTest() {
        RectanglePath path = new RectanglePath();

        path.add(Direction.Right, 100, 50);
        path.add(Direction.Top, 100, 50);
        path.add(Direction.Top, 100, 50);
        path.add(Direction.Left, 100, 50);

        Array<Rectangle> compute = path.compute(new Vector2(0,0));

        assertEquals(rCenter(50, 0, 100, 50), compute.get(0));
        assertEquals(rCenter(100, 50, 50, 100), compute.get(1));
        assertEquals(rCenter(100, 150, 50, 100), compute.get(2));
        assertEquals(rCenter(50, 200, 100, 50), compute.get(3));

    }
}
