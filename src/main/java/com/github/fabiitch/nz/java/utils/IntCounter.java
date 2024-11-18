package com.github.fabiitch.nz.java.utils;

import com.badlogic.gdx.utils.Pool;

public class IntCounter implements Pool.Poolable {
    /**
     * The amount of values added
     */
    public int count;
    /**
     * The sum of all values
     */
    public int total;
    /**
     * The smallest value
     */
    public int min;
    /**
     * The largest value
     */
    public int max;
    /**
     * The average value (total / count)
     */
    public int average;
    /**
     * The latest raw value
     */
    public int latest;

    /**
     * current value
     */
    public int currentValue;

    public void put(int value) {
        if (value > 0 && total > Integer.MAX_VALUE - value || count == Integer.MAX_VALUE) {
            reset();
        }
        latest = value;
        total += value;
        count++;
        average = total / count;

        if (value < min) min = value;
        if (value > max) max = value;

        this.currentValue = 0;
    }

    @Override
    public void reset() {
        count = 0;
        total = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        average = 0;
        latest = 0;
        currentValue = 0;
    }

    public String toStringValueAverage(String fieldName) {
        return fieldName + ": " + "value=" + currentValue + ", average=" + average;
    }

    public String toStringValueAverage() {
        return "current=" + currentValue + ", average=" + average;
    }

    public String toString(String fieldName) {
        return fieldName + "{" +
                "count=" + count +
                ", total=" + total +
                ", min=" + min +
                ", max=" + max +
                ", average=" + average +
                ", latest=" + latest +
                ", value=" + currentValue +
                '}';
    }

    @Override
    public String toString() {
        return "IntCounter{" +
                "count=" + count +
                ", total=" + total +
                ", min=" + min +
                ", max=" + max +
                ", average=" + average +
                ", latest=" + latest +
                ", value=" + currentValue +
                '}';
    }

    public void putCurrent() {
        put(this.currentValue);
    }
}
