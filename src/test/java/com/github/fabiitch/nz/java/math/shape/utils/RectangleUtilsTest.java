package com.github.fabiitch.nz.java.math.shape.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.gdxunit.VTestUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.gdxunit.ShapeTestBuilder.r;
import static com.github.fabiitch.gdxunit.ShapeTestBuilder.v2;
import static com.github.fabiitch.gdxunit.VTestUtils.assertEquals;
import static com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleUtilsTest {

    private final static float DELTA = MathUtils.FLOAT_ROUNDING_ERROR;

    @Test
    public void getVertexTest() {
        Rectangle rect = r(10, 20, 100, 50);
        Vector2 vertexPos = new Vector2();

        assertEquals(10, 20, getA(rect, vertexPos));
        assertEquals(110, 20, getB(rect, vertexPos));
        assertEquals(110, 70, getC(rect, vertexPos));
        assertEquals(10, 70, getD(rect, vertexPos));

        assertEquals(10, 20, getVertex(rect, 0, vertexPos));
        assertEquals(110, 20, getVertex(rect, 1, vertexPos));
        assertEquals(110, 70, getVertex(rect, 2, vertexPos));
        assertEquals(10, 70, getVertex(rect, 3, vertexPos));
    }

    @Test
    public void getEdgeTest() {
        Rectangle rect = r(10, 20, 100, 50);
        Segment tmp1 = new Segment();
        Segment tmp2 = new Segment();
        assertTrue(getAB(rect, tmp1).equalsPoints(tmp2.set(10, 20, 110, 20)));
        assertTrue(getBC(rect, tmp1).equalsPoints(tmp2.set(110, 20, 110, 70)));
        assertTrue(getCD(rect, tmp1).equalsPoints(tmp2.set(110, 70, 10, 70)));
        assertTrue(getAD(rect, tmp1).equalsPoints(tmp2.set(10, 20, 10, 70)));
    }

    @Test
    public void getCenterTest() {
        Rectangle rect1 = r(10, 20, 100, 50);
        Vector2 center1 = getCenter(rect1, new Vector2());
        assertEquals(60, 45, center1);

        Rectangle rect2 = r(100, 200, 500, 150);
        Vector2 center2 = getCenter(rect2, new Vector2());
        assertEquals(100 + 250, 200 + 75, center2);
    }


    @Test
    public void getAsVerticesTest() {
        Rectangle rect1 = r(10, 20, 100, 50);
        float[] asVertices = getAsVertices(rect1);

        assertEquals(10, asVertices[0]);
        assertEquals(20, asVertices[1]);

        assertEquals(10, asVertices[2]);
        assertEquals(70, asVertices[3]);

        assertEquals(110, asVertices[4]);
        assertEquals(70, asVertices[5]);

        assertEquals(110, asVertices[6]);
        assertEquals(20, asVertices[7]);
    }

    @Test
    public void getSegmentsTest() {
        Rectangle rect = r(0, 0, 100, 50);
        Segment segment = new Segment();
        Segment horizontalTop = getHorizontalTop(rect, segment);
        assertEquals(horizontalTop, new Segment(0, 50, 100, 50));

        Segment horizontalBot = getHorizontalBot(rect, segment);
        assertEquals(horizontalBot, new Segment(0, 0, 100, 0));

        Segment verticalLeft = getVerticalLeft(rect, segment);
        assertEquals(verticalLeft, new Segment(0, 0, 0, 50));

        Segment verticalRight = getVerticalRight(rect, segment);
        assertEquals(verticalRight, new Segment(100, 0, 100, 50));
    }

    @Test
    public void testIsVertex() {
        int vertexNum;
        Vector2 v = new Vector2();
        Rectangle rect = r(10, 10, 5, 5);
        vertexNum = isVertex(rect, v.set(10, 10));
        assertEquals(0, vertexNum);

        vertexNum = isVertex(rect, v.set(15, 10));
        assertEquals(1, vertexNum);

        vertexNum = isVertex(rect, v.set(15, 15));
        assertEquals(2, vertexNum);

        vertexNum = isVertex(rect, v.set(10, 15));
        assertEquals(3, vertexNum);

        vertexNum = isVertex(rect, v.set(11, 10));
        assertEquals(-1, vertexNum);
    }

    @Test
    public void getClosestVertexTest() {
        Rectangle rect = r(0, 0, 100, 50);
        Vector2 closestVertex = new Vector2();

        int vertexClosest = getNumClosestVertex(rect, -1, -1);
        getNumClosestVertex(rect, -1, -1, closestVertex);
        assertTrue(vertexClosest == 0);

        vertexClosest = getNumClosestVertex(rect, 102, 1);
        assertTrue(vertexClosest == 1);

        vertexClosest = getNumClosestVertex(rect, 110, 55);
        assertTrue(vertexClosest == 2);

        vertexClosest = getNumClosestVertex(rect, -1, 55);
        assertTrue(vertexClosest == 3);
    }

    @Test
    public void getClosestPointTest() {
        Rectangle rect = r(0, 0, 100, 50);
        Vector2 closestPoint = new Vector2();

        closestPoint(rect, new Vector2(50, 150), closestPoint);
        assertEquals(new Vector2(50, 50), closestPoint);

        closestPoint(rect, new Vector2(150, 25), closestPoint);
        assertEquals(new Vector2(100, 25), closestPoint);

        closestPoint(rect, new Vector2(-100, 25), closestPoint);
        assertEquals(new Vector2(0, 25), closestPoint);
    }

    @Test
    public void getClosestEdgeFromPointTest() {
        Rectangle rect = r(0, 0, 100, 50);
        Segment tmp = new Segment();
        Segment closestSegment = new Segment();

        closestEdge(rect, new Vector2(50, 150), closestSegment);
        assertTrue(getHorizontalTop(rect, tmp).equalsPoints(closestSegment));

        closestEdge(rect, new Vector2(50, -10), closestSegment);
        assertTrue(getHorizontalBot(rect, tmp).equalsPoints(closestSegment));

        closestEdge(rect, new Vector2(-15, 25), closestSegment);
        assertTrue(getVerticalLeft(rect, tmp).equalsPoints(closestSegment));

        closestEdge(rect, new Vector2(300, 25), closestSegment);
        assertTrue(getVerticalRight(rect, tmp).equalsPoints(closestSegment));

        //on rect
        closestEdge(rect, new Vector2(50, 0), closestSegment);
        assertTrue(getHorizontalBot(rect, tmp).equalsPoints(closestSegment));
    }

    @Test
    public void getDiagDstTest() {
        Rectangle rect = r(0, 0, 10, 20);
        float diagDst = getDiagDst(rect);

        assertEquals(Math.sqrt(500), diagDst, 1);
    }

    @Test
    public void circleInsideTest() {
        Rectangle rect = r(50, 50, 10, 20);
        Circle circleInside = getCircleInside(rect);

        assertEquals(55, circleInside.x, DELTA);
        assertEquals(60, circleInside.y, DELTA);
        assertEquals(5, circleInside.radius, DELTA);
    }


    @Test
    public void testOverlaps() {
        Rectangle a = r(0, 0, 50, 50);
        Rectangle b = r(50, 0, 50, 50);
        assertFalse(a.overlaps(b));
        assertFalse(b.overlaps(a));
        assertTrue(overlapsStick(a, b));
        assertTrue(overlapsStick(b, a));

        b = r(-50, 0, 50, 50);
        assertTrue(overlapsStick(a, b));
        assertTrue(overlapsStick(b, a));

        b = r(0, 50, 50, 50);
        assertTrue(overlapsStick(a, b));
        assertTrue(overlapsStick(b, a));

        b = r(0, -50, 50, 50);
        assertTrue(overlapsStick(a, b));
        assertTrue(overlapsStick(b, a));

        b = r(51, 0, 50, 50);
        assertFalse(overlapsStick(a, b));
        assertFalse(overlapsStick(b, a));

        b = r(-51, 0, 50, 50);
        assertFalse(overlapsStick(a, b));
        assertFalse(overlapsStick(b, a));

        b = r(0, 51, 50, 50);
        assertFalse(overlapsStick(a, b));
        assertFalse(overlapsStick(b, a));

        b = r(0, -51, 50, 50);
        assertFalse(overlapsStick(a, b));
        assertFalse(overlapsStick(b, a));
    }

    @Test
    public void getEdgeWithAngleTest() {
        Rectangle rect = r(0, 0, 20, 10);  //center (10,5)
        Segment edge = new Segment();

        getEdgeWithAngle(rect, 0, edge);
        assertEquals(getVerticalRight(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 15, edge);
        assertEquals(getVerticalRight(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 44, edge);
        assertEquals(getVerticalRight(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 45, edge);
        assertEquals(getHorizontalTop(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 50, edge);
        assertEquals(getHorizontalTop(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 90, edge);
        assertEquals(getHorizontalTop(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 134, edge);
        assertEquals(getHorizontalTop(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 135, edge);
        assertEquals(getVerticalLeft(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 150, edge);
        assertEquals(getVerticalLeft(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 224, edge);
        assertEquals(getVerticalLeft(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 225, edge);
        assertEquals(getHorizontalBot(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 300, edge);
        assertEquals(getHorizontalBot(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 314, edge);
        assertEquals(getHorizontalBot(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 360, edge);
        assertEquals(getVerticalRight(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 314 + 360, edge);
        assertEquals(getHorizontalBot(rect, new Segment()), edge);

        getEdgeWithAngle(rect, -360, edge);
        assertEquals(getVerticalRight(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 135 - 720, edge);
        assertEquals(getVerticalLeft(rect, new Segment()), edge);

        getEdgeWithAngle(rect, 314 + -720, edge);
        assertEquals(getHorizontalBot(rect, new Segment()), edge);
    }

    @Test
    public void mergeRectTest() {
        Rectangle rect = r(100, 100);

        rect.merge(50, 50); //contains
        assertEquals(r(0, 0, 100, 100), rect);
        assertTrue(rect.contains(50, 50));

        rect.merge(-10, 50); //grow -x
        assertEquals(r(-10, 0, 110, 100), rect);
        assertTrue(rect.contains(-10, 50));

        rect.merge(-5, 50);//contains
        assertEquals(r(-10, 0, 110, 100), rect);
        assertTrue(rect.contains(-5, 50));

        rect.merge(0, -10); //grow -y
        assertEquals(r(-10, -10, 110, 110), rect);
        assertTrue(rect.contains(0, -10));

        rect.merge(0, -5); //contains
        assertEquals(r(-10, -10, 110, 110), rect);
        assertTrue(rect.contains(0, -5));

        rect.merge(150, -10); //grow +x
        assertEquals(r(-10, -10, 160, 110), rect);
        assertTrue(rect.contains(150, -10));

        rect.merge(100, -10);//contains
        assertEquals(r(-10, -10, 160, 110), rect);
        assertTrue(rect.contains(100, -10));

        rect.merge(100, 150); //grow +y
        assertEquals(r(-10, -10, 160, 160), rect);
        assertTrue(rect.contains(100, 150));
    }


    /**
     * 0 = outside
     * 5 = center
     * ________
     * | 4 | 3 |
     * |_1_|_2_|
     */
    @Test
    public void getRegionInsideTest() {
        Rectangle rect = r(200, 100);
        assertEquals(0, getRegionInside(rect, v2(-10, -10)));
        assertEquals(1, getRegionInside(rect, v2(50, 25)));
        assertEquals(2, getRegionInside(rect, v2(150, 25)));
        assertEquals(3, getRegionInside(rect, v2(150, 75)));
        assertEquals(4, getRegionInside(rect, v2(50, 75)));
        assertEquals(5, getRegionInside(rect, v2(100, 50)));
    }

    /**
     * Renvoi la order relative au rect
     * 0 = inside
     * 4 |  3 | 2
     * __|____|__
     * 5 |  0 | 1
     * __|____|___
     * 6 |  7 | 8
     */
    @Test
    public void getRegionOutsideTest() {
        Rectangle rect = r(200, 100);
        float lowX = -50, hightX = 250, inX = 100;
        float lowY = -25, hightY = 125, inY = 50;
        assertEquals(0, getRegionOutside(rect, v2(inX, inY)));
        assertEquals(1, getRegionOutside(rect, v2(hightX, inY)));
        assertEquals(2, getRegionOutside(rect, v2(hightX, hightY)));
        assertEquals(3, getRegionOutside(rect, v2(inX, hightY)));
        assertEquals(4, getRegionOutside(rect, v2(lowX, hightY)));
        assertEquals(5, getRegionOutside(rect, v2(lowX, inY)));
        assertEquals(6, getRegionOutside(rect, v2(lowX, lowY)));
        assertEquals(7, getRegionOutside(rect, v2(inX, lowY)));
        assertEquals(8, getRegionOutside(rect, v2(hightX, lowY)));

        //vertex
        assertEquals(0, getRegionOutside(rect, v2(0, 0)));
        assertEquals(0, getRegionOutside(rect, v2(rect.width, rect.height)));
        assertEquals(0, getRegionOutside(rect, v2(0, rect.height)));
        assertEquals(0, getRegionOutside(rect, v2(rect.width, 0)));
    }

    @Test
    public void getRegionTest() {
        Rectangle rect = r(200, 100);
        float lowX = -50, hightX = 250, inX = 100;
        float lowY = -25, hightY = 125, inY = 50;
        assertEquals(1, getRegion(rect, v2(hightX, inY)));
        assertEquals(2, getRegion(rect, v2(hightX, hightY)));
        assertEquals(3, getRegion(rect, v2(inX, hightY)));
        assertEquals(4, getRegion(rect, v2(lowX, hightY)));
        assertEquals(5, getRegion(rect, v2(lowX, inY)));
        assertEquals(6, getRegion(rect, v2(lowX, lowY)));
        assertEquals(7, getRegion(rect, v2(inX, lowY)));
        assertEquals(8, getRegion(rect, v2(hightX, lowY)));

        assertEquals(9, getRegion(rect, v2(50, 25)));
        assertEquals(10, getRegion(rect, v2(150, 25)));
        assertEquals(11, getRegion(rect, v2(150, 75)));
        assertEquals(12, getRegion(rect, v2(50, 75)));
        assertEquals(13, getRegion(rect, v2(100, 50)));
    }

    @Test
    public void scaleTest() {
        assertEquals(r(20, 20), RectangleUtils.scale(r(10, 10), 2));
        assertEquals(r(5, 5), RectangleUtils.scale(r(10, 10), 0.5f));

        Rectangle scale2 = scale(r(10, 10, 10, 10), 2, true);
        assertEquals(r(0, 0, 20, 20), scale2);

        Rectangle scale05 = scale(r(10, 10, 10, 10), 0.5f, true);
        assertEquals(r(15, 15, 5, 5), scale05);
    }

    @Test
    public void constainsStickPos() {
        Rectangle rectA = r(0, 0, 200, 200);

        assertTrue(containsStick(rectA, 0, 0));
        assertTrue(containsStick(rectA, 200, 0));
        assertTrue(containsStick(rectA, 200, 200));

        assertFalse(containsStick(rectA, 200.0001f, 200));
        assertFalse(containsStick(rectA, -0.001f, 200));
    }

    @Test
    public void containsSickRectangle() {
        Rectangle rectMergeA = r(0, 0, 200, 200);
        Rectangle rectMergeB = r(10, 290, 20, 20);
        rectMergeA.merge(rectMergeB);
        assertTrue(containsStick(rectMergeA, rectMergeB));

        rectMergeA = r(0, 0, 200, 200);
        rectMergeB = r(10, 290.96002f, 20, 20);
        rectMergeA.merge(rectMergeB);
        assertTrue(containsStick(rectMergeA, rectMergeB));

        //Test 2
        assertTrue(containsStick(r(10, 10, 100, 100), r(10, 10, 100, 100)));

        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 10, 101, 100)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 10, 100, 101)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(11, 10, 100, 100)));
        assertFalse(containsStick(r(10, 10, 100, 100), r(10, 11, 100, 100)));
    }

    @Test
    public void floorCeilTest() {
        Rectangle rect = r(-55.4544f, 88.12112124f, 10.884445f, -25.889787f);
        floorCeil(rect);
        assertEquals(-56, rect.x);
        assertEquals(88, rect.y);
        assertEquals(11, rect.width);
        assertEquals(-25, rect.height);
    }

    @Test
    public void mergeFloorCeilTest() {
        Rectangle rect = r(100.21f, 200.0000356f);
        RectangleUtils.mergeFloorCeil(rect, r(-5.555f, -6.00000554f, 10, 10));
        assertEquals(r(-6, -7, 107, 208), rect);

        rect = r(100, 200);
        RectangleUtils.mergeFloorCeil(rect, r(-6f, -7f, 10, 10));
        assertEquals(r(-6, -7, 106, 207), rect);
    }


    @Test
    public void getRandomPosTest() {
        Rectangle rect1 = r(0, 0, 100, 50);
        for (int i = 0; i < 100; i++)
            assertTrue(rect1.contains(RectangleUtils.getRandomPos(rect1, v2())));
    }

    //TODO marche pas
    @Test
    public void posOnEdgeAngleTest() {
        final float TOLERANCE = 0.1f;
        Rectangle rect = r(0, 0, 10, 10); // center (5,5)
        Vector2 point;

        point = posOnEdgeAngle(rect, 0);
        VTestUtils.assertEquals(10, 5, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 90);
        VTestUtils.assertEquals(5, 10, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 180);
        VTestUtils.assertEquals(0, 5, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 270);
        VTestUtils.assertEquals(5, 0, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 360);
        VTestUtils.assertEquals(10, 5, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 540);
        VTestUtils.assertEquals(0, 5, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 45);
        VTestUtils.assertEquals(10, 10, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 135);
        VTestUtils.assertEquals(0, 10, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 225);
        VTestUtils.assertEquals(0, 0, point, TOLERANCE);

        point = posOnEdgeAngle(rect, 315);
        VTestUtils.assertEquals(10, 0, point, TOLERANCE);

        //Negatif
        point = posOnEdgeAngle(rect, -90);
        VTestUtils.assertEquals(5, 0, point, TOLERANCE);

        point = posOnEdgeAngle(rect, -180);
        VTestUtils.assertEquals(0, 5, point, TOLERANCE);

        point = posOnEdgeAngle(rect, -270);
        VTestUtils.assertEquals(5, 10, point, TOLERANCE);

        point = posOnEdgeAngle(rect, -360);
        VTestUtils.assertEquals(10, 5, point, TOLERANCE);
    }

    @Test
    public void getOverlapTest() {
        {
            Rectangle r1 = r(0, 0, 10, 10);
            Rectangle r2 = r(5, 5, 10, 10);
            Rectangle overlap = getOverlap(r1, r2);
            assertEquals(r(5, 5, 5, 5), overlap);
        }
        {
            Rectangle r1 = r(-10, -10, 50, 50);
            Rectangle r2 = r(0, 5, 15, 15);
            Rectangle overlap = getOverlap(r1, r2);
            assertEquals(r(0, 5, 15, 15), overlap);
        }
    }

    @Test
    public void stickTest() {
        Rectangle r1 = r(0, 0, 10, 10);
        Rectangle r2 = r(0, 10, 5, 10);
        assertTrue(RectangleUtils.stick(r1, r2));
    }

    @Test
    public void getMiddleSegmentTest() {
        Rectangle r1 = r(0, 0, 10, 10);
        assertEquals(new Segment(0, 5, 10, 5), RectangleUtils.getMiddleSegment(r1, Orientation.Horizontal));
        assertEquals(new Segment(5, 0, 5, 10), RectangleUtils.getMiddleSegment(r1, Orientation.Vertical));


        Rectangle r2 = r(0, 0, 20, 20);
        assertEquals(new Segment(0, 10, 20, 10), RectangleUtils.getMiddleSegment(r2, Orientation.Horizontal));
        assertEquals(new Segment(10, 0, 10, 20), RectangleUtils.getMiddleSegment(r2, Orientation.Vertical));
    }

}
