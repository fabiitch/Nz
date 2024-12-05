package com.github.fabiitch.nz.java.math.percent;

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
    public static float percentage(float part, float total) {
        return part / total * 100;
    }

    public static float alpha(float part, float total) {
        return part / total;
    }

    public static float value(float percent, float total) {
        return percent * total / 100;
    }

    public static Vector2 value(Vector2 percent, float total, Vector2 result) {
        result.x = value(percent.x, total);
        result.y = value(percent.y, total);
        return result;
    }

    public static float addPercentage(float percent, float total) {
        return total + (total * percent / 100);
    }

    public static float removePercentage(float percent, float total) {
        return total - total * percent / 100;
    }

    public static float alphaBound(float part, float total) {
        return Math.min(1, part / total);
    }
}
