package com.github.fabiitch.nz.gdx.debug;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.fabiitch.nz.java.utils.CommonStrings;

/**
 * not thread safe //TODO thread local
 */
public class DebugDisplayUtils {

    private static final Float nan = Float.valueOf(Float.NaN);//TODO ??

    public static String printNano(float f) {
        return f + "ns";
    }

    public static String printMs(float f) {
        return f + "ms";
    }

    public static String printNanoToMs(long nano) {
        return TimeUtils.nanosToMillis(nano) + "ms";
    }

    public static String printSeconds(float f) {
        String floating = printFloat(f);
        int indexDot = floating.indexOf(",");
        int charAfterDot = floating.length() - indexDot;
        return floating.subSequence(0, indexDot + Math.min(charAfterDot, 3)).toString() + "s";
    }

    public static String printValue(Object o) {
        if (o == null)
            return CommonStrings.Null;
        if (o instanceof Number) {
            return String.valueOf(o);
        }
        if (o instanceof Vector2)
            return printVector2((Vector2) o);
        if (o instanceof Vector3)
            return printVector3((Vector3) o);
//        if(o instanceof IntCounter)
//            return printIntCounter((IntCounter) o);

        return o.toString();
    }

    //    private static String printIntCounter(IntCounter o) {
//        if (o.count == 0) {
//            return "Waiting Data ...";
//        }
//        return o.toStringCurrentAverage();
//    }

    public static String printFloat(float f, int maxDecimal) {
        String[] split = String.valueOf(f).split("\\.");
        int indexFloating = Math.min(split[1].length(), maxDecimal);
        return split[0] + "," + split[1].substring(0, indexFloating);
    }

    public static String printFloat(float f) {
        if (f == 0)
            return "0";
        if (Float.isNaN(f))
            return "Nan";
        if (Float.isInfinite(f))
            return "Infinite";
        String[] split = String.valueOf(f).split("\\.");
        if (split.length == 1)
            return split[0];
        if (split[0].length() > 5)
            return split[0];
        int indexFloatting = Math.min(split[1].length(), 4);
        return split[0] + "," + split[1].substring(0, indexFloatting);
    }

    public static String printVector2(Vector2 v) {
        if (v.isZero())
            return Vector2.Zero.toString();
        return "(" + printFloat(v.x) + " , " + printFloat(v.y) + ")";
    }

    public static String printVector3(Vector3 v) {
        if (v.isZero())
            return Vector3.Zero.toString();
        return "(" + printFloat(v.x) + " , " + printFloat(v.y) + " , " + printFloat(v.z) + ")";
    }
}
