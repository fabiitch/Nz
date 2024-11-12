package com.github.fabiitch.nz.java.utils.randoms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Randoms {

    @SafeVarargs
    public static <T> T get(T... tab) {
        return tab[MathUtils.random(tab.length - 1)];
    }

    public static <T> T get(List<T> list) {
        return list.get(MathUtils.random(list.size() - 1));
    }

    public static <T> T get(Array<T> array) {
        return array.get(MathUtils.random(array.size - 1));
    }

    public static Vector2 v2(float maxX, float maxY) {
        return v2(0, maxX, 0, maxY, new Vector2());
    }

    public static Vector2 v2(float minX, float maxX, float minY, float maxY) {
        return v2(minX, maxX, minY, maxY, new Vector2());
    }

    public static Vector2 v2(float minX, float maxX, float minY, float maxY, Vector2 vector) {
        float randomX = MathUtils.random(minX, maxX);
        float randomY = MathUtils.random(minY, maxY);
        return vector.set(randomX, randomY);
    }

    public static Color color() {
        float r = MathUtils.random();
        float g = MathUtils.random();
        float b = MathUtils.random();
        Color randomColor = new Color(r, g, b, 1);
        return randomColor;
    }

    public static Color color(Color color) {
        float r = MathUtils.random();
        float g = MathUtils.random();
        float b = MathUtils.random();
        color.r = r;
        color.g = g;
        color.b = b;
        return color;
    }
}
