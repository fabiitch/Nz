package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentageGiverV2 {

    public float totalX;
    public float totalY;

    public PercentageGiverV2(float totalX, float totalY) {
        this.totalX = totalX;
        this.totalY = totalY;
    }

    public PercentageGiverV2(Vector2 vector2) {
        this.totalX = vector2.x;
        this.totalY = vector2.y;
    }

    public Vector2 getValue(float percentX, float percentY) {
        return getValue(percentX, percentY, new Vector2());
    }

    public Vector2 getValue(float percentX, float percentY, Vector2 result) {
        return result.set(Percentage.value(percentX, totalX), Percentage.value(percentY, totalY));
    }

    public Vector2 getValue(Vector2 percent) {
        return getValue(percent.x, percent.y, new Vector2());
    }

    public Vector2 getValue(Vector2 percent, Vector2 result) {
        return getValue(percent.x, percent.y, result);
    }
}
