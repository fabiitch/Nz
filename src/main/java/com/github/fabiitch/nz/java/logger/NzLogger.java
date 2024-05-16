package com.github.fabiitch.nz.java.logger;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class NzLogger {

    public static boolean log = true;
    public static boolean logAlwaysException = true;
    public static boolean authoriseTagStrategy = true;

    private static HashMap<String, Boolean> tagMap = new HashMap<>();

    public static void addTag(String tag) {
        tagMap.put(tag, true);
    }

    public static void removeTag(String tag) {
        tagMap.put(tag, false);
    }

    public static void addTag(String[] tags) {
        for (String tag : tags)
            tagMap.put(tag, true);
    }

    public static void removeTag(String[] tags) {
        for (String tag : tags)
            tagMap.put(tag, false);
    }

    public static void acceptOnly(String... tags) {
        authoriseTagStrategy = false;
        for (String tag : tags)
            addTag(tag);
    }

    private static boolean accept(String tag) {
        Boolean tagBool = tagMap.get(tag);
        if (tagBool == null) {
            return authoriseTagStrategy;
        } else {
            return tagBool;
        }
    }

    public static void debug(String tag, String message) {
        if (log && accept(tag))
            Gdx.app.debug(tag, message);
    }

    public static void debug(String tag, String message, Throwable exception) {
        if (logAlwaysException || (log && accept(tag)))
            Gdx.app.debug(tag, message, exception);
    }

    public static void log(Enum tag, String message) {
        log(tag.name(), message);
    }

    public static void log(String tag, String message) {
        if (log && accept(tag))
            Gdx.app.log(tag, message);
    }

    public static void log(Enum tag, String message, Throwable exception) {
        log(tag.name(), message, exception);
    }

    public static void log(String tag, String message, Throwable exception) {
        if (logAlwaysException || (log && accept(tag)))
            Gdx.app.log(tag, message, exception);
    }

    public static void error(String tag, String message) {
        if (log && accept(tag))
            Gdx.app.error(tag, message);
    }

    public static void error(String tag, String message, Throwable exception) {
        if (logAlwaysException || (log && accept(tag)))
            Gdx.app.error(tag, message, exception);
    }

}
