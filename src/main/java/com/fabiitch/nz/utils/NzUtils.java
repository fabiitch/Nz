package com.fabiitch.nz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fabiitch.nz.math.shapes.builders.RectangleBuilder;

public class NzUtils {

    private NzUtils() {

    }

    public static Rectangle screenAsRectangle(boolean centerAs0) {
        return RectangleBuilder.screen(centerAs0);
    }

    public static Rectangle screenAsRectangle(Camera camera, boolean centerAs0) {
        return RectangleBuilder.screen(camera, centerAs0);
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
