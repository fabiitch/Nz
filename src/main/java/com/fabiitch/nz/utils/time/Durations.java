package com.fabiitch.nz.utils.time;

public class Durations {

    public final static long nanoInSecond = 1_000_000_000;
    public final static long millisInSecond = 1_000_000;

    public static long nanoToSeconds(long startNano, long endNano) {
        return (endNano - startNano) / nanoInSecond;
    }

    public static long millisToSeconds(long startMillis, long endMillis) {
        return (endMillis - startMillis) / millisInSecond;
    }
}
