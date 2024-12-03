package com.github.fabiitch.nz.java.time.timers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountDown {
    private float duration;
    private float acc = 0;

    public CountDown(float duration) {
        this.duration = duration;
    }

    public boolean update(float dt) {
        acc += dt;
        return acc >= duration;
    }

    public boolean finish() {
        return acc >= duration;
    }

    public void reset() {
        acc = 0;
    }
}
