package com.fabiitch.nz.java.data.collections.utils;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

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

    public static <T> List<T> toList(Array<T> array) {
        List<T> list = new ArrayList<>(array.size);
        for (int i = 0, n = array.size; i < n; i++) {
            list.add(array.get(i));
        }
        return list;
    }

    public static <T> Array<T> fromList(List<T> list) {
        Array<T> array = new Array<>(list.size());
        for (int i = 0, n = list.size(); i < n; i++) {
            array.add(list.get(i));
        }
        return array;
    }

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

    /**
     * //TODO utile cette methode ?
     * add in result (no clear if result has entry)
     */
    public static <T> Array<T> getRange(Array<T> array, int from, int to, Array<T> result) {
        result.addAll(array, from, to);
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
