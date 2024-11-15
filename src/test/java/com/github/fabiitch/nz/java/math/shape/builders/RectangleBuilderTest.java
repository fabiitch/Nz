package com.github.fabiitch.nz.java.math.shape.builders;

import com.badlogic.gdx.math.Rectangle;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.r;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RectangleBuilderTest {
    @Test
    public void getRectsAroundTest() {
        Rectangle rect1 = r(0, 0, 100, 50);
        Rectangle[] rectsAround1 = RectangleBuilder.getRectsAround(rect1, 10);
        assertEquals(r(0, -10, 100, 10), rectsAround1[0]);
        assertEquals(r(0, 50, 100, 10), rectsAround1[1]);
        assertEquals(r(-10, 0, 10, 50), rectsAround1[2]);
        assertEquals(r(100, 0, 10, 50), rectsAround1[3]);

        Rectangle rect2 = r(-200, 100, 200, 100);
        Rectangle[] rectsAround2 = RectangleBuilder.getRectsAround(rect2, 50);
        assertEquals(r(-200, 100 - 50, 200, 50), rectsAround2[0]);
        assertEquals(r(-200, 200, 200, 50), rectsAround2[1]);
        assertEquals(r(-200 - 50, 100, 50, 100), rectsAround2[2]);
        assertEquals(r(-200 + 200, 100, 50, 100), rectsAround2[3]);
    }


    @Test
    public void getBorderRectTest() {
        Rectangle r = r(0, 0, 50, 100);

        assertEquals(r(50, 0, 40, 100), RectangleBuilder.getBorderRect(r, Direction.Right, 40));

        assertEquals(r(-40, 0, 40, 100), RectangleBuilder.getBorderRect(r, Direction.Left, 40));

        assertEquals(r(0, 100, 50, 40), RectangleBuilder.getBorderRect(r, Direction.Top, 40));

        assertEquals(r(0, -40, 50, 40), RectangleBuilder.getBorderRect(r, Direction.Bot, 40));
    }

    @Test
    public void getSubRectTest() {
        Rectangle r = r(500, 500, 500, 200);

        assertEquals(r(550, 500, 50, 200), RectangleBuilder.getSubRect(r, Direction.Left, 50, 50));
        assertEquals(r(900, 500, 50, 200), RectangleBuilder.getSubRect(r, Direction.Right, 50, 50));

        assertEquals(r(500, 550, 500, 50), RectangleBuilder.getSubRect(r, Direction.Bot, 50, 50));
        assertEquals(r(500, 600, 500, 50), RectangleBuilder.getSubRect(r, Direction.Top, 50, 50));
    }
}
