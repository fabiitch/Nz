package com.github.fabiitch.nz.gdx.debug.profile.dt;

import com.github.fabiitch.nz.gdx.debug.profile.DTProfiler;
import com.github.fabiitch.nz.java.time.timers.TimeLocker;
import lombok.Getter;

@Getter
public class DtLogProfiler {

    private final DTProfiler profiler;
    private final TimeLocker timeLocker;

    public DtLogProfiler(String name, float windowSeconds) {
        this.profiler = new DTProfiler(name, windowSeconds);
        this.timeLocker = new TimeLocker(windowSeconds);
    }

    public void begin() {
        profiler.begin();
    }

    public boolean endAndLog(float dt) {
        profiler.end();
        return timeLocker.isOpen(dt);
    }
}
