package com.github.fabiitch.nz.java.time.timers;

public class TimerRange {

    public float minTime;
    public float maxTime;

    private float internalTimer;

    public enum State {Less, InRange, More}

    public TimerRange(float minTime, float maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
    public static TimerRange of(float minTime, float maxTime){
        return new TimerRange(minTime, maxTime);
    }

    public void reset() {
        internalTimer = 0;
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
