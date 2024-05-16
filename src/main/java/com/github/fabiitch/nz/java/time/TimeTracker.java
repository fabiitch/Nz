package com.github.fabiitch.nz.java.time;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;

public class TimeTracker implements Pool.Poolable {
    private int countValues;

    private long minTime = Long.MAX_VALUE;
    private long maxTime = Long.MIN_VALUE;
    private long averageTime;

    private long lastValue;

    private long startNanoTime;
    private long accumulator;

    private boolean running;

    public void start() {
        startNanoTime = TimeUtils.nanoTime();
        running = true;
    }

    public void end() {
        long stopTime = System.nanoTime();
        lastValue = stopTime - startNanoTime + accumulator;

        minTime = Math.min(minTime, lastValue);
        maxTime = Math.max(maxTime, lastValue);

        averageTime = (averageTime + lastValue) / 2;
        countValues++;

        accumulator = 0;
        running = false;
    }

    public void pause() {
        long stopTime = System.nanoTime();
        accumulator += stopTime - startNanoTime;
        running = false;
    }

    public String logSeconds() {
        return "current= " + getSeconds() + " | average=" + getAverageSeconds() + " | min=" + getMinSeconds() + " | max=" + getMaxSeconds() + " [s]";
    }

    public String logMillis() {
        return "current= " + getMillis() + " | average=" + getAverageMillis() + " | min=" + getMinMillis() + " | max=" + getMaxMillis()+ " [ms]";
    }

    public String logNano() {
        return "current= " + getNano() + " | average=" + getAverageNano() + " | min=" + getMinNano() + " | max=" + getMaxNano()+ " [,s]";
    }

    public float getSeconds() {
        return TimeConverter.nanoToSecond(lastValue);
    }

    public long getMillis() {
        return TimeConverter.nanoToMillis(lastValue);
    }

    public long getNano() {
        return lastValue;
    }

    public float getMinSeconds() {
        return TimeConverter.nanoToSecond(minTime);
    }

    public long getMinMillis() {
        return TimeConverter.nanoToMillis(minTime);
    }

    public long getMinNano() {
        return minTime;
    }

    public float getMaxSeconds() {
        return TimeConverter.nanoToSecond(maxTime);
    }

    public long getMaxMillis() {
        return TimeConverter.nanoToMillis(maxTime);
    }

    public long getMaxNano() {
        return maxTime;
    }

    public float getAverageSeconds() {
        return TimeConverter.nanoToSecond(averageTime);
    }

    public long getAverageMillis() {
        return TimeConverter.nanoToMillis(averageTime);
    }

    public long getAverageNano() {
        return averageTime;
    }

    public int getCountValues() {
        return countValues;
    }


    @Override
    public void reset() {
        accumulator = 0;
        countValues = 0;
        averageTime = 0;
        minTime = 0;
        maxTime = 0;
    }
}
