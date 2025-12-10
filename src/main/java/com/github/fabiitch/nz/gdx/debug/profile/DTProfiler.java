package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.utils.Array;

public class DTProfiler {
    private final Array<Sample> samples = new Array<>();
    private final float windowSeconds;

    public DTProfiler(float windowSeconds) {
        this.windowSeconds = windowSeconds;
    }

    private long frameStart;

    public void begin() {
        frameStart = System.nanoTime();
    }

    public void end() {
        long now = System.nanoTime();
        float ms = (now - frameStart) / 1_000_000f;

        samples.add(new Sample(ms, now));

        // Purge la fenêtre glissante
        float windowNanos = windowSeconds * 1_000_000_000f;
        long minTime = now - (long) windowNanos;

        while (samples.size > 0 && samples.first().time < minTime) {
            samples.removeIndex(0);
        }
    }

    public float getAvgMs() {
        if (samples.size == 0) return 0;
        float sum = 0;
        for (Sample s : samples) sum += s.ms;
        return sum / samples.size;
    }

    public float getMaxMs() {
        float max = 0;
        for (Sample s : samples) if (s.ms > max) max = s.ms;
        return max;
    }

    public int getFPS() {
        float avg = getAvgMs();
        if (avg == 0) return 0;
        return (int) (1000f / avg);
    }
    @Override
    public String toString() {
        return "[RenderProfiler] "
                + "avg=" + String.format("%.2f", getAvgMs()) + "ms"
                + " | max=" + String.format("%.2f", getMaxMs()) + "ms"
                + " | FPS=" + getFPS()
                + " | samples=" + samples.size;
    }

    private static class Sample {
        final float ms;
        final long time;
        Sample(float ms, long time) {
            this.ms = ms;
            this.time = time;
        }
    }

}
