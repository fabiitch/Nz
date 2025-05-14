package com.github.fabiitch.nz.java.utils.randoms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Randoms {

    public static int get(int max) {
        return MathUtils.random(max);
    }

    public static int get(int min, int max) {
        return MathUtils.random(min, max);
    }

    public static boolean bool() {
        return MathUtils.randomBoolean();
    }

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

    public static Vector2 randomDir(Vector2 result) {
        return result.set(1, 0).setToRandomDirection();
    }

    public static Vector2 randomDir() {
        return new Vector2(1, 0).setToRandomDirection();
    }

    public static Vector2 v2(float maxX, float maxY, Vector2 result) {
        return v2(0, maxX, 0, maxY, result);
    }

    public static Vector2 v2(float maxX, float maxY) {
        return v2(0, maxX, 0, maxY, new Vector2());
    }

    public static Vector2 v2(float minX, float maxX, float minY, float maxY) {
        return v2(minX, maxX, minY, maxY, new Vector2());
    }

    public static Vector2 v2(float minX, float maxX, float minY, float maxY, Vector2 result) {
        float randomX = MathUtils.random(minX, maxX);
        float randomY = MathUtils.random(minY, maxY);
        return result.set(randomX, randomY);
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

    public static int randomSignInt(int start, int min, int max) {
        boolean add = MathUtils.randomBoolean();
        int rdm = MathUtils.random(min, max);
        return add ? start + rdm : start - rdm;
    }
}
