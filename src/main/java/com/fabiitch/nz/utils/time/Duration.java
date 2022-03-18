package com.fabiitch.nz.utils.time;

import com.fabiitch.nz.math.utils.Percentage;

public class Duration {
    public float duration;//seconds
    public float accumulator;

    public Duration(float duration) {
        this.duration = duration;
    }

    public void update(float dt) {
        accumulator += dt;
    }

    public void reset() {
        accumulator = 0;
    }

    public float getAlpha(float time) {
        return Percentage.getAlpha(time, duration);
    }

    public float getAlpha() {
        return Percentage.getAlpha(accumulator, duration);
    }
}
