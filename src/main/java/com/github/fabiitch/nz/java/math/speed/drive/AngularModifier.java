package com.github.fabiitch.nz.java.math.speed.drive;

public interface AngularModifier {

    float modifyAngular(float angleRequest, float speed);


    default float modifyAngular(float dt, float angleRequest, float speed) {
        return dt * modifyAngular(angleRequest, speed);
    }
}
