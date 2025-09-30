package com.github.fabiitch.nz.java.function;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

@UtilityClass
public class ConsumerUtils {

    public static <T> Consumer<T> chain(Consumer<T>... consumers) {
        return Arrays.stream(consumers)
                .filter(Objects::nonNull)
                .reduce(t -> {}, Consumer::andThen);
    }
}
