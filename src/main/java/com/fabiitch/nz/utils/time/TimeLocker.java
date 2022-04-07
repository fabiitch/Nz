package com.fabiitch.nz.utils.time;

/**
 * Locked during duration time
 */
public class TimeLocker extends DT_Timer {

    public boolean active = true;

    public TimeLocker(float duration) {
        super(duration);
    }

    public TimeLocker(float duration, boolean active) {
        super(duration);
        this.active = active;
    }

    public boolean isLock(float dt) {
        boolean unlock = update(dt);
        if (unlock) {
            accumulator -= duration;
        }
        return !unlock;
    }


    @Override
    public boolean update(float dt) {
        return active && super.update(dt);
    }

    public boolean isLock() {
        return active && accumulator < duration;
    }
}
