package com.fabiitch.nz.data.array;

import com.badlogic.gdx.utils.Array;

public class ArrayUtils {

    public static <T> T getLast(Array<T> array) {
        if (array.size == 0)
            return null;
        return array.get(array.size - 1);
    }


    public static <T> Array<T> addIfNotPresent(Array<T> array, T value) {
        if (!array.contains(value, true)) {
            array.add(value);
        }
        return array;
    }

    public static <T> Array<T> addIfNotPresent(Array<T> array, Array<T> values) {
        for (int i = 0, n = values.size; i < n; i++) {
            addIfNotPresent(array, values.get(i));
        }
        return array;
    }

    public static <T> Array<T> addIfNotPresent(Array<T> array, T... values) {
        for (int i = 0, n = values.length; i < n; i++) {
            addIfNotPresent(array, values[i]);
        }
        return array;
    }

}
