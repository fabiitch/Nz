package com.github.fabiitch.nz.java.logger;


import com.badlogic.gdx.utils.CharArray;

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
        CharArray charArray = new CharArray(); //TODO pools ?
        charArray.append(SEPARATOR);
        for (int i = 1; i < n; i++) {
            charArray.append("\n");
            charArray.append(SEPARATOR);
        }
        return charArray.toString();
    }
}
