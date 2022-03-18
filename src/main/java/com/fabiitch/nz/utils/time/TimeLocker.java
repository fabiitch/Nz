package com.fabiitch.nz.utils.time;

public class TimeLocker extends Duration {

    public boolean active = true;

    public TimeLocker(float duration) {
        super(duration);
    }

    public TimeLocker(float duration, boolean active) {
        super(duration);
        this.active = active;
    }

    public boolean isLock(float dt) {
        accumulator += dt;
        if (isLock() == false) {
            accumulator = 0;
            return false;
        }
        return true;
    }

    @Override
    public void update(float dt) {
        if (active)
            super.update(dt);
    }

    public boolean isLock() {
        return active && accumulator < duration;
    }
}
