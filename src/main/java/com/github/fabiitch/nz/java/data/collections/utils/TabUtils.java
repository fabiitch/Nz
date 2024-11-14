package com.github.fabiitch.nz.java.data.collections.utils;

public class TabUtils {
    private TabUtils() {

    }

    /**
     * @return -1 if not found
     */
    public static int indexOf(Object[] tab, Object o) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == o)
                return i;
        }
        return -1;
    }

    public static boolean contains(final Object[] tab, Object o) {
        for (Object object : tab)
            if (object == o)
                return true;
        return false;
    }

    //org/apache/commons/lang3/ArrayUtils.java:1417
    public static void reverse(final Object[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    //org/apache/commons/lang3/ArrayUtils.java:1417
    public static void reverse(final Object[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        Object tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public static <T> void clearValues(T[] array, int begin) {
        for (int i = begin, n = array.length; i < n; i++) {
            array[i] = null;
        }
    }

    public static <T> void clearValues(T[] array, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            array[i] = null;
        }
    }

    /**
     * all value to null
     */
    public static <T> void clearValues(T[] array) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = null;
        }
    }

    public static <T> void removeAndDecal(T[] array, int index) { //TODO voir si plus rapid
        for (int i = index, n = array.length - 1; i < n; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = null;
    }
}
