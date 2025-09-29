package com.github.fabiitch.nz.java.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {
    public static boolean lowerContains(String str, String contains) {
        if (str == null) {
            return false;
        }
        if (contains == null) {
            return false
        }
       return str.toLowerCase().contains(contains.toLowerCase());
    }
}
