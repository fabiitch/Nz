package com.fabiitch.nz.test.unit.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.fabiitch.nz.data.quadtree.QuadTreeUtils.*;
import static com.fabiitch.nz.test.unit.math.utils.MathTesterUtils.r;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadTreeUtilsTest {


    @Test
    public void getSubRegionRect() {
        Rectangle rect = r(0, 0, 200, 100);
        assertEquals(getNW(rect, r()), r(0, 50, 100, 50));
        assertEquals(getSW(rect, r()), r(0, 0, 100, 50));
        assertEquals(getNE(rect, r()), r(100, 50, 100, 50));
        assertEquals(getSE(rect, r()), r(100, 0, 100, 50));
    }

    @Test
    public void splitContainsRectTest() {
        Rectangle parent = new Rectangle(0, 0, 200, 100);

        Assertions.assertTrue(splitContainsRect(parent, r(0,0,100,50)));
        Assertions.assertTrue(splitContainsRect(parent, r(100,50,100,50)));
        Assertions.assertFalse(splitContainsRect(parent, r(100,50,100.001f,50)));

        Assertions.assertTrue(splitContainsRect(parent, r(80,40,20,10)));
        Assertions.assertFalse(splitContainsRect(parent, r(81,41,20,10)));

        Assertions.assertFalse(splitContainsRect(parent, r(90,10,20,10)));
    }
}
