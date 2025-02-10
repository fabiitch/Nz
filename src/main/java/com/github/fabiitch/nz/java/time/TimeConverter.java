package com.github.fabiitch.nz.java.time;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeConverter {

    public final static long MILLIS_IN_SECOND = 1_000;
    public final static long NANO_IN_SECOND = 1_000_000_000;
    public final static long NANO_IN_MILLIS = 1000;

    public final static double D_NANO_IN_SECOND = 1_000_000_000;
    public final static double D_MILLIS_IN_SECOND = 1_000;
    public final static double D_MILLIS_IN_NANO = 1000;

    public static long secondToMillis(float seconds) {
        return (long) (seconds * MILLIS_IN_SECOND);
    }

    public static long secondToMillis(double seconds) {
        return (long) (seconds * MILLIS_IN_SECOND);
    }

    public static long secondToNano(float seconds) {
        return (long) (seconds * NANO_IN_SECOND);
    }

    public static float millisToSecond(long millis) {
        return (float) (millis / D_MILLIS_IN_SECOND);
    }

    public static long millisToNano(long millis) {
        return millis * NANO_IN_MILLIS;
    }

    public static float nanoToSecond(long nanos) {
        return (float) (nanos / D_NANO_IN_SECOND);
    }

    public static long nanoToMillis(long nanos) {
        return nanos / NANO_IN_MILLIS;
    }

}
