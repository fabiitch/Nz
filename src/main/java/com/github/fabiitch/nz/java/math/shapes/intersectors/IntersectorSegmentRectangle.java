package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.SegmentUtils;
import com.github.fabiitch.nz.java.math.vectors.V;

public class IntersectorSegmentRectangle {

    private IntersectorSegmentRectangle() {

    }

    // TODO a voir les static pour mettre sur des pools
    private static final Vector2 tmp1 = new Vector2();
    private static final Vector2 tmp2 = new Vector2();
    private static final Vector2 tmp3 = new Vector2();
    private static final Vector2 tmp4 = new Vector2();

    private static final Array<Vector2> arrayTmp = new Array<>(4); //TODO

    private static final Segment tmpSeg = new Segment();

    public static boolean farthest(Segment segment, Rectangle rectangle, Vector2 intersection,
                                   Segment rectangleSegment) {
        return test(segment, rectangle, intersection, rectangleSegment, false);
    }

    public static boolean closest(Segment segment, Rectangle rectangle, Vector2 intersection,
                                  Segment rectangleSegment) {
        return test(segment, rectangle, intersection, rectangleSegment, true);
    }

    public static boolean farthest(Segment segment, Rectangle rectangle, Vector2 intersection) {
        return test(segment, rectangle, intersection, null, false);
    }

    public static boolean closest(Segment segment, Rectangle rectangle, Vector2 intersection) {
        return test(segment, rectangle, intersection, null, true);
    }

    private static void getSegmentIntersection(Vector2 insersection, Rectangle rect, Segment toSet) {
        if (insersection.equals(tmp1)) {
            RectangleUtils.getVerticalLeft(rect, toSet);
        } else if (insersection.equals(tmp2)) {
            RectangleUtils.getHorizontalBot(rect, toSet);
        } else if (insersection.equals(tmp3)) {
            RectangleUtils.getVerticalRight(rect, toSet);
        } else if (insersection.equals(tmp4)) {
            RectangleUtils.getHorizontalTop(rect, toSet);
        }
    }

    private static boolean test(Segment segment, Rectangle rect, Vector2 intersection, Segment rectangleSegment,
                                boolean closest) {
        arrayTmp.clear();
        int intersectionCount = 0;
        if (instersectVerticalLeft(segment, rect, tmp1)) {
            intersectionCount++;
            arrayTmp.add(tmp1);
        }
        if (instersectHorizontalBot(segment, rect, tmp2)) {
            intersectionCount++;
            arrayTmp.add(tmp2);
        }
        if (intersectionCount >= 2) {
            intersection.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersection, rect, rectangleSegment);
            return true;
        }
        if (instersectVercticalRight(segment, rect, tmp3)) {
            intersectionCount++;
            arrayTmp.add(tmp3);
        }
        if (intersectionCount >= 2) {
            intersection.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersection, rect, rectangleSegment);
            return true;
        }
        if (instersectHorizontalTop(segment, rect, tmp4)) {
            intersectionCount++;
            arrayTmp.add(tmp4);
        }
        if (intersectionCount > 0) {
            intersection.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersection, rect, rectangleSegment);
        }
        return intersectionCount > 0;
    }

    public static boolean instersectVerticalLeft(Segment segment, Rectangle rect, Vector2 intersection) {
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rect.x, rect.y, rect.x, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, intersection);
    }

    public static boolean instersectHorizontalBot(Segment segment, Rectangle rect, Vector2 intersection) {
        float rectangleEndX = rect.x + rect.width;
        tmpSeg.set(rect.x, rect.y, rectangleEndX, rect.y);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, intersection);
    }

    public static boolean instersectVercticalRight(Segment segment, Rectangle rect, Vector2 intersection) {
        float rectangleEndX = rect.x + rect.width;
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rectangleEndX, rect.y, rectangleEndX, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, intersection);
    }

    public static boolean instersectHorizontalTop(Segment segment, Rectangle rect, Vector2 intersection) {
        float rectangleEndX = rect.x + rect.width;
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rect.x, rectangleEndY, rectangleEndX, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, intersection);
    }
}
