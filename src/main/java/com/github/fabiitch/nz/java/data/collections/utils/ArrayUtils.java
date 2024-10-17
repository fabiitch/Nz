package com.github.fabiitch.nz.java.data.collections.utils;

import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.function.Filter;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    public static <T> T removeLast(Array<T> array) {
        T last = getLast(array);
        if (last != null) {
            array.removeIndex(array.size - 1);
        }
        return last;
    }

    public static <T> T removeFirst(Array<T> array) {
        T first = getFirst(array);
        if (first != null) {
            array.removeIndex(0);
        }
        return first;
    }

    public static <T> void filter(Array<T> array, Filter<T> filter) {
        for (int i = 0; i < array.size; i++) {
            if (!filter.accept(array.get(i))) {
                array.removeIndex(i);
                i--;
            }
        }
    }

    public static <T> boolean containsOnly(Array<T> array, T... values) {
        //TODO pools array
        Array<T> arrayTmp = new Array<>(array);

        for (T value : values) {
            if (arrayTmp.contains(value, true))
                arrayTmp.removeValue(value, true);
            else
                return false;
        }
        return arrayTmp.size == 0;
    }

    public static <T> boolean containsAll(Array<T> array, T... values) {
        for (T value : values) {
            if (!array.contains(value, true))
                return false;
        }
        return true;
    }

    public static <T> boolean containsAll(Array<T> array, boolean identity, T... values) {
        for (T value : values) {
            if (!array.contains(value, identity))
                return false;
        }
        return true;
    }

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

    public static <T> T getFirst(Array<T> array) {
        if (array.size == 0)
            return null;
        return array.get(0);
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

    public static <T> Array<T> copy(Array<T> source, Array<T> target) {
        int diff = source.size - target.size;
        if (diff < 0) { //target bigger than source
            for (int i = 0, n = source.size; i < n; i++) {
                target.set(i, source.get(i));
            }
            target.removeRange(source.size, target.size - 1);
        } else {
            for (int i = 0, n = target.size; i < n; i++) {
                target.set(i, source.get(i));
            }
            for (int i = target.size, n = source.size; i < n; i++) {
                target.add(source.get(i));
            }
        }
        return target;
    }
}
