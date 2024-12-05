package com.github.fabiitch.nz.java.math.vectors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.gdxunit.vectors.VTestUtils;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.v2;


public class V2Test {

    @Test
    public void isZeroTest() {
        Assertions.assertTrue(V2.isZero(v2()));
        Assertions.assertTrue(V2.isZero(v2(MathUtils.FLOAT_ROUNDING_ERROR / 2, MathUtils.FLOAT_ROUNDING_ERROR / 2)));
        Assertions.assertTrue(V2.isZero(v2(0.1f, 0.1f), 0.1f));

        Assertions.assertFalse(V2.isZero(v2(1, 0)));
        Assertions.assertFalse(V2.isZero(v2(0.11f, 0.11f), 0.1f));
    }

    @Test
    public void getClosestTest() {
        Vector2 start = v2(0, 0);
        Vector2 v1 = v2(8, 2);
        Vector2 v2 = v2(10, 0);
        Vector2 v3 = v2(5, 0);
        Array<Vector2> vector2Array = new Array<>();
        vector2Array.add(v1, v2, v3, null);

        Vector2 closest = V.getClosest(start, vector2Array);
        Assertions.assertEquals(closest, v3);
    }

    @Test
    public void getMiddleTest() {
        Vector2 v1 = v2(5, 5);
        Vector2 v2 = v2(10, 10);
        Vector2 closest = V2.middle(v1, v2, new Vector2());
        Assertions.assertEquals(closest, v2(7.5f, 7.5f));
    }

    @Test
    public void toFloatArrayTest() {
        Array<Vector2> array = new Array<>();
        array.add(v2(0, 0));
        array.add(v2(10, 10));
        array.add(v2(15, 15));
        array.add(v2(5, 5));

        float[] floats = V2.toFloatArray(array);

        Assertions.assertEquals(0, floats[0] + floats[1], 0);
        Assertions.assertEquals(20, floats[2] + floats[3], 0);
        Assertions.assertEquals(30, floats[4] + floats[5], 0);
        Assertions.assertEquals(10, floats[6] + floats[7], 0);

    }

    @Test
    public void setDirTest() {
        Vector2 velocity;

        velocity = V2.changeDirection(v2(50, 0), v2(-1, 0));
        VTestUtils.assertEquals(v2(-50, 0), velocity);

        velocity = V2.changeDirection(v2(0, 10), v2(1, 0));
        VTestUtils.assertEquals(v2(10, 0), velocity);

        velocity = V2.changeDirection(v2(50, 50), v2(-1, 0));
        VTestUtils.assertEquals(v2(-70, 0f), velocity, 1);

        velocity = V2.changeDirection(v2(10, -10), v2(0, 1));
        VTestUtils.assertEquals(v2(0, 14), velocity, 1);
    }

    @Test
    public void getNormaleTest() {
        Vector2 normale = v2();

        V2.getNormal(v2(1, 0), normale);
        VTestUtils.assertEquals(v2(0, 1), normale, 0);

        V2.getNormal(v2(-1, 0), normale);
        VTestUtils.assertEquals(v2(0, -1), normale, 0);

        V2.getNormal(v2(0, 1), normale);
        VTestUtils.assertEquals(v2(-1, 0), normale, 0);

        V2.getNormal(v2(0, -1), normale);
        VTestUtils.assertEquals(v2(1, 0), normale, 0);
    }

    @Test
    public void angleDeg() {
        float angle = V2.angleDeg(v2(0, 1), v2(1, 0));
        Assertions.assertEquals(90, angle, 0.1f);
    }


    @Test
    public void minTest() {
        Vector2 min;
        min = V2.min(v2(50, -50), 0);
        VTestUtils.assertEquals(50, 0, min);

        min = V2.min(v2(-50, -50), 0);
        VTestUtils.assertEquals(0, 0, min);

        min = V2.min(v2(50, 50), 0);
        VTestUtils.assertEquals(50, 50, min);
    }

    @Test
    public void maxTest() {
        Vector2 max;
        max = V2.max(v2(50, -50), 0);
        VTestUtils.assertEquals(0, -50, max);

        max = V2.max(v2(-50, -50), 0);
        VTestUtils.assertEquals(-50, -50, max);

        max = V2.max(v2(50, 50), 0);
        VTestUtils.assertEquals(0, 0, max);
    }

    @Test
    public void manhattanDistance() {
        Assertions.assertEquals(10, V2.manhattanDistance(v2(0, 0), v2(10, 0)));
    }

    @Test
    public void getTranslationTest() {
        Assertions.assertEquals(v2(10,0), V2.getTranslation(v2(0, 0), v2(10, 0), new Vector2()));
    }
}
