package com.fabiitch.nz.math.vectors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.math.AbstractMathTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class V2Test extends AbstractMathTest {

    @Test
    public void isZeroTest() {
        Assertions.assertTrue(V2.isZero(v()));
        Assertions.assertTrue(V2.isZero(v(MathUtils.FLOAT_ROUNDING_ERROR / 2, MathUtils.FLOAT_ROUNDING_ERROR / 2)));
        Assertions.assertTrue(V2.isZero(v(0.1f, 0.1f), 0.1f));

        Assertions.assertFalse(V2.isZero(v(1, 0)));
        Assertions.assertFalse(V2.isZero(v(0.11f, 0.11f), 0.1f));
    }

    @Test
    public void getClosestTest() {
        Vector2 start = v(0, 0);
        Vector2 v1 = v(8, 2);
        Vector2 v2 = v(10, 0);
        Vector2 v3 = v(5, 0);
        Array<Vector2> vector2Array = new Array<>();
        vector2Array.add(v1, v2, v3, null);

        Vector2 closest = V.getClosest(start, vector2Array);
        Assertions.assertEquals(closest, v3);
    }

    @Test
    public void getMiddleTest() {
        Vector2 v1 = v(5, 5);
        Vector2 v2 = v(10, 10);
        Vector2 closest = V2.middle(v1, v2, new Vector2());
        Assertions.assertEquals(closest, v(7.5f, 7.5f));
    }

    @Test
    public void toFloatArrayTest() {
        Array<Vector2> array = new Array<>();
        array.add(v(0, 0));
        array.add(v(10, 10));
        array.add(v(15, 15));
        array.add(v(5, 5));

        float[] floats = V2.toFloatArray(array);

        Assertions.assertEquals(0, floats[0] + floats[1], 0);
        Assertions.assertEquals(20, floats[2] + floats[3], 0);
        Assertions.assertEquals(30, floats[4] + floats[5], 0);
        Assertions.assertEquals(10, floats[6] + floats[7], 0);

    }

    @Test
    public void setDirTest() {
        Vector2 velocity;

        velocity = V2.changeDirection(v(50, 0), v(-1, 0));
        VTestUtils.assertEquals(v(-50, 0), velocity);

        velocity = V2.changeDirection(v(0, 10), v(1, 0));
        VTestUtils.assertEquals(v(10, 0), velocity);

        velocity = V2.changeDirection(v(50, 50), v(-1, 0));
        VTestUtils.assertEquals(v(-70, 0f), velocity, 1);

        velocity = V2.changeDirection(v(10, -10), v(0, 1));
        VTestUtils.assertEquals(v(0, 14), velocity, 1);
    }

    @Test
    public void getNormaleTest() {
        Vector2 normale = v();

        V2.getNormal(v(1, 0), normale);
        VTestUtils.assertEquals(v(0, 1), normale, 0);

        V2.getNormal(v(-1, 0), normale);
        VTestUtils.assertEquals(v(0, -1), normale, 0);

        V2.getNormal(v(0, 1), normale);
        VTestUtils.assertEquals(v(-1, 0), normale, 0);

        V2.getNormal(v(0, -1), normale);
        VTestUtils.assertEquals(v(1, 0), normale, 0);
    }

    @Test
    public void angleDeg() {
        float angle = V2.angleDeg(v(0, 1), v(1, 0));
        Assertions.assertEquals(90, angle, 0.1f);
    }


    @Test
    public void minTest() {
        Vector2 min;
        min = V2.min(v(50, -50), 0);
        VTestUtils.assertEquals(50, 0, min);

        min = V2.min(v(-50, -50), 0);
        VTestUtils.assertEquals(0, 0, min);

        min = V2.min(v(50, 50), 0);
        VTestUtils.assertEquals(50, 50, min);
    }

    @Test
    public void maxTest() {
        Vector2 max;
        max = V2.max(v(50, -50), 0);
        VTestUtils.assertEquals(0, -50, max);

        max = V2.max(v(-50, -50), 0);
        VTestUtils.assertEquals(-50, -50, max);

        max = V2.max(v(50, 50), 0);
        VTestUtils.assertEquals(0, 0, max);
    }
}
