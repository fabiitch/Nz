package com.github.fabiitch.nz.java.math.shape.utils;

import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.vectors.VTestUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.*;
import static com.github.fabiitch.nz.java.math.shapes.utils.SegmentUtils.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SegmentUtilsTest {

    private static final float TOLERANCE = 0.01f;

    private static Segment s(Vector2 a, Vector2 b) {
        return new Segment(a, b);
    }

    private static Segment s(float aX, float aY, float bX, float bY) {
        return new Segment(aX, aY, bX, bY);
    }

    //http://www.ambrsoft.com/MathCalc/Line/TwoLinesIntersection/TwoLinesIntersection.htm
    @Test
    public void getSegmentIntersectionTest1() {
        Vector2 intersection = new Vector2();
        Segment s1 = s(0, 0, 100, 0);
        Segment s2 = s(50, 50, 50, -50);
        boolean intersect = getSegmentIntersection(s1, s2, intersection);

        assertTrue(intersect);
        VTestUtils.assertEquals(50, 0, intersection);
    }

    @Test
    public void getSegmentIntersectionTest2() {
        Vector2 intersection = new Vector2();
        Segment s1 = s(20, -20, 150, 200);
        Segment s2 = s(10, 150, 250, 10);
        boolean intersect = getSegmentIntersection(s1, s2, intersection);

        assertTrue(intersect);
        VTestUtils.assertEquals(92.14f, 102.08f, intersection, TOLERANCE);
    }

    @Test
    public void getSegmentIntersectionTest3() {
        Vector2 intersection = new Vector2();
        Segment s1 = s(0, 0, 50, 0);
        Segment s2 = s(0, 10, 0, 50);
        boolean intersect = getSegmentIntersection(s1, s2, intersection);

        assertFalse(intersect);
        VTestUtils.assertEquals(0, 0, intersection, TOLERANCE);
    }

    @Test
    public void getAngleReflexionTest1() {
        Segment s1 = s(50, 0, 50, 50);
        Vector2 direction = v2(1, 0);
        float angleReflexion = getAngleReflexionDeg(s1, direction);
        Assertions.assertEquals(0, angleReflexion, 0.1f);
    }

    @Test
    public void getAngleReflexionTest2() {
        Segment s1 = s(0, 0, 50, 0);
        Vector2 direction = v2(1, 1).nor();
        float angleReflexion = getAngleReflexionDeg(s1, direction);
        Assertions.assertEquals(135f, angleReflexion, 0.1f);
    }


    @Test
    public void minDistanceTest() {
        Segment s1, s2;
        //parralel
        s1 = s(0, 0, 50, 0);
        s2 = s(-50, -50, 0, -50);
        Assertions.assertEquals(50, dstMin(s1, s2));

        //intersect
        s1 = s(0, 0, 50, 0);
        s2 = s(25, 25, 0, -50);
        Assertions.assertEquals(0, dstMin(s1, s2));


        //perpendiculary not intersect
        s1 = s(0, 0, 50, 0);
        s2 = s(25, 100, 0, 10);
        Assertions.assertEquals(10, dstMin(s1, s2));

        //random
        s1 = s(0, 0, 50, 0);
        s2 = s(-50, 100, 20, 10);
        Assertions.assertEquals(10, dstMin(s1, s2));
    }


}
