package com.fabiitch.nz.math.g2d.rectangle;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.fabiitch.nz.math.MathTestUtils.r;
import static com.fabiitch.nz.math.MathTestUtils.v2;

public class RectangleUtilsTest {

    @Test
    public void getRandomPosTest() {
        Rectangle rect1 = r(0, 0, 100, 50);
        for (int i = 0; i < 100; i++)
            Assertions.assertTrue(rect1.contains(RectangleUtils.getRandomPos(rect1, v2())));
    }
}
