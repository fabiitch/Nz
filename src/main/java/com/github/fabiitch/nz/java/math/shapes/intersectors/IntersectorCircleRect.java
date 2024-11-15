package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntersectorCircleRect {
    private static float internalDst(Rectangle r, Circle c) {
        float closestX = c.x;
        float closestY = c.y;

        if (c.x < r.x) {
            closestX = r.x;
        } else if (c.x > r.x + r.width) {
            closestX = r.x + r.width;
        }

        if (c.y < r.y) {
            closestY = r.y;
        } else if (c.y > r.y + r.height) {
            closestY = r.y + r.height;
        }

        closestX = closestX - c.x;
        closestX *= closestX;
        closestY = closestY - c.y;
        closestY *= closestY;

        return closestX + closestY;
    }

    public static boolean overlap(Rectangle r, Circle c) {
        return internalDst(r, c) < c.radius * c.radius;
    }
    public static boolean overlapStick(Rectangle r, Circle c) {
        return internalDst(r, c) <= c.radius * c.radius;
    }
}
