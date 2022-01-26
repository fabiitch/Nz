package com.fabiitch.nz.math.utils;

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
        return part * 100 / total;
    }

    public static float getAlpha(float part, float total) {
        return part / total;
    }

    public static float getValue(float percent, float total) {
        return percent * total / 100;
    }

    public static float addXPercentTo(float percent, float total) {
        return total + (total * percent / 100);
    }

    public static float removeXPercentTo(float percent, float total) {
        return total - total * percent / 100;
    }

}
