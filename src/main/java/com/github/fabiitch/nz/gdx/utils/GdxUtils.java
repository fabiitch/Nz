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
}
