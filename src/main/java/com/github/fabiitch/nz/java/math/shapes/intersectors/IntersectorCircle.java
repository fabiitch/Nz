package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.*;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;

public class IntersectorCircle {

    private static final Vector2 tmp = new Vector2(); //TODO group tous les tmp
    private static final Vector2 tmp2 = new Vector2();
    private static final Vector2 tmp3 = new Vector2();
    private static final Vector2 tmp4 = new Vector2();
    private static final Vector2 tmp5 = new Vector2();

    private static final float[] tmpRectVertices = new float[8];


    public static boolean overlapStickRectangle(Circle c, Rectangle r){
        float closestX = c.x;
        float closestY = c.y;

        if (c.x < r.x) {
            closestX = r.x;
        } else if (c.x > r.x + r.width) {
            closestX = r.x + r.width;
        }

        if (c.y < r.y) {
            closestY = r.y;
        } else if (c.y > r.y + r.height) {
            closestY = r.y + r.height;
        }

        closestX = closestX - c.x;
        closestX *= closestX;
        closestY = closestY - c.y;
        closestY *= closestY;

        return closestX + closestY <= c.radius * c.radius;
    }

    public static boolean polygon(Circle circle, Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();
        int i = 0;
        while (i <= vertices.length / 2) {
            tmp.set(vertices[i], vertices[i + 1]);
            tmp2.set(vertices[i + 2], vertices[i + 3]);
            boolean b = Intersector.intersectSegmentCircle(tmp, tmp2, circle, null);
            if (b)
                return true;
            i += 2;
        }
        //last and first point
        tmp.set(vertices[0], vertices[1]);
        tmp2.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);
        return Intersector.intersectSegmentCircle(tmp, tmp2, circle, null);
    }

    /**
     * @return intersect
     * goOut set  to put circle out of  rectangle
     */
    public static boolean replaceFromRectangle(Circle circle, Rectangle rectangle, Vector2 goOut) {
        RectangleUtils.getAsVertices(rectangle, tmpRectVertices);
        Polygon tmpPolygon = PolygonUtils.getTmpPolygon(tmpRectVertices);
        return replaceFromPolygon(circle, tmpPolygon, goOut);
    }

    /**
     * @return intersect
     * goOut set to put circle out of polygons
     */
    public static boolean replaceFromPolygon(Circle circle, Polygon polygon, Vector2 goOut) {
        tmp3.set(circle.x, circle.y);
        float dstMin = Float.MAX_VALUE;

        float[] vertices = polygon.getTransformedVertices();
        int i = 0;
        while (i <= vertices.length / 2) {
            tmp.set(vertices[i], vertices[i + 1]);
            tmp2.set(vertices[i + 2], vertices[i + 3]);
            Intersector.nearestSegmentPoint(tmp, tmp2, tmp3, tmp4);
            float dstCenter = tmp4.dst(tmp3);
            if (dstCenter < dstMin) {
                tmp5.set(tmp4);
                dstMin = dstCenter;
            }
            i += 2;
        }
        //last and first point
        tmp.set(vertices[0], vertices[1]);
        tmp2.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);
        Intersector.nearestSegmentPoint(tmp, tmp2, tmp3, tmp4);
        float dstCenter = tmp4.dst(tmp3);
        if (dstCenter < dstMin) {
            tmp5.set(tmp4);
            dstMin = dstCenter;
        }

        if (dstMin > circle.radius)
            return false;

        tmp2.setZero();
        if (polygon.contains(circle.x, circle.y)) {
            V2.directionTo(tmp3, tmp5, tmp);
            tmp2.add(tmp.setLength(circle.radius + dstMin));
        } else {
            V2.directionTo(tmp5, tmp3, tmp);
            tmp2.add(tmp.setLength(circle.radius - dstMin));
        }
        goOut.set(tmp2);
        return true;
    }

    public static int segmentIntersection(Circle circle, Segment segment,
                                          Vector2 intersectionA, Vector2 intersectionB) {
        return segmentIntersection(circle, segment.a, segment.b, intersectionA, intersectionB);
    }


    public static boolean firstSegmentIntersection(Circle circle, Segment segment, Vector2 intersection) {
        int nbIntersection = segmentIntersection(circle, segment, intersection, null);
        return nbIntersection > 0;
    }

    /**
     * @return nbIntersection
     */
    public static int segmentIntersection(Circle circle, Vector2 pointA, Vector2 pointB,
                                          Vector2 intersectionA, Vector2 intersectionB) {
        float baX = pointB.x - pointA.x;
        float baY = pointB.y - pointA.y;
        float caX = circle.x - pointA.x;
        float caY = circle.y - pointA.y;

        float a = baX * baX + baY * baY;
        float bBy2 = baX * caX + baY * caY;
        float c = caX * caX + caY * caY - circle.radius * circle.radius;

        float pBy2 = bBy2 / a;
        float q = c / a;

        float disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return 0;
        }
        if (intersectionA == null) {
            return disc == 0 ? 1 : 2;
        }
        // if disc == 0 ... dealt with later
        float tmpSqrt = (float) Math.sqrt(disc);
        float abScalingFactor1 = -pBy2 + tmpSqrt;
        float abScalingFactor2 = -pBy2 - tmpSqrt;

        intersectionA.set(pointA.x - baX * abScalingFactor1, pointA.y
                - baY * abScalingFactor1);
        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
            return 1;
        }
        if (intersectionB == null)
            return 2;
        intersectionB.set(pointA.x - baX * abScalingFactor2, pointA.y
                - baY * abScalingFactor2);
        return 2;
    }
}
