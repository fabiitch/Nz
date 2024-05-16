package com.github.fabiitch.nz.java.data.collections.utils;

import com.badlogic.gdx.utils.Array;

//TODO ??
public class ToArray {
    private ToArray() {

    }

    public static <T> Array<T> get(T[][] tab) {
        Array<T> array = new Array<>();
        for (int i = 0; i < tab.length; i++) {
            array.addAll(tab[i]);
        }
        return array;
    }

    public static <T> Array<T> get(T v1) {
        Array<T> array = new Array<>(1);
        array.add(v1);
        return array;
    }

    public static <T> Array<T> get(T... values) {
        Array<T> array = new Array<>(values.length);
        array.addAll(values);
        return array;
    }

    public static <T> Array<T> get(T v1, T v2) {
        Array<T> array = new Array<>(2);
        array.add(v1, v2);
        return array;
    }

    public static <T> Array<T> get(T v1, T v2, T v3) {
        Array<T> array = new Array<>(3);
        array.add(v1, v2, v3);
        return array;
    }
}
