package com.fabiitch.nz.utils;

public class TimeLocker {
    public float duration;//seconds
    public float accumulator;

    public TimeLocker(float duration) {
        this.duration = duration;
    }

    public boolean isLock(float dt) {
        accumulator += dt;
        if (isLock() == false) {
            accumulator = 0;
            return false;
        }
        return true;
    }

    public boolean isLock() {
        return accumulator < duration;
    }
}
