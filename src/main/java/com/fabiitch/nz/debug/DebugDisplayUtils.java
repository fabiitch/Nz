package com.fabiitch.nz.debug;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.fabiitch.nz.utils.CommonStrings;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;

/**
 * not thread safe //TODO thread local
 */
public class DebugDisplayUtils {
    private static final DecimalFormat floatFormatter = new DecimalFormat();
    private static final DecimalFormat msFormatter = new DecimalFormat();

    private static StringBuffer sb = new StringBuffer();
    private static FieldPosition fieldPosition = new FieldPosition(0);
    private static final Float nan = Float.valueOf(Float.NaN);//TODO ??

  static  {
      floatFormatter.setMaximumFractionDigits(3);
      floatFormatter.setGroupingUsed(false);
      DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
      decimalFormatSymbols.setDecimalSeparator(',');
      floatFormatter.setDecimalFormatSymbols(decimalFormatSymbols);

      msFormatter.setMaximumFractionDigits(0);
      msFormatter.setGroupingUsed(false);
  }

    public static String printNano(float f) {
        return f + "ns";
    }

    public static String printMs(float f) {
        return f + "ms";
    }

    public static String printNanoToMs(long nano) {
        return TimeUtils.nanosToMillis(nano) + "ms";
    }

    public static String printValue(Object o) {
      if(o == null)
          return CommonStrings.Null;
        if (o instanceof Number) {
            String s = floatFormatter.format(o, sb, fieldPosition).toString();
            sb.setLength(0);
            return s;
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

    public static String printFloat(float f) {
        if (f == 0)
            return "0";
        if (Float.isNaN(f))
            return "Nan";
        if (Float.isInfinite(f))
            return "Infinite";
        String s = floatFormatter.format(f, sb, fieldPosition).toString();
        sb.setLength(0);
        return s;
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
