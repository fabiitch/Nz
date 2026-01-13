package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Arrays;

public class DTProfiler {

    private final String name;

    private final Array<Sample> samples = new Array<>(true, 256);
    private final float windowSeconds;

    private float[] sortBuffer = new float[0];
    private long frameStart;

    private final Pool<Sample> samplePool = new Pool<Sample>(256) {
        @Override
        protected Sample newObject() {
            return new Sample();
        }
    };
    public DTProfiler(String name, float windowSeconds) {
        this.name = name;
        this.windowSeconds = windowSeconds;
    }

    public DTProfiler(float windowSeconds) {
        this(null, windowSeconds);
    }

    public void begin() {
        frameStart = System.nanoTime();
    }

    public void end() {
        long now = System.nanoTime();
        float ms = (now - frameStart) / 1_000_000f;

        Sample s = samplePool.obtain();
        s.ms = ms;
        s.time = now;
        samples.add(s);

        // purge fenêtre glissante
        long minTime = now - (long) (windowSeconds * 1_000_000_000L);
        while (samples.size > 0 && samples.first().time < minTime) {
            Sample old = samples.removeIndex(0);
            samplePool.free(old);
        }
    }

    public float getAvgMs() {
        if (samples.size == 0) return 0f;
        float sum = 0f;
        for (int i = 0; i < samples.size; i++) {
            sum += samples.get(i).ms;
        }
        return sum / samples.size;
    }

    public float getMaxMs() {
        float max = 0f;
        for (int i = 0; i < samples.size; i++) {
            float v = samples.get(i).ms;
            if (v > max) max = v;
        }
        return max;
    }

    /** p en [0..100] */
    public float getPercentileMs(int p) {
        if (samples.size == 0) return 0f;

        int pp = Math.max(0, Math.min(100, p));
        int n = samples.size;

        if (sortBuffer.length < n) {
            sortBuffer = new float[n];
        }

        for (int i = 0; i < n; i++) {
            sortBuffer[i] = samples.get(i).ms;
        }

        Arrays.sort(sortBuffer, 0, n);

        if (pp == 0) return sortBuffer[0];
        if (pp == 100) return sortBuffer[n - 1];

        int k = (int) Math.ceil((pp / 100.0) * n);
        int idx = Math.max(0, Math.min(n - 1, k - 1));

        return sortBuffer[idx];
    }

    public float getP50Ms() { return getPercentileMs(50); }
    public float getP95Ms() { return getPercentileMs(95); }
    public float getP99Ms() { return getPercentileMs(99); }

    public int getFPS() {
        float avg = getAvgMs();
        return avg == 0f ? 0 : (int) (1000f / avg);
    }

    @Override
    public String toString() {
        return "[DTProfiler=" + name + "] "
                + "avg=" + String.format("%.2f", getAvgMs()) + "ms"
                + " p95=" + String.format("%.2f", getP95Ms()) + "ms"
                + " p99=" + String.format("%.2f", getP99Ms()) + "ms"
                + " max=" + String.format("%.2f", getMaxMs()) + "ms"
                + " FPS=" + getFPS()
                + " samples=" + samples.size;
    }

    private static class Sample implements Pool.Poolable {
        float ms;
        long time;

        @Override
        public void reset() {
            ms = 0f;
            time = 0L;
        }
    }
}
