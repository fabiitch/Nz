package com.github.fabiitch.nz.java.math.speed.drive.angular;

import com.badlogic.gdx.math.Interpolation;
import com.github.fabiitch.nz.java.math.percent.Percentage;
import com.github.fabiitch.nz.java.math.speed.drive.AngularModifier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AngularInterpolationSpeed implements AngularModifier {
    private float minAngle, maxAngle;

    private float maxSpeed;
    private Interpolation interpolation;

    @Override
    public float modifyAngular(float angleRequest, float speed) {
        return interpolation.apply(maxAngle, minAngle, Percentage.alphaBound(speed, maxSpeed));
    }
}
