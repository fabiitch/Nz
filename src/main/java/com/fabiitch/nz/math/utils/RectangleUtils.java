package com.fabiitch.nz.math.utils;

import com.badlogic.gdx.math.Rectangle;

public class RectangleUtils {

    public static boolean containsStick(Rectangle rectA, Rectangle rectB) {
        float xMinA = rectA.x, xMaxA = xMinA + rectA.width;
        float yMinA = rectA.y, yMaxA = yMinA + rectA.height;

        float xMinB = rectB.x, xMaxB = xMinB + rectB.width;
        float yMinB = rectB.y, yMaxB = yMinB + rectB.height;

        return ((xMinB >= xMinA && xMinB <= xMaxA) && (xMaxB >= xMinA && xMaxB <= xMaxA))
                && ((yMinB >= yMinA && yMinB <= yMaxA) && (yMaxB >= yMinA && yMaxB <= yMaxA));
    }
}
