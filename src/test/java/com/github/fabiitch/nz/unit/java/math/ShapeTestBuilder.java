package com.github.fabiitch.nz.unit.java.math;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShapeTestBuilder {

    public static Circle c(float radius) {
        return new Circle(0, 0, radius);
    }

    public static Circle c(float x, float y, float radius) {
        return new Circle(x, y, radius);
    }

    public static Rectangle rect(float x, float y, float witdh, float height) {
        return new Rectangle(x, y, witdh, height);
    }

    public static Rectangle rect(Vector2 pos, float witdh, float height) {
        return new Rectangle(pos.x, pos.y, witdh, height);
    }

    public static Rectangle rect(float witdh, float height) {
        return rect(0, 0, witdh, height);
    }

    public static Rectangle rect() {
        return rect(0, 0, 0, 0);
    }

    public static Vector2 v2() {
        return new Vector2();
    }

    public static Vector2 v2(float a, float b) {
        return new Vector2(a, b);
    }
}
