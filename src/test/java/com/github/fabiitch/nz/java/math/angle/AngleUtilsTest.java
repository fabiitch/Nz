package com.github.fabiitch.nz.java.math.angle;

import com.badlogic.gdx.math.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.nz.java.math.angle.AngleUtils.*;
import static com.github.fabiitch.nz.java.math.vectors.v2.V2.v;
import static org.junit.jupiter.api.Assertions.*;

public class AngleUtilsTest {
    private static final float DELTA_0 = 0f;
    private static final float DELTA_01 = 0.1f;

    @Test
    public void testCoherence() {
        float dst1Signed = distanceSigned(v(1, 0), v(0, 1));
        float dst1Abs = distanceAbs(v(1, 0), v(0, 1));
        Assertions.assertEquals(Math.abs(dst1Signed), dst1Abs, DELTA_0);

        float dst2Signed = distanceSigned(v(1, 0), v(-1, 0));
        float dst2Abs = distanceAbs(v(1, 0), v(-1, 0));
        assertEquals(Math.abs(dst2Signed), dst2Abs, DELTA_0);

        float dst3Signed = distanceSigned(v(1, 0), v(1, -0.1f));
        float dst3Abs = distanceAbs(v(1, 0), v(1, -0.1f));
        assertEquals(Math.abs(dst3Signed), dst3Abs, DELTA_0);
    }

    @Test
    public void distanceSignedTest() {
        assertEquals(-50, distanceSigned(50, 100));
        assertEquals(50, distanceSigned(100, 50));

        assertEquals(-50, distanceSigned(50, 100 + 360 * 5));
        assertEquals(-50, distanceSigned(50 + 360 * 5, 100));

        assertEquals(-50, distanceSigned(100, 50 + 360 * 5));
        assertEquals(-50, distanceSigned(100 + 360 * 5, 50));

    }

    @Test
    public void distanceAbsTest() {
        float dst1 = distanceAbs(v(1, 0), v(0, 1));
        assertEquals(90, dst1, DELTA_01);

        float dst2 = distanceAbs(v(1, 0), v(-1, 0));
        assertEquals(180, dst2, DELTA_01);

        assertEquals(0, distanceAbs(0, 360), DELTA_01);

        assertEquals(20, distanceAbs(10, 350), DELTA_01);
    }

    @Test
    public void normaliseAngleDegTest() {
        assertEquals(0, normaliseDeg(0), DELTA_01);
        assertEquals(50, normaliseDeg(50), DELTA_01);
        assertEquals(0, normaliseDeg(360), DELTA_01);
        assertEquals(5, normaliseDeg(365), DELTA_01);
        assertEquals(0, normaliseDeg(720), DELTA_01);

        assertEquals(355, normaliseDeg(-5), DELTA_01);
        assertEquals(180, normaliseDeg(-180), DELTA_01);
        assertEquals(0, normaliseDeg(-720), DELTA_01);
    }


    @Test
    public void normaliseAngleRad02PiTest() {
        assertEquals(0, AngleUtils.normaliseRad02Pi(0), DELTA_01);
        assertEquals(MathUtils.PI, AngleUtils.normaliseRad02Pi(MathUtils.PI), DELTA_01);
        assertEquals(MathUtils.PI, AngleUtils.normaliseRad02Pi(-MathUtils.PI), DELTA_01);
        assertEquals(0, AngleUtils.normaliseRad02Pi(-MathUtils.PI2), DELTA_01);

        assertEquals(MathUtils.PI, AngleUtils.normaliseRad02Pi(MathUtils.PI * 3), DELTA_01);
        assertEquals(0, AngleUtils.normaliseRad02Pi(MathUtils.PI * 4), DELTA_01);
    }

    @Test
    public void normaliseRadPiNPiTest() {
        assertEquals(0, AngleUtils.normaliseRadPiNPi(0), DELTA_01);
        assertEquals(-MathUtils.PI, AngleUtils.normaliseRadPiNPi(MathUtils.PI), DELTA_01);

        assertEquals(-MathUtils.PI, AngleUtils.normaliseRadPiNPi(-MathUtils.PI), DELTA_01);
        assertEquals(0, AngleUtils.normaliseRadPiNPi(-MathUtils.PI2), DELTA_01);

        assertEquals(-MathUtils.PI, AngleUtils.normaliseRadPiNPi(MathUtils.PI * 3), DELTA_01);
        assertEquals(0, AngleUtils.normaliseRadPiNPi(MathUtils.PI * 4), DELTA_01);
    }

    @Test
    public void isSameLineTest() {
        assertTrue(isSameLine(0, 0, 0));
        assertTrue(isSameLine(360, 360, 0));
        assertTrue(isSameLine(90, 90, 0));


        assertTrue(isSameLine(0, 180, 0));
        assertTrue(isSameLine(360, 180, 0));
        assertTrue(isSameLine(90, 270, 0));

        assertFalse(isSameLine(3, 180, 0));
        assertFalse(isSameLine(1, 180, 0));

        assertTrue(isSameLine(5, 180, 5));
        assertTrue(isSameLine(365, 180, 5));
        assertTrue(isSameLine(85, 270, 5));

        assertTrue(isSameLine(85, -270, 5));
        assertTrue(isSameLine(-85, 270, 5));
    }

    @Test
    public void isReverseTest() {
        assertTrue(isReverse(0, 180, 0));
        assertTrue(isReverse(0, -180, 0));
        assertTrue(isReverse(360, 180, 0));
        assertTrue(isReverse(90, 270, 0));

        assertTrue(isReverse(15, 180, 15));
        assertTrue(isReverse(15, -180, 15));

        assertFalse(isReverse(15, 574, 15));
        assertFalse(isReverse(15, -16, 15));

    }

}
