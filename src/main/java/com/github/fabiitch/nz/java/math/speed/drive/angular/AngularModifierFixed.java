package com.github.fabiitch.nz.java.math.speed.drive.angular;

import com.github.fabiitch.nz.java.math.speed.drive.AngularModifier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AngularModifierFixed implements AngularModifier {

    private float maxAngle;

    @Override
    public float modifyAngular(float angleRequest, float speed) {
        return Math.min(angleRequest, maxAngle);
    }
}
