package com.fabiitch.nz.java.data.collections.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<T> asList(T... values) {
        List<T> list = new ArrayList<>();
        for (T value : values)
            list.add(value);
        return list;
    }
}
