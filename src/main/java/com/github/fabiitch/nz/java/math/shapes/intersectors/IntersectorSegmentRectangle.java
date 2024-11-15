package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.SegmentUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.vectors.V;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntersectorSegmentRectangle {

    // TODO a voir les static pour mettre sur des pools
    private static final Vector2 tmp1 = new Vector2();
    private static final Vector2 tmp2 = new Vector2();
    private static final Vector2 tmp3 = new Vector2();
    private static final Vector2 tmp4 = new Vector2();

    private static final Array<Vector2> arrayTmp = new Array<>(4); //TODO

    private static final Segment tmpSeg = new Segment();

    public static boolean test(Segment segment, Rectangle rectangle) {
        if (intersectBotEdge(segment, rectangle, null))
            return true;
        if (intersectTopEdge(segment, rectangle, null))
            return true;
        if (intersectRightEdge(segment, rectangle, null))
            return true;
        if (intersectLeftEdge(segment, rectangle, null))
            return true;
        return false;
    }

    public static boolean farthest(Segment segment, Rectangle rectangle, Vector2 intersection, Segment rectangleSegment) {
        return test(segment, rectangle, intersection, rectangleSegment, false);
    }

    public static boolean closest(Segment segment, Rectangle rectangle, Vector2 intersection, Segment rectangleSegment) {
        return test(segment, rectangle, intersection, rectangleSegment, true);
    }

    public static boolean farthest(Segment segment, Rectangle rectangle, Vector2 intersection) {
        return test(segment, rectangle, intersection, null, false);
    }

    public static boolean closest(Segment segment, Rectangle rectangle, Vector2 intersection) {
        return test(segment, rectangle, intersection, null, true);
    }

    private static void getSegmentIntersection(Vector2 intersection, Rectangle rect, Segment result) {
        if (intersection.equals(tmp1)) {
            RectangleUtils.getVerticalLeft(rect, result);
        } else if (intersection.equals(tmp2)) {
            RectangleUtils.getHorizontalBot(rect, result);
        } else if (intersection.equals(tmp3)) {
            RectangleUtils.getVerticalRight(rect, result);
        } else if (intersection.equals(tmp4)) {
            RectangleUtils.getHorizontalTop(rect, result);
        }
    }

    private static boolean test(Segment segment, Rectangle rect, Vector2 intersectionResult, Segment rectangleSegment, boolean closest) {
        arrayTmp.clear();
        int intersectionCount = 0;
        if (intersectLeftEdge(segment, rect, tmp1)) {
            intersectionCount++;
            arrayTmp.add(tmp1);
        }
        if (intersectBotEdge(segment, rect, tmp2)) {
            intersectionCount++;
            arrayTmp.add(tmp2);
        }
        if (intersectionCount >= 2) {
            intersectionResult.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersectionResult, rect, rectangleSegment);
            return true;
        }
        if (intersectRightEdge(segment, rect, tmp3)) {
            intersectionCount++;
            arrayTmp.add(tmp3);
        }
        if (intersectionCount >= 2) {
            intersectionResult.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersectionResult, rect, rectangleSegment);
            return true;
        }
        if (intersectTopEdge(segment, rect, tmp4)) {
            intersectionCount++;
            arrayTmp.add(tmp4);
        }
        if (intersectionCount > 0) {
            intersectionResult.set(closest ? V.getClosest(segment.a, arrayTmp)
                    : V.getFarthest(segment.a, arrayTmp));
            if (rectangleSegment != null)
                getSegmentIntersection(intersectionResult, rect, rectangleSegment);
        }
        return intersectionCount > 0;
    }

    public static boolean intersectEdge(Segment segment, Rectangle rect, Direction directionEdge, Vector2 result) {
        switch (directionEdge) {
            case Top:
                return intersectTopEdge(segment, rect, result);
            case Bot:
                return intersectBotEdge(segment, rect, result);
            case Left:
                return intersectLeftEdge(segment, rect, result);
            case Right:
                return intersectRightEdge(segment, rect, result);
        }
        return false;
    }


    public static boolean intersectLeftEdge(Segment segment, Rectangle rect, Vector2 result) {
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rect.x, rect.y, rect.x, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, result);
    }

    public static boolean intersectBotEdge(Segment segment, Rectangle rect, Vector2 result) {
        float rectangleEndX = rect.x + rect.width;
        tmpSeg.set(rect.x, rect.y, rectangleEndX, rect.y);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, result);
    }

    public static boolean intersectRightEdge(Segment segment, Rectangle rect, Vector2 result) {
        float rectangleEndX = rect.x + rect.width;
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rectangleEndX, rect.y, rectangleEndX, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, result);
    }

    public static boolean intersectTopEdge(Segment segment, Rectangle rect, Vector2 result) {
        float rectangleEndX = rect.x + rect.width;
        float rectangleEndY = rect.y + rect.height;
        tmpSeg.set(rect.x, rectangleEndY, rectangleEndX, rectangleEndY);
        return SegmentUtils.getSegmentIntersection(segment, tmpSeg, result);
    }
}
