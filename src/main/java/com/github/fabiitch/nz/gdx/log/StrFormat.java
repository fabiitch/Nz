package com.github.fabiitch.nz.gdx.log;

import com.badlogic.gdx.utils.Array;

import java.util.StringJoiner;
import java.util.function.Function;
import java.util.regex.Matcher;

public class StrFormat {

    public static String format(String msg, Object... params) {
        for (Object param : params) {
            String safeParam = Matcher.quoteReplacement(param != null ? param.toString() : "null");
            msg = msg.replaceFirst("\\{}", safeParam);
        }
        return msg;
    }


    public static String join(String delimiter, Object... args) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object arg : args) {
            joiner.add(arg.toString());
        }
        return joiner.toString();
    }

    public static String join(String delimiter, Array<Object> args) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object arg : args) {
            joiner.add(arg.toString());
        }
        return joiner.toString();
    }

    public static <T> String join(String delimiter, Function<T, String> printer, T... args) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (T arg : args) {
            joiner.add(printer.apply(arg));
        }
        return joiner.toString();
    }

    public static <T> String join(String delimiter, Function<T, String> printer, Array<T> array) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (T arg : array) {
            joiner.add(printer.apply(arg));
        }
        return joiner.toString();
    }
}
