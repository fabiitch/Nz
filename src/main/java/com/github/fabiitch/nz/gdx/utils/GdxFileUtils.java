package com.github.fabiitch.nz.gdx.utils;

import com.badlogic.gdx.Gdx;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GdxFileUtils {

    public static boolean internalExist(final String path) {
        return Gdx.files.internal(path).exists();
    }

    public static boolean absoluteExist(final String path) {
        return Gdx.files.absolute(path).exists();
    }

    public static boolean external(final String path) {
        return Gdx.files.external(path).exists();
    }

    public static boolean local(final String path) {
        return Gdx.files.local(path).exists();
    }
}
