package com.github.fabiitch.nz.java.math.shapes.utils.rectangle;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.r;

public class CuttingRectangleTest {

    @Test
    public void throwTest() {
        Rectangle rectangle = r(0, 0, 10, 10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> CuttingRectangle.cutRectangleHorizontally(rectangle, 15, 5));
    }

    @Test
    public void cutMiddleTest() {
        Rectangle rectangle = r(0, 0, 10, 10);
        CuttingRectangle cuttingRectangle = CuttingRectangle.cutRectangleVertically(rectangle, 5, 2);

        Assertions.assertEquals(r(0, 0, 5, 10), cuttingRectangle.getNewRectangle1());
        Assertions.assertEquals(r(5, 0, 2, 10), cuttingRectangle.getCuttingRect());
        Assertions.assertEquals(r(7, 0, 3, 10), cuttingRectangle.getNewRectangle2());
    }

    @Test
    public void cutLeftTest() {
        Rectangle rectangle = r(0, 0, 10, 10);
        CuttingRectangle cuttingRectangle = CuttingRectangle.cutRectangleVertically(rectangle, 0, 2);

        Assertions.assertNull(cuttingRectangle.getNewRectangle1());
        Assertions.assertEquals(r(0, 0, 2, 10), cuttingRectangle.getCuttingRect());
        Assertions.assertEquals(r(2, 0, 8, 10), cuttingRectangle.getNewRectangle2());
    }

    @Test
    public void cutRightTest() {
        Rectangle rectangle = r(0, 0, 10, 10);
        CuttingRectangle cuttingRectangle = CuttingRectangle.cutRectangleVertically(rectangle, 8, 2);

        Assertions.assertEquals(r(0, 0, 8, 10), cuttingRectangle.getNewRectangle1());
        Assertions.assertEquals(r(8, 0, 2, 10), cuttingRectangle.getCuttingRect());
        Assertions.assertNull(cuttingRectangle.getNewRectangle2());
    }

    @Test
    public void cutAllTest() {
        Rectangle rectangle = r(0, 0, 10, 10);
        CuttingRectangle cuttingRectangle = CuttingRectangle.cutRectangleVertically(rectangle, 0, 20);
        Assertions.assertNull(cuttingRectangle.getNewRectangle1());
        Assertions.assertNull(cuttingRectangle.getNewRectangle2());
        Assertions.assertEquals(r(0, 0, 10, 10), cuttingRectangle.getCuttingRect());
    }
}
