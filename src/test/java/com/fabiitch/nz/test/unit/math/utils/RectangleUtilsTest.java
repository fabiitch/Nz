package com.fabiitch.nz.test.unit.math.utils;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Test;

import static com.fabiitch.nz.math.utils.RectangleUtils.containsStick;
import static com.fabiitch.nz.test.unit.math.utils.MathTesterUtils.r;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleUtilsTest {

    @Test
    public void containsSickTest() {
        Rectangle rectMergeA = r(0, 0, 200, 200);
        Rectangle rectMergeB = r(10, 290, 20, 20);
        rectMergeA.merge(rectMergeB);
        assertTrue(containsStick(rectMergeA, rectMergeB));

        rectMergeA = r(0, 0, 200, 200);
        rectMergeB = r(10, 290.96002f, 20, 20);
        rectMergeA.merge(rectMergeB);
        assertTrue(containsStick(rectMergeA, rectMergeB));

        //Test 2
        assertTrue(containsStick(r(10, 10, 100, 100), r(10, 10, 100, 100)));

        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 10, 101, 100)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 10, 100, 101)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(11, 10, 100, 100)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 11, 100, 100)));
    }

}
