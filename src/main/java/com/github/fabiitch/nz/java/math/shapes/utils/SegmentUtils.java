package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;

public class SegmentUtils {

    public static Segment tmpSegment = new Segment();
    private static final Vector2 tmpV1 = new Vector2();

    private SegmentUtils() {
    }


    public static Vector2 closestPoint(Segment segment2D, Vector2 point, Vector2 result) {
        return Intersector.nearestSegmentPoint(segment2D.a, segment2D.b, point, result);
    }

    public static boolean getSegmentIntersection(Segment s1, Segment s2, Vector2 intersection) {
        return Intersector.intersectSegments(s1.a, s1.b, s2.a, s2.b, intersection);
    }

    public static float getAngleReflexionDeg(Segment segment, Vector2 direction) {
        Vector2 normal = segment.getNormal(tmpV1);
        if (normal.hasOppositeDirection(direction))
            normal.rotateDeg(180);
        float angleReflection = V2.angleDeg(normal) - (direction.angleDeg() - normal.angleDeg());
        return angleReflection;
    }

    public static float dstMin(Segment segment1, Segment segment2) {
        if (getSegmentIntersection(segment1, segment2, null)) {
            return 0;
        }
        float dst1aTo2 = segment2.dst(segment1.a);
        float dst1bTo2 = segment2.dst(segment1.b);
        float min1to2 = Math.min(dst1aTo2, dst1bTo2);

        float dst2aTo1 = segment1.dst(segment2.a);
        float dst2bTo1 = segment1.dst(segment2.b);
        float min2to1 = Math.min(dst2aTo1, dst2bTo1);

        return Math.min(min1to2, min2to1);
    }

}
