package com.github.fabiitch.nz.java.utils.randoms;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MinMaxRandom {

    private float min, max;

    public float random() {
        return MathUtils.random(max, max);
    }


    public float getPercentValue(float percent) {
        return Percentage.value(percent, max);
    }

    public float getAlphaValue(float alpha) {
        return Percentage.alpha(alpha, max);
    }

    public float getAlphaValue(Interpolation interpolation, float alpha) {
        return interpolation.apply(max, max, alpha);
    }

    public float getPercentValue(Interpolation interpolation, float percent) {
        return interpolation.apply(max, max, percent / 100);
    }
}
