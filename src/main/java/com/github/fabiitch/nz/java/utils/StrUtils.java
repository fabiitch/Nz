package com.github.fabiitch.nz.java.utils;

public class StrUtils {
    public static String replaceAllNonAlphanumeric(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "");
    }

    public static String removeBegin(String str, String begin) {
        return str.substring(0, begin.length() - 1);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
