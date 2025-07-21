package com.github.fabiitch.nz.java.math.shapes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.vectors.VTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.github.fabiitch.gdxunit.shape.ShapeTestBuilder.v2;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TriangleTest  {
    private static final float ANGLE_TOLERANCE = 1f;
    private static final float POSITION_TOLERANCE = 0.01f;

    private Vector2 v0;
    private Vector2 v1;
    private Vector2 v2;
    private Triangle triangle;
    private Vector2 posTest;

    @BeforeEach
    public void init() {
        v0 = new Vector2(0, 0);
        v1 = new Vector2(0, 5);
        v2 = new Vector2(5, 0);
        triangle = new Triangle(v0, v1, v2);

        posTest = v2(-100, -100);
    }

    private void moveAndRotate() {
        float min = -50;
        float max = +50;
        triangle.translate(MathUtils.random.nextFloat() * (max - min) + min,
                MathUtils.random.nextFloat() * (max - min) + min);
        triangle.rotate(1f);

        triangle.getA(v0);
        triangle.getB(v1);
        triangle.getC(v2);
    }

    @Test
    public void getDirTest() {
        triangle.getDir(0, 1, posTest);
        VTestUtils.assertEquals(v2(0, 1), posTest, ANGLE_TOLERANCE);

        triangle.getDir(0, 2, posTest);
        VTestUtils.assertEquals(v2(1, 0), posTest, ANGLE_TOLERANCE);

        triangle.getDir(1, 2, posTest);
        final Vector2 BCNor =v2(5, 0).sub(0, 5).nor();
        VTestUtils.assertEquals(BCNor, posTest, ANGLE_TOLERANCE);
        moveAndRotate();
    }

    @Test
    public void getAngleTest() {
        for (int i = 0; i < 10; i++) {
            float angleA = triangle.getAngleDeg(0);
            assertEquals(90, angleA, ANGLE_TOLERANCE);

            float angleB = triangle.getAngleDeg(1);
            assertEquals(45, angleB, ANGLE_TOLERANCE);

            float angleC = triangle.getAngleDeg(2);
            assertEquals(45, angleC, ANGLE_TOLERANCE);
            moveAndRotate();
        }
    }

    @Test
    public void getVertex() {
        for (int i = 0; i < 10; i++) {
            triangle.getA(posTest);
            VTestUtils.assertEquals(posTest, v0);

            triangle.getB(posTest);
            VTestUtils.assertEquals(posTest, v1);

            triangle.getC(posTest);
            VTestUtils.assertEquals(posTest, v2);
            moveAndRotate();
        }
    }

    // com.nzt.gdx.math.shape.Triangle.getVertex(int)
    // private method cant test witouht PowerMock
    @Test
    public void getVertexAskTest() {
        assertEquals(0, 0 % 3);
        assertEquals(1, 1 % 3);
        assertEquals(2, 2 % 3);
        assertEquals(0, 3 % 3);
        assertEquals(1, 4 % 3);
        assertEquals(2, 5 % 3);
        assertEquals(0, 6 % 3);
        assertEquals(1, 7 % 3);
        assertEquals(2, 8 % 3);
        assertEquals(0, 9 % 3);
        assertEquals(1, 10 % 3);
    }

}
