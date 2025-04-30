package com.github.fabiitch.nz.java.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectUtils {

    public boolean isNotNull(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                return false;
        }
        return true;
    }

    public boolean isNull(Object... objects) {
        return !isNotNull(objects);
    }
}
