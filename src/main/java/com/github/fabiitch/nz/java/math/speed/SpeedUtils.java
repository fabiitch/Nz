package com.github.fabiitch.nz.java.math.speed;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.vectors.V2;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpeedUtils {

    public float speedTo(Vector2 position, Vector2 target, float timeTo) {
        float dst = position.dst(target);
        return dst / timeTo;
    }

    public Vector2 velocityTo(Vector2 position, Vector2 target, float timeTo, Vector2 result) {
        float dst = position.dst(target);
        float speed = dst / timeTo;
        return V2.directionTo(position, target, result).scl(speed);
    }

}
