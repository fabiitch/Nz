package com.github.fabiitch.nz.java.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] parts = input
                .replaceAll("[^a-zA-Z0-9]+", " ")
                .trim()
                .split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            if (i == 0) {
                sb.append(part);
            } else {
                sb.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1));
            }
        }

        return sb.toString();
    }

    public static boolean lowerContains(String str, String contains) {
        if (str == null) {
            return false;
        }
        if (contains == null) {
            return false;
        }
       return str.toLowerCase().contains(contains.toLowerCase());
    }
}
