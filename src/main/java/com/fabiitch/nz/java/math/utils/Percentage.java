package com.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Vector2;

public class Percentage {

    private Percentage() {

    }

//	pourcentage = (montant partiel / montant total) x 100

    /**
     * @param part  montant partiel
     * @param total montant total
     * @return number 0-100
     */
    public static float getPercent(float part, float total) {
        return part / total * 100;
    }

    public static float getAlpha(float part, float total) {
        return part / total;
    }

    public static float getValue(float percent, float total) {
        return percent * total / 100;
    }


    public static Vector2 getValue(Vector2 percent, float total, Vector2 result) {
        result.x = getValue(percent.x, total);
        result.y = getValue(percent.y, total);
        return result;
    }

    public static float addXPercentTo(float percent, float total) {
        return total + (total * percent / 100);
    }

    public static float removeXPercentTo(float percent, float total) {
        return total - total * percent / 100;
    }

}
