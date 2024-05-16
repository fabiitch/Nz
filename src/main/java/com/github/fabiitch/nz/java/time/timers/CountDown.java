package com.github.fabiitch.nz.java.time.timers;

import com.github.fabiitch.nz.java.function.DoIt;

//TODO utile ?
public class CountDown {
    public float duration;
    public DoIt doIt;
    public float internalTimer;

    public CountDown(float duration, DoIt doIt) {
        this.duration = duration;
        this.doIt = doIt;
    }

    public CountDown(float duration) {
        this.duration = duration;
    }

    /**
     * return true if end
     *
     * @param dt
     * @return
     */
    public boolean update(float dt) {
        if (internalTimer < duration) {
            internalTimer += dt;
            if (internalTimer >= duration) {
                if (doIt != null)
                    doIt.doIt();
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        internalTimer = 0;
    }
}

