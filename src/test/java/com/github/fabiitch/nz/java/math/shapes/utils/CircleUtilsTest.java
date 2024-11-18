package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.vectors.VTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.c;
import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.v2;
import static com.github.fabiitch.nz.java.math.shapes.utils.CircleUtils.*;


public class CircleUtilsTest {
    private final static Vector2 tmp = new Vector2();
    private final static float TOLERANCE = 0.001f;

    @Test
    public void getCenterTest() {
        Circle circle = c(50, 50, 50);
        Vector2 center = getCenter(circle, tmp);
        VTestUtils.assertEquals(center, v2(50, 50));
    }

    @Test
    public void posWithAngleDegTest() {

        Circle circle = c(0, 0, 50);
        Vector2 pos;

        pos = posWithAngleDeg(circle, 0, tmp);
        VTestUtils.assertEquals(v2(50, 0), pos, TOLERANCE);

        pos = posWithAngleDeg(circle, 90, tmp);
        VTestUtils.assertEquals(v2(0, 50), pos, TOLERANCE);

        pos = posWithAngleDeg(circle, 180, tmp);
        VTestUtils.assertEquals(v2(-50, 0), pos, TOLERANCE);


        pos = posWithAngleDeg(circle, 270, tmp);
        VTestUtils.assertEquals(v2(0, -50), pos, TOLERANCE);

        pos = posWithAngleDeg(circle, 360, tmp);
        VTestUtils.assertEquals(v2(50, 0), pos, TOLERANCE);
    }

    @Test
    public void getTangentTest() {
        Circle circle = c(0, 0, 50);
        Vector2 tangent;

        tangent = getTangentDeg(circle, 0, tmp);
        VTestUtils.assertEquals(v2(0, 1), tangent, TOLERANCE);

        tangent = getTangentDeg(circle, 90, tmp);
        VTestUtils.assertEquals(v2(-1, 0), tangent, TOLERANCE);

        tangent = getTangentDeg(circle, 180, tmp);
        VTestUtils.assertEquals(v2(0, -1), tangent, TOLERANCE);

        tangent = getTangentDeg(circle, 270, tmp);
        VTestUtils.assertEquals(v2(1, 0), tangent, TOLERANCE);
    }


    @Test
    public void dirFromCenterTest() {
        Circle circle = c(0, 0, 50);
        Vector2 normal;
        normal = dirFromCenter(circle, v2(50, 0), v2());
        VTestUtils.assertEquals(v2(1, 0), normal);

        normal = dirFromCenter(circle, 0, v2());
        VTestUtils.assertEquals(v2(1, 0), normal);

        normal = dirFromCenter(circle, v2(0, 5), v2());
        VTestUtils.assertEquals(v2(0, 1), normal);

        normal = dirFromCenter(circle, 90, v2());
        VTestUtils.assertEquals(v2(0, 1), normal);
    }

    @Test
    public void dirToCenterTest() {
        Circle circle = c(0, 0, 50);
        Vector2 normal;
        normal = dirToCenter(circle, v2(50, 0), v2());
        VTestUtils.assertEquals(v2(-1, 0), normal);

        normal = dirToCenter(circle, 0, v2());
        VTestUtils.assertEquals(v2(-1, 0), normal);

        normal = dirToCenter(circle, v2(0, 5), v2());
        VTestUtils.assertEquals(v2(0, -1), normal);

        normal = dirToCenter(circle, 90, v2());
        VTestUtils.assertEquals(v2(0, -1), normal);
    }

    @Test
    public void getRectBoundsTest() {
        Circle circle = c(0, 0, 100);
        Rectangle rectBounds = getRectBounds(circle, new Rectangle());
        Assertions.assertEquals(new Rectangle(-100, -100, 200, 200), rectBounds);

        circle = c(200, 200, 200);
        rectBounds = getRectBounds(circle, new Rectangle());
        Assertions.assertEquals(new Rectangle(0, 0, 400, 400), rectBounds);
    }

    @Test
    public void getRandomPosTest(){
        Circle circle = c(0, 0, 100);
        for(int i = 0; i < 100 ; i++){
            Assertions.assertTrue(circle.contains(CircleUtils.getRandomPos(circle, v2())));
        }
    }
}
