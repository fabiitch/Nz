package com.fabiitch.nz.math;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MathTestUtils {

    public static Circle c(float radius) {
        return new Circle(0, 0, radius);
    }

    public static Circle c(float x, float y, float radius) {
        return new Circle(x, y, radius);
    }

    public static Rectangle r(float x, float y, float witdh, float height) {
        return new Rectangle(x, y, witdh, height);
    }

    public static Rectangle r(float witdh, float height) {
        return r(0, 0, witdh, height);
    }

    public static Rectangle r() {
        return r(0, 0, 0, 0);
    }

//    public static Segment s(float aX, float aY, float bX, float bY) {
//        return new Segment(aX, aY, bX, bY);
//    }

    public Vector2 v2() {
        return new Vector2();
    }

    public static Vector2 v2(float a, float b) {
        return new Vector2(a, b);
    }
}
