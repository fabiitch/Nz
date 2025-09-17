package com.github.fabiitch.nz.java.data.collections.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ListUtils {

    public static <T> T getFirst(List<T> list) {
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
    public static <T> T getLast(List<T> list) {
        return list.get(list.size()-1);
    }

    public static <T> List<T> takeXLast(List<T> list, int xLast) {
        int sizeList = list.size();
        int take = Math.min(xLast, sizeList);

        List<T> result = new ArrayList<>(take);
        for (int i = 0; i < take; i++) {
            T data = list.get(sizeList - i - 1);
            result.add(data);
        }
        return result;
    }


    public static <T> List<T> asList(T... values) {
        Arrays.asList(values);
        List<T> list = new ArrayList<>(Arrays.asList(values));
        return list;
    }

    public static boolean isEmpty(List<?> list) {
        return list != null && list.isEmpty();
    }

    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }
}
