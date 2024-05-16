package com.github.fabiitch.nz.java.data.collections.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils {
    public static <T> List<T> asList(T... values) {
        Arrays.asList(values);
        List<T> list = new ArrayList<>(Arrays.asList(values));
        return list;
    }
}
