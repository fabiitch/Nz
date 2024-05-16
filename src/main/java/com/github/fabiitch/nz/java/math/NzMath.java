package com.github.fabiitch.nz.java.math;

import com.badlogic.gdx.math.MathUtils;

public class NzMath {

    public static final float FLOAT_ROUNDING_ERROR = MathUtils.FLOAT_ROUNDING_ERROR;

    private NzMath() {

    }
    public static boolean isLong(String strNum){
        if (strNum == null) {
            return false;
        }
        try {
            long l = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean isFloat(String strNum){
        if (strNum == null) {
            return false;
        }
        try {
            float f = Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //TODO plein de replace a faire sur les distance qui ne peuvent etre negative
    public static boolean isZero(float positive) {
        return positive <= FLOAT_ROUNDING_ERROR;
    }

    public static boolean isZero(float positive, float tolerance) {
        return positive <= tolerance;
    }

    //TODO présent en java8 sur Math
    //TODO doc
    public static int floorMod(int x, int y) {
        int r = x - floorDiv(x, y) * y;
        return r;
    }

    //TODO présent en java8 sur Math
    //TODO doc
    public static int floorDiv(int x, int y) {
        int r = x / y;
        // if the signs are different and modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }

    public static boolean sameSign(float a, float b) {
        return (a >= 0) ^ (b < 0);
    }

    public static boolean sameSignWithZero(float a, float b) {
        if (MathUtils.isZero(a) || MathUtils.isZero(b))
            return true;
        return (a >= 0) ^ (b < 0);
    }

    public static boolean sameSignWithZero(float a, float b, float tolerance) {
        if (MathUtils.isZero(a, tolerance) || MathUtils.isZero(b, tolerance))
            return true;
        return (a >= 0) ^ (b < 0);
    }

}
