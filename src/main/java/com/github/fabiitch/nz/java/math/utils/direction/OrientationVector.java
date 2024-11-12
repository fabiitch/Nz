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
        addHorizontal(xy.x);
        addVertical(xy.y);
    }

    public static OrientationVector tmp(Orientation orientation, float orientationValue, float otherOrientationValue) {
        return TMP.set(orientation, orientationValue, otherOrientationValue);
    }


    public float get(Orientation orientation) {
        if (orientation == this.orientation)
            return orientationValue;
        return otherOrientationValue;
    }

    public OrientationVector addHorizontal(float x) {
        if (orientation == Orientation.Horizontal)
            orientationValue += x;
        else
            otherOrientationValue += x;
        return this;
    }

    public OrientationVector addVertical(float y) {
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

    public OrientationVector add(Orientation orientation, float value) {
        if (orientation == this.orientation)
            orientationValue += value;
        else
            otherOrientationValue += value;
        return this;
    }

    public Vector2 addToV2(Orientation orientation, Vector2 vector) {
        float value = get(orientation);
        if (orientation == Orientation.Horizontal)
            vector.x += value;
        else
            vector.y = value;
        return vector;
    }

    public Vector2 addToV2(Vector2 v) {
        return v.add(get(Orientation.Horizontal), get(Orientation.Vertical));
    }

    public Vector2 toV2() {
        float x = get(Orientation.Horizontal);
        float y = get(Orientation.Vertical);
        return new Vector2(x, y);
    }

    public OrientationVector cpy() {
        return new OrientationVector(orientation, orientationValue, otherOrientationValue);
    }

    public OrientationVector set(OrientationVector orientationVector) {
        this.orientation = orientationVector.orientation;
        this.orientationValue = orientationVector.orientationValue;
        this.otherOrientationValue = orientationVector.otherOrientationValue;
        return this;
    }

    public OrientationVector set(Orientation orientation, float orientationValue, float otherOrientationValue) {
        this.orientation = orientation;
        this.orientationValue = orientationValue;
        this.otherOrientationValue = otherOrientationValue;
        return this;
    }

    @Override
    public void reset() {
        orientation = null;
        orientationValue = 0;
        otherOrientationValue = 0;
    }
}
