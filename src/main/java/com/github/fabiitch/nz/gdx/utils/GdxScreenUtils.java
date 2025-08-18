package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GdxScreenUtils {

    public static Vector2 getScreenResolution() {
        return new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    public static float getScreenRatio() {
        return Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
    }

    public static Vector2 getPercentPos(float percentX, float percentY) {
        float x = Percentage.value(percentX, Gdx.graphics.getWidth());
        float y = Percentage.value(percentY, Gdx.graphics.getHeight());
        return new Vector2(x, y);
    }
}
