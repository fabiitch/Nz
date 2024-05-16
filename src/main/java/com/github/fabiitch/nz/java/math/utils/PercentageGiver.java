package com.github.fabiitch.nz.java.math.utils;

public class PercentageGiver {

    public float total;

    public PercentageGiver(float total) {
        this.total = total;
    }

    public float value(float percent) {
        return Percentage.value(percent, total);
    }

    public float getPercent(float value) {
        return Percentage.percentage(value, total);
    }

    public float getAlpha(float value) {
        return Percentage.alpha(value, total);
    }
}
