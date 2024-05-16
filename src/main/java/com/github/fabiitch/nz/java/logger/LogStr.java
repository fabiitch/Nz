package com.github.fabiitch.nz.java.logger;

import com.badlogic.gdx.utils.StringBuilder;

public class LogStr {

    public static final String SEPARATOR = "=================================================";
    public static final String HALF_LINE = "=================";

    public static String asTag(String tag){
        return "["+tag+"] ";
    }

    public static String separator(String title) {
        return SEPARATOR + "\n" + "                       " + title + "\n" + SEPARATOR;
    }

    public static String separator(int n) {
        StringBuilder sb = new StringBuilder(); //TODO pools ?
        sb.append(SEPARATOR);
        for (int i = 1; i < n; i++) {
            sb.append("\n");
            sb.append(SEPARATOR);
        }
        return sb.toString();
    }
}
