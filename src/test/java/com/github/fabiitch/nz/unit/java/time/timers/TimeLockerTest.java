package com.github.fabiitch.nz.unit.java.time.timers;

import com.github.fabiitch.nz.java.time.timers.TimeLocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeLockerTest {

    @Test
    public void test(){
        TimeLocker timeLocker = new TimeLocker(100);

        Assertions.assertTrue(timeLocker.isLock());
        Assertions.assertTrue(timeLocker.isLock(50));
        Assertions.assertTrue(timeLocker.isLock(49));

        Assertions.assertFalse(timeLocker.isLock(1));

        Assertions.assertTrue(timeLocker.isLock());
        Assertions.assertFalse(timeLocker.isLock(100));
    }
}
