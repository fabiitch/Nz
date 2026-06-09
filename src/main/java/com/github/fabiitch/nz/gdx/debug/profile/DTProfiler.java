package com.github.fabiitch.nz.gdx.debug.profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Arrays;
import java.util.Locale;

public class DTProfiler {

    private final String name;
    private final float windowSeconds;

    private final Array<Sample> samples = new Array<Sample>(true, 512);
    private final Pool<Sample> samplePool = new Pool<Sample>(512) {
        @Override
        protected Sample newObject() {
            return new Sample();
        }
    };

    private float[] sortBuffer = new float[512];
    private long frameStartNs;
    private boolean running;

    public DTProfiler(String name, float windowSeconds) {
        this.name = name;
        this.windowSeconds = windowSeconds;
    }

    public DTProfiler(float windowSeconds) {
        this(null, windowSeconds);
    }

    public void begin() {
        frameStartNs = System.nanoTime();
        running = true;
    }

    public void end() {
        if (!running) {
            return;
        }

        long nowNs = System.nanoTime();
        float ms = (nowNs - frameStartNs) / 1_000_000f;

        running = false;

        Sample sample = samplePool.obtain();
        sample.ms = ms;
        sample.timeNs = nowNs;
        samples.add(sample);

        purgeOldSamples(nowNs);
    }

    private void purgeOldSamples(long nowNs) {
        if (samples.size == 0) {
            return;
        }

        long minTimeNs = nowNs - (long) (windowSeconds * 1_000_000_000L);

        int removeCount = 0;
        while (removeCount < samples.size && samples.get(removeCount).timeNs < minTimeNs) {
            samplePool.free(samples.get(removeCount));
            removeCount++;
        }

        if (removeCount > 0) {
            samples.removeRange(0, removeCount - 1);
        }
    }

    public void clear() {
        for (int i = 0; i < samples.size; i++) {
            samplePool.free(samples.get(i));
        }

        samples.clear();
        running = false;
        frameStartNs = 0L;
    }

    public int getSampleCount() {
        return samples.size;
    }

    public float getAvgMs() {
        return getStats().getAvgMs();
    }

    public float getMaxMs() {
        return getStats().getMaxMs();
    }

    public float getP50Ms() {
        return getStats().getP50Ms();
    }

    public float getP95Ms() {
        return getStats().getP95Ms();
    }

    public float getP99Ms() {
        return getStats().getP99Ms();
    }

    public Stats getStats() {
        int n = samples.size;

        if (n == 0) {
            return Stats.empty();
        }

        ensureSortBufferCapacity(n);

        float sum = 0f;
        float max = 0f;

        for (int i = 0; i < n; i++) {
            float ms = samples.get(i).ms;

            sortBuffer[i] = ms;
            sum += ms;

            if (ms > max) {
                max = ms;
            }
        }

        Arrays.sort(sortBuffer, 0, n);

        return new Stats(
                sum / n,
                percentileFromSorted(sortBuffer, n, 50),
                percentileFromSorted(sortBuffer, n, 95),
                percentileFromSorted(sortBuffer, n, 99),
                max,
                n
        );
    }

    private void ensureSortBufferCapacity(int requiredSize) {
        if (sortBuffer.length >= requiredSize) {
            return;
        }

        int newSize = sortBuffer.length;
        while (newSize < requiredSize) {
            newSize *= 2;
        }

        sortBuffer = new float[newSize];
    }

    private static float percentileFromSorted(float[] sorted, int n, int percentile) {
        if (n == 0) {
            return 0f;
        }

        int p = Math.max(0, Math.min(100, percentile));

        if (p == 0) {
            return sorted[0];
        }

        if (p == 100) {
            return sorted[n - 1];
        }

        int k = (int) Math.ceil((p / 100.0) * n);
        int index = Math.max(0, Math.min(n - 1, k - 1));

        return sorted[index];
    }

    @Override
    public String toString() {
        Stats stats = getStats();

        return "[DTProfiler=" + name + "] "
                + "avg=" + formatMs(stats.getAvgMs()) + "ms"
                + " p50=" + formatMs(stats.getP50Ms()) + "ms"
                + " p95=" + formatMs(stats.getP95Ms()) + "ms"
                + " p99=" + formatMs(stats.getP99Ms()) + "ms"
                + " max=" + formatMs(stats.getMaxMs()) + "ms"
                + " samples=" + stats.getSamples();
    }

    private static String formatMs(float value) {
        return String.format(Locale.US, "%.2f", value);
    }

    public static final class Stats {

        private final float avgMs;
        private final float p50Ms;
        private final float p95Ms;
        private final float p99Ms;
        private final float maxMs;
        private final int samples;

        private Stats(float avgMs, float p50Ms, float p95Ms, float p99Ms, float maxMs, int samples) {
            this.avgMs = avgMs;
            this.p50Ms = p50Ms;
            this.p95Ms = p95Ms;
            this.p99Ms = p99Ms;
            this.maxMs = maxMs;
            this.samples = samples;
        }

        public static Stats empty() {
            return new Stats(0f, 0f, 0f, 0f, 0f, 0);
        }

        public float getAvgMs() {
            return avgMs;
        }

        public float getP50Ms() {
            return p50Ms;
        }

        public float getP95Ms() {
            return p95Ms;
        }

        public float getP99Ms() {
            return p99Ms;
        }

        public float getMaxMs() {
            return maxMs;
        }

        public int getSamples() {
            return samples;
        }
    }

    private static class Sample implements Pool.Poolable {
        float ms;
        long timeNs;

        @Override
        public void reset() {
            ms = 0f;
            timeNs = 0L;
        }
    }
}