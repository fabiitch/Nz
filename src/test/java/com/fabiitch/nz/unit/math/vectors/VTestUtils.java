package com.fabiitch.nz.unit.math.vectors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.junit.jupiter.api.Assertions;

/**
 * utils for test Vectors and print value in junit
 */
public class VTestUtils {
    private static final float TOLERANCE = MathUtils.FLOAT_ROUNDING_ERROR;

    //  V2  ========================
    public static void assertEquals(float x, float y, Vector2 v, float tolerance, String msgError) {
        Assertions.assertEquals(x, v.x, tolerance, msgError == null ? "x not equals" : msgError + ": x not equals");
        Assertions.assertEquals(y, v.y, tolerance, msgError == null ? "y not equals" : msgError + ": y not equals");
    }

    public static void assertEquals(Vector2 v1, Vector2 v2, float tolerance, String msgError) {
        assertEquals(v1.x, v1.y, v2, tolerance, msgError);
    }

    public static void assertEquals(Vector2 v1, Vector2 v2) {
        assertEquals(v1, v2, TOLERANCE);
    }

    public static void assertEquals(float x, float y, Vector2 v) {
        assertEquals(x, y, v, TOLERANCE, null);
    }

    public static void assertEquals(float x, float y, Vector2 v, float tolerance) {
        assertEquals(x, y, v, tolerance, null);
    }

    public static void assertEquals(Vector2 v1, Vector2 v2, float tolerance) {
        assertEquals(v1, v2, tolerance, null);
    }

    public static void assertEquals(Vector2 v1, Vector2 v2, String msgError) {
        assertEquals(v1, v2, TOLERANCE, msgError);
    }


    //  V3  ========================
    public static void assertEquals(float x, float y, float z, Vector3 v2, float tolerance, String msgError) {
        Assertions.assertEquals(x, v2.x, tolerance, msgError == null ? "x not equals" : msgError + ": x not equals");
        Assertions.assertEquals(y, v2.y, tolerance, msgError == null ? "y not equals" : msgError + ": y not equals");
        Assertions.assertEquals(z, v2.z, tolerance, msgError == null ? "z not equals" : msgError + ": z not equals");
    }

    public static void assertEquals(float x, float y, float z, Vector3 v) {
        assertEquals(x, y, z, v, TOLERANCE, null);
    }

    public static void assertEquals(Vector3 v1, Vector3 v2, String msgError) {
        assertEquals(v1.x, v1.y, v1.z, v2, TOLERANCE, msgError);
    }

    public static void assertEquals(Vector3 v1, Vector3 v2) {
        assertEquals(v1, v2, TOLERANCE);
    }

    public static void assertEquals(Vector3 v1, Vector3 v2, float tolerance) {
        assertEquals(v1.x, v1.y, v1.z, v2, tolerance, null);
    }

    //  !V2  ========================
    public static void assertNotEquals(Vector2 v1, Vector2 v2, float tolerance, String errorMsg) {
        Assertions.assertNotEquals(v1.x, v2.x, tolerance, errorMsg == null ? "x equals" : errorMsg + ": x equals");
        Assertions.assertNotEquals(v1.y, v2.y, tolerance, errorMsg == null ? "y equals" : errorMsg + ": y equals");
    }

    public static void assertNotEquals(Vector2 v1, Vector2 v2, float tolerance) {
        assertNotEquals(v1, v2, tolerance, null);
    }

    public static void assertNotEquals(Vector2 v1, Vector2 v2, String msgError) {
        assertNotEquals(v1, v2, TOLERANCE, msgError);
    }

    public static void assertNotEquals(Vector2 v1, Vector2 v2) {
        assertNotEquals(v1, v2, TOLERANCE);
    }

    //  !V3  ========================
    public static void assertNotEquals(Vector3 v1, Vector3 v2, float tolerance, String errorMsg) {
        Assertions.assertNotEquals(v1.x, v2.x, tolerance, errorMsg == null ? "x equals" : errorMsg + ": x equals");
        Assertions.assertNotEquals(v1.y, v2.y, tolerance, errorMsg == null ? "y equals" : errorMsg + ": y equals");
        Assertions.assertNotEquals(v1.z, v2.z, tolerance, errorMsg == null ? "z equals" : errorMsg + ": z equals");
    }

    public static void assertNotEquals(Vector3 v1, Vector3 v2) {
        assertNotEquals(v1, v2, TOLERANCE, null);
    }

    public static void assertNotEquals(Vector3 v1, Vector3 v2, float tolerance) {
        assertNotEquals(v1, v2, tolerance, null);
    }
}
