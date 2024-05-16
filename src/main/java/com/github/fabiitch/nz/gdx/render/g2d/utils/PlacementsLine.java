package com.github.fabiitch.nz.gdx.render.g2d.utils;

public class PlacementsLine {

    private PlacementsLine() {

    }

    /**
     * Remplit l'espace restant avec le spacebetween et margin
     */
    public static float[] posMiddle(int numberObject, float totalWidth, float widthObject) {
        float spaceBetween;
        float marginRight;
        spaceBetween = marginRight = (totalWidth - widthObject * numberObject) / (numberObject + 1);
        return getPosMiddleAll(numberObject, widthObject, 0, spaceBetween, marginRight);
    }

    /**
     * Remplit l'espace restant avec le spacebetween et margin
     */
    public static float[] posMiddle(int numberObject, float totalWidth, float startX, float widthObject) {
        float spaceBetween;
        float marginRight;
        spaceBetween = marginRight = (totalWidth - widthObject * numberObject) / (numberObject + 1);
        return getPosMiddleAll(numberObject, widthObject, startX, spaceBetween, marginRight);
    }

    private static float[] getPosMiddleAll(int numberObject, float widthObject, float startX, float spaceBetween,
                                           float marginRight) {
        float[] result = new float[numberObject];
        for (int i = 0; i < numberObject; i++) {
            float x = startX + marginRight + widthObject * i + spaceBetween * i + widthObject / 2;
            result[i] = x;
        }
        return result;
    }

}
