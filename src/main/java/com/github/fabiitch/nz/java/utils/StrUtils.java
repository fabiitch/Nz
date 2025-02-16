package com.github.fabiitch.nz.java.utils;

public class StrUtils {
    public static String replaceAllNonAlphanumeric(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "");
    }
}
