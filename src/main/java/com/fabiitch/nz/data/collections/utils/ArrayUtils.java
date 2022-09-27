package com.fabiitch.nz.data.collections.utils;

import com.badlogic.gdx.utils.Array;

public class ArrayUtils {
//    public static <T> Array<T> reverse(final Array<T> array) {
//        int j = Math.min(array.length, endIndexExclusive) - 1;
//        Object tmp;
//        while (j > i) {
//            tmp = array[j];
//            array[j] = array[i];
//            array[i] = tmp;
//            j--;
//            i++;
//        }
//    }


    public static <T> T getLast(Array<T> array) {
        if (array.size == 0)
            return null;
        return array.get(array.size - 1);
    }

    // 0 is last
    public static <T> T getFromEnd(Array<T> array, int num) {
        if (array.size <= num)
            return null;
        return array.get(array.size - 1 - num);
    }

    /**
     * @return the array reverse
     */
    public static <T> Array<T> getRangeFromEnd(Array<T> array, int from, int to, Array<T> result) {
        int fromInArray = Math.max(0, array.size - ++to);

        result.addAll(array, fromInArray, to - from);
        TabUtils.reverse(result.items);
        return result;
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
