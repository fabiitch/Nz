package com.github.fabiitch.nz.java.time;

public class Durations {

    public final static long nanoInSecond = 1_000_000_000;
    public final static long millisInSecond = 1_000_000;

    public static float nanoToSeconds(long startNano, long endNano) {
        return TimeConverter.nanoToSecond(endNano - startNano);
    }

    public static float millisToSeconds(long startMillis, long endMillis) {
        return TimeConverter.millisToSecond(endMillis - startMillis);
    }

}
