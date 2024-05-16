package com.github.fabiitch.nz.unit.java.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.nz.java.time.TimeConverter.*;

public class TimeConverterTest {

    private final static float SECOND_MAX_DELTA = 0.0001f;
    private final static long MILLIS_MAX_DELTA = 1;
    private final static long NANOS_MAX_DELTA = 10;

    @Test
    public void secondToMillisTest() {
        Assertions.assertEquals(10_000, secondToMillis(10), MILLIS_MAX_DELTA);

        Assertions.assertEquals(10 * MILLIS_IN_SECOND, secondToMillis(10), MILLIS_MAX_DELTA);
        Assertions.assertEquals(MILLIS_IN_SECOND / 2, secondToMillis(0.5f), MILLIS_MAX_DELTA);

        Assertions.assertEquals(MILLIS_IN_SECOND * 55 + MILLIS_IN_SECOND * .0005F,
                secondToMillis(55.0005f), MILLIS_MAX_DELTA);
    }

    @Test
    public void secondToNanosTest() {
        Assertions.assertEquals(10 * NANO_IN_SECOND, secondToNano(10), NANOS_MAX_DELTA);
        Assertions.assertEquals(NANO_IN_SECOND / 2, secondToNano(0.5f), NANOS_MAX_DELTA);

        Assertions.assertEquals(NANO_IN_SECOND * 55 + NANO_IN_SECOND * .0005F,
                secondToNano(55.0005f), NANOS_MAX_DELTA);
    }

    @Test
    public void millisToSecondTest() {
        Assertions.assertEquals(2, millisToSecond(2000), SECOND_MAX_DELTA);

        Assertions.assertEquals(10, millisToSecond(secondToMillis(10)), SECOND_MAX_DELTA);
        Assertions.assertEquals(0.005f, millisToSecond(secondToMillis(0.005f)), SECOND_MAX_DELTA);
        Assertions.assertEquals(0.5f, millisToSecond(MILLIS_IN_SECOND / 2), SECOND_MAX_DELTA);
    }

    @Test
    public void millisToNanoTest() {
        Assertions.assertEquals(1000, millisToNano(1), NANOS_MAX_DELTA);
        Assertions.assertEquals(5000 * 1000, millisToNano(5000), NANOS_MAX_DELTA);
    }

    @Test
    public void nanoToSecondTest() {
        Assertions.assertEquals(0.5f, nanoToSecond(NANO_IN_SECOND / 2), SECOND_MAX_DELTA);
        Assertions.assertEquals(10, nanoToSecond(NANO_IN_SECOND * 10), SECOND_MAX_DELTA);
        Assertions.assertEquals(55.005f, nanoToSecond((long) (NANO_IN_SECOND * 55.005f)), SECOND_MAX_DELTA);
    }

    @Test
    public void nanoToMillisTest() {
        Assertions.assertEquals(1, nanoToMillis(NANO_IN_MILLIS), SECOND_MAX_DELTA);
        Assertions.assertEquals(0, nanoToMillis(NANO_IN_MILLIS / 2), SECOND_MAX_DELTA);
        Assertions.assertEquals(1, nanoToMillis(NANO_IN_MILLIS + NANO_IN_MILLIS / 2), SECOND_MAX_DELTA);

        Assertions.assertEquals(15_000, nanoToMillis(15_000 * 1000), SECOND_MAX_DELTA);
    }

}
