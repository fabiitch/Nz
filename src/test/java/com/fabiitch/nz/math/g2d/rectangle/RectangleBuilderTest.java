package com.fabiitch.nz.math.g2d.rectangle;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Test;

import static com.fabiitch.nz.math.MathTestUtils.r;
import static com.fabiitch.nz.math.g2d.rectangle.RectangleBuilder.getRectsAround;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleBuilderTest {

    @Test
    public void getRectsAroundTest() {
        Rectangle rect1 = r(0, 0, 100, 50);
        Rectangle[] rectsAround1 = getRectsAround(rect1, 10);
        assertEquals(r(0, -10, 100, 10), rectsAround1[0]);
        assertEquals(r(0, 50, 100, 10), rectsAround1[1]);
        assertEquals(r(-10, 0, 10, 50), rectsAround1[2]);
        assertEquals(r(100, 0, 10, 50), rectsAround1[3]);

        Rectangle rect2 = r(-200, 100, 200, 100);
        Rectangle[] rectsAround2 = getRectsAround(rect2, 50);
        assertEquals(r(-200, 100 - 50, 200, 50), rectsAround2[0]);
        assertEquals(r(-200, 200, 200, 50), rectsAround2[1]);
        assertEquals(r(-200 - 50, 100, 50, 100), rectsAround2[2]);
        assertEquals(r(-200 + 200, 100, 50, 100), rectsAround2[3]);
    }

}
