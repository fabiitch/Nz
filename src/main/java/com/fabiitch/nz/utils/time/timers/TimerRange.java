package com.fabiitch.nz.utils.time.timers;

import com.fabiitch.nz.utils.GameTick;

public class TimerRange {

    public float minTime;
    public float maxTime;

    private float internalTimer;

    public enum State {Less, InRange, More}

    public TimerRange(float minTime, float maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public void reset() {
        internalTimer = 0;
    }

    public State update(GameTick gameTick) {
        return update(gameTick.dt);
    }

    public State update(float dt) {
        internalTimer += dt;
        return getState();
    }

    public State getState() {
        if (internalTimer < minTime)
            return State.Less;
        if (internalTimer > maxTime)
            return State.More;
        return State.InRange;
    }
}
