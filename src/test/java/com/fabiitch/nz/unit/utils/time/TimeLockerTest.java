package com.fabiitch.nz.unit.utils.time;

import com.fabiitch.nz.utils.time.TimeLocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeLockerTest {

    @Test
    public void test(){
        TimeLocker timeLocker = new TimeLocker(100);

        Assertions.assertFalse(timeLocker.isLock());

        Assertions.assertFalse(timeLocker.isLock(50));
        Assertions.assertFalse(timeLocker.isLock(49));
        Assertions.assertTrue(timeLocker.isLock(2));
        Assertions.assertFalse(timeLocker.isLock());
    }
}
