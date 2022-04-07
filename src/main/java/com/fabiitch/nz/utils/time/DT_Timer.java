package com.fabiitch.nz.utils.time;

import com.fabiitch.nz.math.utils.Percentage;

public class DT_Timer {
    public float duration;//seconds
    public float accumulator;

    public DT_Timer(float duration) {
        this.duration = duration;
    }

    /**
     * true if duration is reach
     */
    public boolean update(float dt) {
        accumulator += dt;
        return done();
    }

    public void reset() {
        accumulator = 0;
    }

    public boolean done() {
        return accumulator >= duration;
    }

    public float getAlpha(float time) {
        return Percentage.getAlpha(time, duration);
    }

    public float getAlpha() {
        return Percentage.getAlpha(accumulator, duration);
    }
}
