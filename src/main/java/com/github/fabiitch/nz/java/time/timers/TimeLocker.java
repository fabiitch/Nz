package com.github.fabiitch.nz.java.time.timers;


/**
 * Locked during duration time
 */
public class TimeLocker {

    public boolean autoReset = true;
    public boolean active = true;

    public float duration;
    private float accumulator;

    public TimeLocker(float duration) {
        this.duration = duration;
    }

    public boolean isOpen() {
        return accumulator >= duration;
    }

    public boolean isLock() {
        return accumulator < duration;
    }

    public boolean isOpen(float dt) {
        return update(dt);
    }

    public boolean isLock(float dt) {
        return !update(dt);
    }

    private boolean update(float dt) {
        if (active) {
            accumulator += dt;
            boolean end = accumulator >= duration;
            if (end && autoReset) {
                accumulator = 0;
            }
            return end;
        }
        return false;
    }

    public TimeLocker config(boolean autoReset, boolean active) {
        this.autoReset = autoReset;
        this.active = active;
        return this;
    }

    public void reset() {
        accumulator = 0;
    }
}
