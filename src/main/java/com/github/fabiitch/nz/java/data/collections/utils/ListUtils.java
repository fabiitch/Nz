package com.github.fabiitch.nz.java.data.collections.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ListUtils {
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
