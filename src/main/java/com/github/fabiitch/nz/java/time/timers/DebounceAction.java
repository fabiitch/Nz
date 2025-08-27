package com.github.fabiitch.nz.java.time.timers;

import lombok.Getter;
import lombok.Setter;

public class DebounceAction {

    @Getter
    @Setter
    private float timer;
    private float accumulator;
    public Runnable action;

    public DebounceAction(float timer) {
        this.timer = timer;
    }

    public void update(float delta) {
        if (action == null)
            return;
        accumulator += delta;
        if (accumulator >= timer) {
            accumulator = 0;
            action.run();
            action = null;
        }
    }

    public void setAction(Runnable action) {
        this.action = action;
        accumulator = 0;
    }
}
