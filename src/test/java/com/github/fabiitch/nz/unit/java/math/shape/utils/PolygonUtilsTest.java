package com.github.fabiitch.nz.unit.java.math.shape.utils;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.VTestUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.Triangle;
import com.github.fabiitch.nz.java.math.shapes.builders.TriangleBuilder;
import com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;
import static com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils.*;

public class PolygonUtilsTest {
    private static final Vector2 tmp = v2(0, 0);

    float[] vertices = new float[]{0, 0, 50, 50, 60, 60, 300, 0};
    Polygon polygon = new Polygon(vertices);

    @Test
    public void verticesAsVectorsTest() {
        Vector2[] verticesAsVectors = getVerticesAsVectors(polygon);
        VTestUtils.assertEquals(v2(0, 0), verticesAsVectors[0]);
        VTestUtils.assertEquals(v2(50, 50), verticesAsVectors[1]);
        VTestUtils.assertEquals(v2(60, 60), verticesAsVectors[2]);
        VTestUtils.assertEquals(v2(300, 0), verticesAsVectors[3]);
    }

    @Test
    public void getVertexBeforeTest() {
        int maxIndex = vertices.length / 2 - 1; //3
        Assertions.assertEquals(maxIndex, getVertexBefore(polygon, 0));
        Assertions.assertEquals(0, getVertexBefore(polygon, 1));
        Assertions.assertEquals(1, getVertexBefore(polygon, 2));
        Assertions.assertEquals(2, getVertexBefore(polygon, 3));
        Assertions.assertEquals(maxIndex, getVertexBefore(polygon, 4));
        Assertions.assertEquals(0, getVertexBefore(polygon, 5));
        Assertions.assertEquals(1, getVertexBefore(polygon, 6));
    }

    @Test
    public void getVertexAfterTest() {
        Assertions.assertEquals(1, getVertexAfter(polygon, 0));
        Assertions.assertEquals(2, getVertexAfter(polygon, 1));
        Assertions.assertEquals(3, getVertexAfter(polygon, 2));
        Assertions.assertEquals(0, getVertexAfter(polygon, 3));
        Assertions.assertEquals(1, getVertexAfter(polygon, 4));
        Assertions.assertEquals(2, getVertexAfter(polygon, 5));
        Assertions.assertEquals(3, getVertexAfter(polygon, 6));
    }

    @Test
    public void getMaxDstFromZeroTest() {
        float maxDstVertexFromZero = getMaxDstVertex(polygon, tmp);
        float maxDstVertexFromZero2 = getMaxDstVertex(polygon);

        VTestUtils.assertEquals(v2(300, 0), tmp);
        Assertions.assertEquals(300, maxDstVertexFromZero);
        Assertions.assertEquals(300, maxDstVertexFromZero2);
    }

    @Test
    public void getMinDstFromZeroTest() {
        float maxDstVertexFromZero = getMinDstVertex(polygon, tmp);
        float maxDstVertexFromZero2 = getMinDstVertex(polygon);

        VTestUtils.assertEquals(v2(0, 0), tmp);
        Assertions.assertEquals(0, maxDstVertexFromZero);
        Assertions.assertEquals(0, maxDstVertexFromZero2);
    }


    @Test
    public void getVertexTest() {
        getVertex(polygon, 0, tmp);
        VTestUtils.assertEquals(v2(0, 0), tmp);

        getVertex(polygon, 1, tmp);
        VTestUtils.assertEquals(v2(50, 50), tmp);

        getVertex(polygon, 2, tmp);
        VTestUtils.assertEquals(v2(60, 60), tmp);

        getVertex(polygon, 3, tmp);
        VTestUtils.assertEquals(v2(300, 0), tmp);
    }

    @Test
    public void getVertexAngleTest() {
        Polygon rect = new Polygon(new float[]{0, 0, 100, 0, 100, 50, 0, 50});
        float angleA = getVertexAngleDeg(rect, 0);
        Assertions.assertEquals(90, angleA, 0);

        float angleB = getVertexAngleDeg(rect, 1);
        Assertions.assertEquals(90, angleB, 0);

        float angleC = getVertexAngleDeg(rect, 2);
        Assertions.assertEquals(90, angleC, 0);

        float angleD = getVertexAngleDeg(rect, 3);
        Assertions.assertEquals(90, angleD, 0);

    }

    @Test
    public void getVertexAngleTest2() {
        Triangle triangle = TriangleBuilder.isoscelesRectangle(0, v2(0, 0), 50);

        float angleA = getVertexAngleDeg(triangle, 0);
        Assertions.assertEquals(90, angleA, 0);

        float angleB = getVertexAngleDeg(triangle, 1);
        Assertions.assertEquals(45, angleB, 0);

        float angleC = getVertexAngleDeg(triangle, 2);
        Assertions.assertEquals(45, angleC, 0);

    }

    @Test
    public void isConvexTest() {
        Polygon rect = new Polygon(new float[]{0, 0, 100, 0, 100, 50, 0, 50});
        Assertions.assertTrue(isConvex(rect));

        Triangle triangle = TriangleBuilder.isoscelesRectangle(0, v2(0, 0), 50);
        Assertions.assertTrue(isConvex(triangle));

        Assertions.assertTrue(isConvex(polygon));

        Polygon concavePoly = new Polygon(new float[]{0, 0, 5, 5, 0, 10, 20, 10, 20, 0});
        Assertions.assertFalse(isConvex(concavePoly));
    }

    @Test
    public void getClosestEdgeFromPointTest() {
        Segment result = new Segment();
        Polygon rect = new Polygon(new float[]{0, 0, 100, 0, 100, 50, 0, 50});

        PolygonUtils.getClosestEdge(rect, v2(50, 150), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 50, 100, 50)));

        PolygonUtils.getClosestEdge(rect, v2(-25, 25), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 0, 50)));

        PolygonUtils.getClosestEdge(rect, v2(600, 25), result);
        Assertions.assertTrue(result.equalsPoints(s(100, 0, 100, 50)));

        PolygonUtils.getClosestEdge(rect, v2(50, -150), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 100, 0)));

        //inside
        PolygonUtils.getClosestEdge(rect, v2(50, 10), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 100, 0)));

        //on edge
        PolygonUtils.getClosestEdge(rect, v2(50, 0), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 100, 0)));
    }

    @Test
    public void getClosestEdgeFromSegmentTest() {
        Segment result = new Segment();
        Polygon rect = new Polygon(new float[]{0, 0, 100, 0, 100, 50, 0, 50});

        //perp left
        PolygonUtils.getClosestEdge(rect, s(-50, 25, 10, 25), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 0, 50)));

        //parallel left
        PolygonUtils.getClosestEdge(rect, s(-100, 25, -50, 25), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 0, 0, 50)));

        //intersectTop
        PolygonUtils.getClosestEdge(rect, s(25, 100, 25, 25), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 50, 100, 50)));

        //intersectTopBot
        PolygonUtils.getClosestEdge(rect, s(25, 100, 25, -50), result);
        Assertions.assertTrue(result.equalsPoints(s(0, 50, 100, 50)) || result.equalsPoints(s(0, 0, 100, 0)));
    }


    private static Segment s(float aX, float aY, float bX, float bY) {
        return new Segment(aX, aY, bX, bY);
    }
}
