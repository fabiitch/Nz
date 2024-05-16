package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ScreenUtils {

    private ScreenUtils() {

    }

    public static Vector2 getScreenCenter(Vector2 pos) {
        return pos.set(getScreenCenterX(), getScreenCenterY());
    }

    public static float getScreenCenterX() {
        return Gdx.graphics.getWidth() / 2;
    }

    public static float getScreenCenterY() {
        return Gdx.graphics.getHeight() / 2;
    }
}
