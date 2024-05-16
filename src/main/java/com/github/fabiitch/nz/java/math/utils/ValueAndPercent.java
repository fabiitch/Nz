package com.github.fabiitch.nz.java.math.utils;


//TODO poolable
public class ValueAndPercent {

    private float percent;
    private PercentageGiver percentageGiver;

    public ValueAndPercent(float percent, PercentageGiver percentageGiver) {
        this.percent = percent;
        this.percentageGiver = percentageGiver;
    }

    public float value() {
        return percentageGiver.value(percent);
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

}
