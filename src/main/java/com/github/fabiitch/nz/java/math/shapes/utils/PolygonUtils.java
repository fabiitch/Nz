package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.NzMath;

public class PolygonUtils {

    public static Polygon tmpPolygon = new Polygon(); //TODO mathSett
    public static Vector2 tmpV1 = new Vector2(); //TODO mathSett
    public static Vector2 tmpV2 = new Vector2();
    public static Vector2 tmpV3 = new Vector2();

    private PolygonUtils() {
    }

    /**
     * Warning, create new [] and newVector
     */
    public static Vector2[] getVerticesAsVectors(Polygon polygon) {
        float[] transformedVertices = polygon.getTransformedVertices();

        Vector2[] vertices = new Vector2[transformedVertices.length / 2];
        for (int i = 0; i < transformedVertices.length; i += 2) {
            vertices[i / 2] = new Vector2(transformedVertices[i], transformedVertices[i + 1]);
        }
        return vertices;
    }

    public static Vector2 getPos(Polygon polygon, Vector2 pos) {
        return pos.set(polygon.getX(), polygon.getY());
    }


    private static float getMinOrMaxDstVertex(Polygon polygon, boolean getMax, Vector2 vertexReturn) {
        float[] vertices = polygon.getTransformedVertices();
        Vector2 tmp = PolygonUtils.tmpV1;
        float dstKeep = getMax ? Float.MIN_VALUE : Float.MAX_VALUE;
        int i = 0;
        while (i < vertices.length) {
            tmp.set(vertices[i], vertices[i + 1]);
            float dstVertex = tmp.dst(0, 0);
            if (getMax ? dstVertex > dstKeep : dstVertex < dstKeep) {
                dstKeep = dstVertex;
                if (vertexReturn != null)
                    vertexReturn.set(tmp);
            }
            i += 2;
        }
        return dstKeep;
    }

    public static float getMinDstVertex(Polygon polygon, Vector2 vertex) {
        return getMinOrMaxDstVertex(polygon, false, vertex);
    }

    public static float getMinDstVertex(Polygon polygon) {
        return getMinDstVertex(polygon, null);
    }

    public static float getMaxDstVertex(Polygon polygon, Vector2 vertex) {
        return getMinOrMaxDstVertex(polygon, true, vertex);
    }

    public static float getMaxDstVertex(Polygon polygon) {
        return getMaxDstVertex(polygon, null);
    }


    public static Polygon getTmpPolygon(float[] vertices) {
        tmpPolygon.setVertices(vertices);
        return tmpPolygon;
    }

    public static Vector2 getCenter(Polygon polygon, Vector2 toSet) {
        return GeometryUtils.polygonCentroid(polygon.getTransformedVertices(), 0, polygon.getVertices().length, toSet);
    }

    public static float getVertexValue(Polygon polygon, int vertexNum, boolean xValue) {
        float[] vertices = polygon.getTransformedVertices();
        if (xValue)
            return vertices[2 * vertexNum];
        return vertices[2 * vertexNum + 1];
    }

    public static Vector2 getVertex(Polygon polygon, int vertexNum, Vector2 result) {
        float[] vertices = polygon.getTransformedVertices();
        if (vertexNum < 0 || vertexNum > vertices.length / 2)
            throw new IllegalArgumentException("getVertex can return vertex range (0,2)");
        return result.set(getVertexValue(polygon, vertexNum, true), getVertexValue(polygon, vertexNum, false));
    }

    public static int getVertexBefore(Polygon polygon, int vertex) {
        return NzMath.floorMod(vertex - 1, polygon.getVertices().length / 2);
    }

    public static int getVertexAfter(Polygon polygon, int vertex) {
        return (vertex + 1) % (polygon.getVertices().length / 2);
    }

    public static float getVertexAngleDeg(Polygon polygon, int vertex) {
        int vertexA = getVertexBefore(polygon, vertex);
        int vertexB = getVertexAfter(polygon, vertex);

        getVertex(polygon, vertex, tmpV1);
        tmpV1.sub(getVertexValue(polygon, vertexA, true), getVertexValue(polygon, vertexA, false));

        getVertex(polygon, vertex, tmpV2);
        tmpV2.sub(getVertexValue(polygon, vertexB, true), getVertexValue(polygon, vertexB, false));

        float angle = tmpV1.angleDeg(tmpV2);
        if (angle > 180)
            angle = 360 - angle;
        return angle;
    }

    public static boolean isConvex(Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();

        if (vertices.length / 2 < 4)
            return true;

        boolean sign = false;
        int n = vertices.length / 2;

        for (int i = 0; i < n; i++) {
            float dx1 = getVertexValue(polygon, (i + 2) % n, true) - getVertexValue(polygon, (i + 1) % n, true);
            float dy1 = getVertexValue(polygon, (i + 2) % n, false) - getVertexValue(polygon, (i + 1) % n, false);
            float dx2 = getVertexValue(polygon, (i), true) - getVertexValue(polygon, (i + 1) % n, true);
            float dy2 = getVertexValue(polygon, (i) % n, false) - getVertexValue(polygon, (i + 1) % n, false);
            float zcrossproduct = dx1 * dy2 - dy1 * dx2;
            if (i == 0)
                sign = zcrossproduct > 0;
            else if (sign != (zcrossproduct > 0))
                return false;
        }

        return true;
    }

    public static Segment getClosestEdge(Polygon polygon, Vector2 point, Segment result) {
        float dstMin = Float.MAX_VALUE;
        boolean intersectionFind = false;
        float[] vertices = polygon.getTransformedVertices();
        int i = 0;
        int vertexA = 0, vertexB = 0;

        while (i <= vertices.length / 2) {
            result.a.set(vertices[i], vertices[i + 1]);
            result.b.set(vertices[i + 2], vertices[i + 3]);
            result.closestPoint(point, tmpV1);

            float dstPoint = point.dst2(tmpV1);
            if (dstPoint < dstMin) {
                vertexA = i / 2;
                vertexB = vertexA++;
                dstMin = dstPoint;
                if (NzMath.isZero(dstPoint)) {
                    intersectionFind = true;
                    break;
                }

            }
            i += 2;
        }
        //last and first point
        if (!intersectionFind) {
            result.a.set(vertices[0], vertices[1]);
            result.b.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);
            result.closestPoint(point, tmpV1);
            float dstPoint = point.dst2(tmpV1);
            if (dstPoint < dstMin) {
                vertexA = 0;
                vertexB = vertices.length / 2 - 1;
            }
        }
        PolygonUtils.getVertex(polygon, vertexA, result.a);
        PolygonUtils.getVertex(polygon, vertexB, result.b);
        return result;
    }

    public static Segment getClosestEdge(Polygon polygon, Segment segment, Segment result) {
        float dstMinEdgeSegment = Float.MAX_VALUE;
        boolean intersectionFind = false;
        float[] vertices = polygon.getTransformedVertices();
        int i = 0;
        int vertexA = 0, vertexB = 0;

        while (i <= vertices.length / 2) {
            result.a.set(vertices[i], vertices[i + 1]);
            result.b.set(vertices[i + 2], vertices[i + 3]);

            float dstToEdge = SegmentUtils.dstMin(result, segment);
            if (dstToEdge < dstMinEdgeSegment) {
                vertexA = i / 2;
                vertexB = vertexA++;
                dstMinEdgeSegment = dstToEdge;
                if (NzMath.isZero(dstToEdge)) {
                    intersectionFind = true;
                    break;
                }
            }
            i += 2;
        }

        //last and first point
        if (!intersectionFind) {
            result.a.set(vertices[0], vertices[1]);
            result.b.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);
            float dstToEdge = SegmentUtils.dstMin(result, segment);
            if (dstToEdge < dstMinEdgeSegment) {
                vertexA = 0;
                vertexB = vertices.length / 2 - 1;
            }
        }
        PolygonUtils.getVertex(polygon, vertexA, result.a);
        PolygonUtils.getVertex(polygon, vertexB, result.b);
        return result;
    }

    public static boolean isClockwise(Polygon polygon) {
        return GeometryUtils.isClockwise(polygon.getVertices(), 0, polygon.getVertices().length);
    }

    public static boolean isCCW(Polygon polygon) {
        return !GeometryUtils.isClockwise(polygon.getVertices(), 0, polygon.getVertices().length);
    }

    public static void ensureCCW(Polygon polygon) {
        GeometryUtils.ensureCCW(polygon.getVertices());
    }

    public static void ensureClockWise(Polygon polygon) {
        if (!isClockwise(polygon))
            reverseVertices(polygon);
    }

    public static void reverseVertices(Polygon polygon) {
        float[] vertices = polygon.getVertices();
        int lastX = vertices.length - 2;
        for (int i = 0, n = vertices.length / 2; i < n; i += 2) {
            int other = lastX - i;
            float x = vertices[i];
            float y = vertices[i + 1];
            vertices[i] = vertices[other];
            vertices[i + 1] = vertices[other + 1];
            vertices[other] = x;
            vertices[other + 1] = y;
        }
    }

    public static Rectangle getBoundingRectangle(Polygon polygon, Rectangle bounds){
        float[] vertices = polygon.getTransformedVertices();

        float minX = vertices[0];
        float minY = vertices[1];
        float maxX = vertices[0];
        float maxY = vertices[1];

        final int numFloats = vertices.length;
        for (int i = 2; i < numFloats; i += 2) {
            minX = minX > vertices[i] ? vertices[i] : minX;
            minY = minY > vertices[i + 1] ? vertices[i + 1] : minY;
            maxX = maxX < vertices[i] ? vertices[i] : maxX;
            maxY = maxY < vertices[i + 1] ? vertices[i + 1] : maxY;
        }
        bounds.x = minX;
        bounds.y = minY;
        bounds.width = maxX - minX;
        bounds.height = maxY - minY;

        return bounds;
    }
}
