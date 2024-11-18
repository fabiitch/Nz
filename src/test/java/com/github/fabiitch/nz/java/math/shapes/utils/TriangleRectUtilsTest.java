package com.github.fabiitch.nz.java.math.shapes.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.nz.java.math.shapes.utils.TriangleRectUtils.hypoFromAdjacent;

public class TriangleRectUtilsTest {

    private final static float TOLERANCE = 1f;

    /**
     * result from https://www.calculat.org/fr/aire-perimetre/triangle-rectangle.html
     */
    @Test
    public void hypoFromAdjacentTest() {
        Assertions.assertEquals(14.14f, hypoFromAdjacent(10, 45), TOLERANCE);
        Assertions.assertEquals(25.56f, hypoFromAdjacent(25, 12), TOLERANCE);
        Assertions.assertEquals(25.56f, hypoFromAdjacent(25, 12), TOLERANCE);
        Assertions.assertEquals(190.04f, hypoFromAdjacent(33, 80), TOLERANCE);
    }
}
