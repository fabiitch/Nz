package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.MathUtils;

public class TriangleRectUtils {

    /**
     * B
     * |
     * |■
     * A-----C
     * <p>
     * cosB = coté adjacent/hypothénuse
     * sinB = coté opposé/hypothénuse
     */
    public static float hypoFromAdjacent(float ajdacentLen, float betaDeg) {
        //cosB = ajdacentLen/hypo
        return ajdacentLen / MathUtils.cosDeg(betaDeg);
    }
}
