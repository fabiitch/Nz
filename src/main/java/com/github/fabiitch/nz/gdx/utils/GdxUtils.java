package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class GdxUtils {

    public static long getHeapMb() {
        return Gdx.app.getJavaHeap() / (1024L * 1024L);
    }
    public static long getNativeHeapMb() {
        return Gdx.app.getNativeHeap() / (1024L * 1024L);
    }

    public static boolean isMobile() {
        Application.ApplicationType type = Gdx.app.getType();
        return type == Application.ApplicationType.Android || type == Application.ApplicationType.iOS;
    }

    public static boolean isDesktop() {
        return !isMobile();
    }

    public static String getJavaHeapLog() {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long used = total - free;
        long max = rt.maxMemory();

        return "Heap -> used=" + used / 1024 / 1024 + "MB"
                + " / total=" + total / 1024 / 1024 + "MB"
                + " / max=" + max / 1024 / 1024 + "MB";
    }


    public static String getMemoryLog() {
        Runtime rt = Runtime.getRuntime();

        long heapTotal = rt.totalMemory();
        long heapFree  = rt.freeMemory();
        long heapUsed  = heapTotal - heapFree;
        long heapMax   = rt.maxMemory();

        double nativeUsed = -1;

        try {
            OperatingSystemMXBean os =
                    (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            nativeUsed = os.getProcessCpuLoad(); // dummy init guard
            nativeUsed = os.getCommittedVirtualMemorySize();
        } catch (Throwable ignored) {
            // JVM sans accès aux extensions com.sun.*
        }

        return String.format(
                "MEM | heap: used=%dMB total=%dMB max=%dMB | native: committed=%s",
                heapUsed / 1024 / 1024,
                heapTotal / 1024 / 1024,
                heapMax / 1024 / 1024,
                nativeUsed > 0 ? (nativeUsed / 1024 / 1024 + "MB") : "n/a"
        );
    }

}
