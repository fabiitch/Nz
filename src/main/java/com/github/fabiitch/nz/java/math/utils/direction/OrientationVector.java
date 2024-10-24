package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrientationVector implements Pool.Poolable {
    private Orientation orientation;
    private float orientationValue, otherOrientationValue;

    private static OrientationVector TMP = new OrientationVector();

    public OrientationVector(Orientation orientation, Vector2 xy) {
        this.orientation = orientation;
        addX(xy.x);
        addY(xy.y);
    }

    public OrientationVector tmp(Orientation orientation, float orientationValue, float otherOrientationValue) {
        return TMP.set(orientation, orientationValue, otherOrientationValue);
    }

    public OrientationVector cpy() {
        return new OrientationVector(orientation, orientationValue, otherOrientationValue);
    }

    public OrientationVector set(Orientation orientation, float orientationValue, float otherOrientationValue) {
        this.orientation = orientation;
        this.orientationValue = orientationValue;
        this.otherOrientationValue = otherOrientationValue;
        return this;
    }

    public OrientationVector addX(float x) {
        if (orientation == Orientation.Horizontal)
            orientationValue += x;
        else
            otherOrientationValue += x;
        return this;
    }

    public OrientationVector addY(float y) {
        if (orientation == Orientation.Horizontal)
            otherOrientationValue += y;
        else
            orientationValue += y;
        return this;
    }


    public OrientationVector add(float a, float b) {
        orientationValue += a;
        otherOrientationValue += b;
        return this;
    }

    public Vector2 toV2() {
        float x, y;
        if (orientation == Orientation.Horizontal) {
            x = orientationValue;
            y = otherOrientationValue;
        } else {
            x = otherOrientationValue;
            y = orientationValue;
        }
        return new Vector2(x, y);
    }

    @Override
    public void reset() {
        orientation = null;
        orientationValue = 0;
        otherOrientationValue = 0;
    }
}
