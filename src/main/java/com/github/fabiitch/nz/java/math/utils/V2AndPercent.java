package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Vector2;

public class V2AndPercent {

    private PercentageGiver xGiver, yGiver;
    private Vector2 percents;
    private Vector2 value = new Vector2();

    public V2AndPercent(float percentX, float percentY) {
        percents = new Vector2(percentX, percentY);
        xGiver = new PercentageGiver(0);
        yGiver = new PercentageGiver(0);
    }

    public V2AndPercent(float x, float y, PercentageGiver xGiver, PercentageGiver yGiver) {
        this.percents = new Vector2(x, y);
        this.xGiver = xGiver;
        this.yGiver = yGiver;
    }

    public V2AndPercent(Vector2 percents, PercentageGiver xGiver, PercentageGiver yGiver) {
        this.percents = percents;
        this.xGiver = xGiver;
        this.yGiver = yGiver;
    }
    public Vector2 getPercents() {
        return this.percents;
    }

    public Vector2 getValues() {
        return this.value.set(xGiver.value(percents.x), yGiver.value(percents.y));
    }

    public void setTotal(float totalX, float totalY) {
        setTotalX(totalX);
        setTotalY(totalY);
    }

    public void setTotalX(float totalX) {
        xGiver.total = totalX;
    }

    public void setTotalY(float totalY) {
        yGiver.total = totalY;
    }


}
