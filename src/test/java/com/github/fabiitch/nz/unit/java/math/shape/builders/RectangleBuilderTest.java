package com.github.fabiitch.nz.unit.java.math.shape.builders;

import com.badlogic.gdx.math.Rectangle;
import com.github.fabiitch.gdxunit.ShapeTestBuilder;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class RectangleBuilderTest {
    @Test
    public void getRectsAroundTest() {
        Rectangle rect1 = ShapeTestBuilder.r(0, 0, 100, 50);
        Rectangle[] rectsAround1 = RectangleBuilder.getRectsAround(rect1, 10);
        Assertions.assertEquals(ShapeTestBuilder.r(0, -10, 100, 10), rectsAround1[0]);
        Assertions.assertEquals(ShapeTestBuilder.r(0, 50, 100, 10), rectsAround1[1]);
        Assertions.assertEquals(ShapeTestBuilder.r(-10, 0, 10, 50), rectsAround1[2]);
        Assertions.assertEquals(ShapeTestBuilder.r(100, 0, 10, 50), rectsAround1[3]);

        Rectangle rect2 = ShapeTestBuilder.r(-200, 100, 200, 100);
        Rectangle[] rectsAround2 = RectangleBuilder.getRectsAround(rect2, 50);
        Assertions.assertEquals(ShapeTestBuilder.r(-200, 100 - 50, 200, 50), rectsAround2[0]);
        Assertions.assertEquals(ShapeTestBuilder.r(-200, 200, 200, 50), rectsAround2[1]);
        Assertions.assertEquals(ShapeTestBuilder.r(-200 - 50, 100, 50, 100), rectsAround2[2]);
        Assertions.assertEquals(ShapeTestBuilder.r(-200 + 200, 100, 50, 100), rectsAround2[3]);
    }

}
