package com.fabiitch.nz.java.math.utils;

public class PercentageGiver {

    public float total;

    public PercentageGiver(float total) {
        this.total = total;
    }

    public float value(float percent) {
        return Percentage.getValue(percent, total);
    }

    public float getPercent(float value) {
        return Percentage.getPercent(value, total);
    }

    public float getAlpha(float value) {
        return Percentage.getAlpha(value, total);
    }
}
