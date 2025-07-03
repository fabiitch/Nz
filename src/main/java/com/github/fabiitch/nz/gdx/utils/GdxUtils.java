package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

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
}
