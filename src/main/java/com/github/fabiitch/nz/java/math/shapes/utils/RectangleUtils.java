package com.github.fabiitch.nz.java.math.shapes.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.NzMath;
import com.github.fabiitch.nz.java.math.angle.AngleUtils;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import com.github.fabiitch.nz.java.math.vectors.V2;

//TODO groupé les math tmpV1 vector segment ect

/**
 * D3-----C2
 * |------|
 * |------|
 * A0-----B1
 */
public class RectangleUtils {

    private static final Vector2 tmpV1 = new Vector2();
    private static final Segment tmpSegment = new Segment();


    private RectangleUtils() {
    }


    public static boolean isALine(Rectangle rectangle) {
        return rectangle.getWidth() == 0 || rectangle.getHeight() == 0;
    }

    public static Vector2 getVertex(Rectangle rect, int vertexNum, Vector2 result) {
        vertexNum %= 4;
        if (vertexNum == 0) {
            return getA(rect, result);
        }
        if (vertexNum == 1) {
            return getB(rect, result);
        }
        if (vertexNum == 2) {
            return getC(rect, result);
        }
        return getD(rect, result);
    }

    //CORNER METHOD
    public static Vector2 getTopRight(Rectangle rect, Vector2 pos) {
        return getC(rect, pos);
    }

    public static Vector2 getTopLeft(Rectangle rect, Vector2 pos) {
        return getD(rect, pos);
    }

    public static Vector2 getBotRight(Rectangle rect, Vector2 pos) {
        return getB(rect, pos);
    }

    public static Vector2 getBotLeft(Rectangle rect, Vector2 pos) {
        return getA(rect, pos);
    }

    public static Vector2 getTopRight(Rectangle r) {
        return new Vector2(r.x + r.width / 2, r.y + r.height / 2);
    }

    public static Vector2 getTopLeft(Rectangle rect) {
        return getD(rect, new Vector2());
    }

    public static Vector2 getBotRight(Rectangle rect) {
        return getB(rect, new Vector2());
    }

    public static Vector2 getBotLeft(Rectangle rect) {
        return getA(rect, new Vector2());
    }

    // ============ MIDDLE METHOD

    public static Vector2 getTopMiddle(Rectangle r) {
        return new Vector2(r.x + r.width / 2, r.y + r.height);
    }

    public static Vector2 getBotMiddle(Rectangle r) {
        return new Vector2(r.x + r.width / 2, r.y);
    }

    public static Vector2 getRightMiddle(Rectangle r) {
        return new Vector2(r.x + r.width, r.y + r.height / 2);
    }

    public static Vector2 getLeftMiddle(Rectangle r) {
        return new Vector2(r.x, r.y + r.height / 2);
    }

    /**
     * getBotLeft()
     */
    public static Vector2 getA(Rectangle rect, Vector2 pos) {
        return pos.set(rect.x, rect.y);
    }

    /**
     * getBotRight()
     */
    public static Vector2 getB(Rectangle rect, Vector2 pos) {
        return pos.set(rect.x + rect.width, rect.y);
    }

    /**
     * getTopRight()
     */
    public static Vector2 getC(Rectangle rect, Vector2 pos) {
        return pos.set(rect.x + rect.width, rect.y + rect.height);
    }

    /**
     * getTopLeft()
     */
    public static Vector2 getD(Rectangle rect, Vector2 pos) {
        return pos.set(rect.x, rect.y + rect.height);
    }

    public static Segment getAB(Rectangle rect, Segment segment) {
        getA(rect, segment.a);
        getB(rect, segment.b);
        return segment;
    }

    public static Segment getAC(Rectangle rect, Segment segment) {
        getA(rect, segment.a);
        getC(rect, segment.b);
        return segment;
    }

    public static Segment getBC(Rectangle rect, Segment segment) {
        getB(rect, segment.a);
        getC(rect, segment.b);
        return segment;
    }

    public static Segment getCD(Rectangle rect, Segment segment) {
        getC(rect, segment.a);
        getD(rect, segment.b);
        return segment;
    }

    public static Segment getAD(Rectangle rect, Segment segment) {
        getA(rect, segment.a);
        getD(rect, segment.b);
        return segment;
    }

    public static Segment getDB(Rectangle rect, Segment segment) {
        getD(rect, segment.a);
        getB(rect, segment.b);
        return segment;
    }

    public static Segment getEdgeVerticalBot(Rectangle rect, Segment segment) {
        return getAB(rect, segment);
    }

    public static Segment getEdgeVerticalTop(Rectangle rect, Segment segment) {
        return getCD(rect, segment);
    }

    public static Segment getEdgeHorizontalRight(Rectangle rect, Segment segment) {
        return getBC(rect, segment);
    }

    public static Segment getEdgeHorizontalLeft(Rectangle rect, Segment segment) {
        return getAD(rect, segment);
    }

    public static Vector2 getCenterRelative(Rectangle rect, Vector2 center) {
        return center.set(rect.width / 2, rect.height / 2);
    }

    public static Vector2 getCenterTmp(Rectangle rect) {
        return getCenter(rect, tmpV1);
    }

    public static Vector2 getCenter(Rectangle rect) {
        return V2.v(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

    public static Vector2 getCenter(Rectangle rect, Vector2 center) {
        return center.set(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

    public static Vector2 getPositionTmp(Rectangle rect) {
        return rect.getPosition(tmpV1);
    }

    public static Vector2 getPosition(Rectangle rect) {
        return rect.getPosition(new Vector2());
    }

    public static Rectangle setCenter(Rectangle rect, Vector2 newCenter) {
        return setCenter(rect, newCenter.x, newCenter.y);
    }

    public static Rectangle setCenter(Rectangle rect, float x, float y) {
        rect.x = x - rect.width / 2;
        rect.y = y - rect.height / 2;
        return rect;
    }


    /**
     * return the closest point on edge
     */
    public static Vector2 closestPoint(Rectangle rectangle, Vector2 point, Vector2 result) {
        Vector2 closestPoint = result;
        Vector2 closestPointTmp = tmpV1;

        Segment horizontalBot = RectangleUtils.getHorizontalBot(rectangle, tmpSegment);
        SegmentUtils.closestPoint(horizontalBot, point, result);

        Segment horizontalTop = RectangleUtils.getHorizontalTop(rectangle, tmpSegment);
        SegmentUtils.closestPoint(horizontalTop, point, closestPointTmp);
        if (closestPointTmp.dst2(point) < closestPoint.dst2(point)) {
            closestPoint.set(closestPointTmp);
        }

        Segment verticalLeft = RectangleUtils.getVerticalLeft(rectangle, tmpSegment);
        SegmentUtils.closestPoint(verticalLeft, point, closestPointTmp);
        if (closestPointTmp.dst2(point) < closestPoint.dst2(point)) {
            closestPoint.set(closestPointTmp);
        }

        Segment verticalRight = RectangleUtils.getVerticalRight(rectangle, tmpSegment);
        SegmentUtils.closestPoint(verticalRight, point, closestPointTmp);
        if (closestPointTmp.dst2(point) < closestPoint.dst2(point)) {
            closestPoint.set(closestPointTmp);
        }
        return closestPoint;
    }

    public static Segment closestEdge(Rectangle rectangle, Segment segment, Segment result) {
        float dstMin, newDstCompare;

        Segment horizontalBot = RectangleUtils.getHorizontalBot(rectangle, result);
        dstMin = SegmentUtils.dstMin(horizontalBot, segment);
        if (NzMath.isZero(dstMin)) {
            return result;
        }

        Segment horizontalTop = RectangleUtils.getHorizontalTop(rectangle, tmpSegment);
        newDstCompare = SegmentUtils.dstMin(horizontalTop, segment);
        if (newDstCompare < dstMin) {
            if (NzMath.isZero(newDstCompare))
                return result.set(horizontalTop);
            dstMin = newDstCompare;
            result.set(horizontalTop);
        }

        Segment verticalRight = RectangleUtils.getVerticalRight(rectangle, tmpSegment);
        newDstCompare = SegmentUtils.dstMin(verticalRight, segment);
        if (newDstCompare < dstMin) {
            if (NzMath.isZero(newDstCompare))
                return result.set(verticalRight);
            dstMin = newDstCompare;
            result.set(verticalRight);
        }

        Segment verticalLeft = RectangleUtils.getVerticalLeft(rectangle, tmpSegment);
        newDstCompare = SegmentUtils.dstMin(verticalLeft, segment);
        if (newDstCompare < dstMin) {
            result.set(verticalLeft);
        }

        return result;
    }

    public static Segment closestEdge(Rectangle rectangle, Vector2 point, Segment result) {
        float minDst2, newDst2;
        Vector2 nextPointToTest = tmpV1;

        Segment horizontalBot = RectangleUtils.getHorizontalBot(rectangle, tmpSegment);
        SegmentUtils.closestPoint(horizontalBot, point, nextPointToTest);
        result.set(horizontalBot);
        minDst2 = nextPointToTest.dst2(point);
        if (NzMath.isZero(minDst2))
            return result;

        Segment horizontalTop = RectangleUtils.getHorizontalTop(rectangle, tmpSegment);
        SegmentUtils.closestPoint(horizontalTop, point, nextPointToTest);
        newDst2 = nextPointToTest.dst2(point);
        if (NzMath.isZero(minDst2))
            return result.set(horizontalTop);
        if (newDst2 < minDst2) {
            result.set(horizontalTop);
            minDst2 = newDst2;
        }

        Segment verticalLeft = RectangleUtils.getVerticalLeft(rectangle, tmpSegment);
        SegmentUtils.closestPoint(verticalLeft, point, nextPointToTest);
        newDst2 = nextPointToTest.dst2(point);
        if (NzMath.isZero(minDst2))
            return result.set(verticalLeft);
        if (newDst2 < minDst2) {
            result.set(verticalLeft);
            minDst2 = newDst2;
        }

        Segment verticalRight = RectangleUtils.getVerticalRight(rectangle, tmpSegment);
        SegmentUtils.closestPoint(verticalRight, point, nextPointToTest);
        newDst2 = nextPointToTest.dst2(point);
        if (newDst2 < minDst2)
            return result.set(verticalRight);

        return result;
    }

    public static int getNumClosestVertex(Rectangle rect, float x, float y, Vector2 vertexPos) {
        int vertextClosest = getNumClosestVertex(rect, x, y);
        if (vertextClosest == 1) {
            getA(rect, vertexPos);
        } else if (vertextClosest == 2) {
            getB(rect, vertexPos);
        } else if (vertextClosest == 3) {
            getC(rect, vertexPos);
        } else if (vertextClosest == 4) {
            getD(rect, vertexPos);
        }
        return vertextClosest;
    }

    public static int getNumClosestVertex(Rectangle rect, float x, float y) {
        int nb = 0;
        float dstA = getA(rect, tmpV1).dst2(x, y);
        float closest = dstA;

        float dstB = getB(rect, tmpV1).dst2(x, y);
        if (dstB < closest) {
            closest = dstB;
            nb = 1;
        }

        float dstC = getC(rect, tmpV1).dst2(x, y);
        if (dstC < closest) {
            closest = dstC;
            nb = 2;
        }
        float dstD = getD(rect, tmpV1).dst2(x, y);
        if (dstD < closest) {
            nb = 3;
        }
        return nb;
    }

    public static int isVertex(Rectangle rect, Vector2 point) {
        if (isPosA(rect, point))
            return 0;
        if (isPosB(rect, point))
            return 1;
        if (isPosC(rect, point))
            return 2;
        if (isPosD(rect, point))
            return 3;
        return -1;
    }

    public static boolean isCenter(Rectangle rect, Vector2 pos) {
        return MathUtils.isEqual(rect.x + rect.width / 2, pos.x)
                && MathUtils.isEqual(rect.y + rect.height / 2, pos.y);
    }

    public static boolean isPosA(Rectangle rect, Vector2 pos) {
        return MathUtils.isEqual(rect.x, pos.x)
                && MathUtils.isEqual(rect.y, pos.y);
    }

    public static boolean isPosB(Rectangle rect, Vector2 pos) {
        return MathUtils.isEqual(rect.x + rect.width, pos.x)
                && MathUtils.isEqual(rect.y, pos.y);
    }

    public static boolean isPosC(Rectangle rect, Vector2 pos) {
        return MathUtils.isEqual(rect.x + rect.width, pos.x)
                && MathUtils.isEqual(rect.y + rect.width, pos.y);
    }

    public static boolean isPosD(Rectangle rect, Vector2 pos) {
        return MathUtils.isEqual(rect.x, pos.x)
                && MathUtils.isEqual(rect.y + rect.width, pos.y);
    }

    public static Segment getHorizontalBot(Rectangle rect, Segment segment) {
        segment.set(rect.x, rect.y, rect.x + rect.width, rect.y);
        return segment;
    }

    public static Segment getHorizontalTop(Rectangle rect, Segment segment) {
        float aX = rect.x;
        float aY = rect.y + rect.height;
        float bX = rect.x + rect.width;
        float bY = rect.y + rect.height;
        segment.set(aX, aY, bX, bY);
        return segment;
    }

    public static Segment getVerticalLeft(Rectangle rect, Segment segment) {
        segment.set(rect.x, rect.y, rect.x, rect.y + rect.height);
        return segment;
    }

    public static float getDiagDst(Rectangle rectangle) {
        return (float) Math.sqrt(rectangle.width * rectangle.width + rectangle.height * rectangle.height);
    }

    public static float dstVertexCenter(Rectangle rectangle) {
        return getDiagDst(rectangle) / 2;
    }

    public static Segment getVerticalRight(Rectangle rect, Segment segment) {
        segment.set(rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height);
        return segment;
    }

    public static float[] getAsVertices(Rectangle rect) {
        return getAsVertices(rect, new float[8]);
    }

    /**
     * clockwise
     */
    public static float[] getAsVertices(Rectangle rect, float[] vertices) {
        vertices[0] = rect.x;
        vertices[1] = rect.y;

        vertices[2] = rect.x;
        vertices[3] = rect.y + rect.height;

        vertices[4] = rect.x + rect.width;
        vertices[5] = rect.y + rect.height;

        vertices[6] = rect.x + rect.width;
        vertices[7] = rect.y;
        return vertices;
    }

    public static float radiusCircleInside(Rectangle rectangle) {
        if (rectangle.width < rectangle.height)
            return rectangle.width / 2;
        else
            return rectangle.height / 2;
    }

    public static Circle getCircleInside(Rectangle rectangle) {
        return new Circle(getCenter(rectangle, tmpV1), radiusCircleInside(rectangle));
    }

    public static Rectangle floorCeil(Rectangle rect) {
        return rect.set(MathUtils.floor(rect.x), MathUtils.floor(rect.y),
                MathUtils.ceil(rect.width), MathUtils.ceil(rect.height));
    }

    public static Rectangle mergeFloorCeil(Rectangle root, Rectangle other) {
        float minX = (float) Math.floor(Math.min(root.x, other.x));
        float maxX = (float) Math.ceil(Math.max(root.x + root.width, other.x + other.width));
        root.x = minX;
        root.width = maxX - minX;

        float minY = (float) Math.floor(Math.min(root.y, other.y));
        float maxY = (float) Math.ceil(Math.max(root.y + root.height, other.y + other.height));
        root.y = minY;
        root.height = maxY - minY;
        return root;
    }

    public static boolean containsStick(Rectangle rect, float x, float y) {
        float xMinA = rect.x, xMaxA = xMinA + rect.width;
        float yMinA = rect.y, yMaxA = yMinA + rect.height;
        return ((x >= xMinA && x <= xMaxA) && (y >= yMinA && y <= yMaxA));
    }

    public static boolean containsStick(Rectangle rect, Vector2 pos) {
        return containsStick(rect, pos.x, pos.y);
    }

    public static boolean containsStick(Rectangle rectA, Rectangle rectB) {
        float xMinA = rectA.x, xMaxA = xMinA + rectA.width;
        float yMinA = rectA.y, yMaxA = yMinA + rectA.height;

        float xMinB = rectB.x, xMaxB = xMinB + rectB.width;
        float yMinB = rectB.y, yMaxB = yMinB + rectB.height;

        return ((xMinB >= xMinA && xMinB <= xMaxA) && (xMaxB >= xMinA && xMaxB <= xMaxA))
                && ((yMinB >= yMinA && yMinB <= yMaxA) && (yMaxB >= yMinA && yMaxB <= yMaxA));
    }

    public static boolean overlapsStick(Rectangle rectA, Rectangle rectB) {
        return rectA.x <= rectB.x + rectB.width
                && rectA.x + rectA.width >= rectB.x
                && rectA.y <= rectB.y + rectB.height
                && rectA.y + rectA.height >= rectB.y;
    }

    public static boolean stick(Rectangle rectA, Rectangle rectB) {
        Segment segA = new Segment();
        Segment sebB = new Segment();
        if (getAB(rectA, segA).hasCommonPart(getAB(rectB, sebB)))
            return true;
        if (getBC(rectA, segA).hasCommonPart(getBC(rectB, sebB)))
            return true;
        if (getCD(rectA, segA).hasCommonPart(getCD(rectB, sebB)))
            return true;
        if (getAD(rectA, segA).hasCommonPart(getAD(rectB, sebB)))
            return true;
        return false;
    }

    public static float[] toVertices(Rectangle rect, boolean setCenterRect) {
        return toVertices(rect.x, rect.y, rect.width, rect.height, setCenterRect);
    }

    public static float[] toVertices(float x, float y, float width, float height, boolean setCenterRect) {
        float[] vertices;
        if (setCenterRect) {
            vertices = new float[]{
                    x - width / 2, y - height / 2,
                    x + width / 2, y - height / 2,
                    x + width / 2, y + height / 2,
                    x - width / 2, y + height / 2};
        } else {
            vertices = new float[]{0, 0, width, 0, width, height, 0, height};
        }
        return vertices;
    }

    public static float[] toVertices(float width, float height, boolean setCenterRect) {
        float[] vertices;
        if (setCenterRect) {
            vertices = new float[]{-width / 2, -height / 2, width / 2, -height / 2, width / 2, height / 2, -width / 2, height / 2};
        } else {
            vertices = new float[]{0, 0, width, 0, width, height, 0, height};
        }
        return vertices;
    }

    public static float getMaxWidthHeight(Rectangle rect) {
        return Math.max(rect.width, rect.height);
    }

    /**
     * 1-8 = out
     * 9-12 = inside
     * 13 = center
     */
    public static int getRegion(Rectangle rect, Vector2 position) {
        int pos = getRegionOutside(rect, position);
        if (pos == 0)
            pos = getRegionInside(rect, position) + 8;
        return pos;
    }

    public static Segment getEdgeWithAngle(Rectangle rect, float angleDeg, Segment result) {
        float angle = AngleUtils.normaliseDeg(angleDeg);

        if (angle >= 315 || angle < 45) {
            return getVerticalRight(rect, result);
        } else if (angle >= 45 && angle < 135) {
            return getHorizontalTop(rect, result);
        } else if (angle >= 135 && angle < 225) {
            return getVerticalLeft(rect, result);
        } else {
            return getHorizontalBot(rect, result);
        }
    }

    /**
     * 0 = outside
     * 5 = center
     * ________
     * | 4 | 3 |
     * |_1_|_2_|
     */
    public static int getRegionInside(Rectangle rect, Vector2 position) {
        float x = position.x, y = position.y;
        float midX = rect.x + rect.width / 2, midY = rect.y + rect.height / 2;
        if (!rect.contains(x, y))
            return 0;
        if (isCenter(rect, tmpV1.set(x, y)))
            return 5;
        if (x > midX) {
            if (y > midY)
                return 3;
            else
                return 2;
        } else if (y > midY)
            return 4;
        else
            return 1;
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
    public static int getRegionOutside(Rectangle rect, Vector2 position) {
        float x = position.x, y = position.y;
        int result = 0;
        float rectX = rect.x, rectY = rect.y;
        if (x < rectX) {
            result = 5;
            if (y < rectY)
                result = 6;
            else if (y > rectY + rect.height)
                result = 4;
        } else if (x > rectX + rect.width) {
            result = 1;
            if (y < rectY)
                result = 8;
            else if (y > rectY + rect.height)
                result = 2;
        } else {
            if (y < rectY)
                result = 7;
            else if (y > rectY + rect.height)
                result = 3;
        }
        return result;
    }


    public static Vector2 getRandomPos(Rectangle rect, Vector2 v) {
        return v.set(MathUtils.random(rect.x, rect.x + rect.width), MathUtils.random(rect.y, rect.y + rect.height));
    }


    public static float getXMax(Rectangle rectangle) {
        return rectangle.x + rectangle.width;
    }

    public static float getYMax(Rectangle rectangle) {
        return rectangle.y + rectangle.height;
    }

    public static Rectangle scale(Rectangle rect, float scale) {
        return scale(rect, scale, false);
    }

    public static Rectangle scale(Rectangle rect, float scale, boolean replacePosition) {
        float newWitdh = rect.width * scale;
        float newHeight = rect.height * scale;
        if (replacePosition) {
            float diffX = rect.width - newWitdh;
            float diffY = rect.height - newHeight;
            translate(rect, diffX, diffY);
        }
        return rect.setSize(newWitdh, newHeight);
    }

    public static Rectangle translate(Rectangle rect, float x, float y) {
        rect.x += x;
        rect.y += y;
        return rect;
    }

    public static Rectangle translate(Rectangle rect, Vector2 vector2) {
        rect.x += vector2.x;
        rect.y += vector2.y;
        return rect;
    }

    public static Rectangle scale(float scale, Rectangle rect, boolean pos) {
        if (pos) {
            rect.x *= scale;
            rect.y *= scale;
        }
        return rect.setSize(rect.width * scale, rect.height * scale);
    }

    public static Vector2 posOnEdgeAngle(Rectangle rect, float angleDeg) {
        Vector2 centerRect = RectangleUtils.getCenter(rect, tmpV1);
        float dstMax = centerRect.dst(RectangleUtils.getA(rect, new Vector2()));
        Vector2 projectionFromCenter = new Vector2(1, 0).setAngleDeg(angleDeg).setLength(dstMax).add(centerRect);

        Segment tmpSegment = new Segment();
        Vector2 intersection = new Vector2();

        Segment segFromCenter = new Segment(centerRect, projectionFromCenter);

        Segment ab = RectangleUtils.getAB(rect, tmpSegment);
        if (SegmentUtils.getSegmentIntersection(segFromCenter, ab, intersection)) {
            return intersection;
        }
        Segment bc = RectangleUtils.getBC(rect, tmpSegment);
        if (SegmentUtils.getSegmentIntersection(segFromCenter, bc, intersection)) {
            return intersection;
        }
        Segment cd = RectangleUtils.getCD(rect, tmpSegment);
        if (SegmentUtils.getSegmentIntersection(segFromCenter, cd, intersection)) {
            return intersection;
        }
        Segment ad = RectangleUtils.getAD(rect, tmpSegment);
        if (SegmentUtils.getSegmentIntersection(segFromCenter, ad, intersection)) {
            return intersection;
        }

        return null;
    }

    public static boolean isInsideX(Rectangle rect, float x) {
        return x > rect.x && x < rect.x + rect.width;
    }

    public static boolean isInsideX(Rectangle rect, Rectangle rect2) {
        return isInsideX(rect, rect2.x) || isInsideX(rect, rect2.x + rect2.width);
    }

    public static boolean isInsideY(Rectangle rect, float y) {
        return y > rect.y && y < rect.y + rect.height;
    }

    public static boolean isInsideY(Rectangle rect, Rectangle rect2) {
        return isInsideY(rect, rect2.y) || isInsideY(rect, rect2.y + rect2.height);
    }

    public static Rectangle getOverlap(Rectangle rectA, Rectangle rectB) {
        // Vérifier s'il y a un chevauchement
        if (!rectA.overlaps(rectB)) {
            return null; // Pas de chevauchement
        }

        // Calculer les coordonnées du rectangle de chevauchement
        float overlapX = Math.max(rectA.x, rectB.x);
        float overlapY = Math.max(rectA.y, rectB.y);
        float overlapWidth = Math.min(rectA.x + rectA.width, rectB.x + rectB.width) - overlapX;
        float overlapHeight = Math.min(rectA.y + rectA.height, rectB.y + rectB.height) - overlapY;

        // Retourner le rectangle de chevauchement
        return new Rectangle(overlapX, overlapY, overlapWidth, overlapHeight);
    }


    public static float getMax(Rectangle rect, Orientation orientation) {
        if (orientation == Orientation.Horizontal)
            return getXMax(rect);
        return getYMax(rect);
    }

    public static float getSize(Rectangle rect, Orientation orientation) {
        if (orientation == Orientation.Horizontal)
            return rect.getWidth();
        return rect.getHeight();
    }

    public static float getSize(Rectangle rect, Direction direction) {
        if (direction.getOrientation() == Orientation.Horizontal)
            return rect.getWidth();
        return rect.getHeight();
    }

    public static Rectangle reverseWidthHeight(Rectangle rect) {
        float width = rect.width;
        float height = rect.height;
        rect.width = height;
        rect.height = width;
        return rect;
    }

    public static Segment getMiddleSegment(Rectangle rect, Orientation orientation) {
        if (orientation == Orientation.Vertical) {
            Vector2 a = new Vector2(rect.x + rect.width / 2, rect.y);
            Vector2 b = new Vector2(rect.x + rect.width / 2, rect.y + rect.getHeight());
            return new Segment(a, b);
        }
        Vector2 a = new Vector2(rect.x, rect.y + rect.getHeight() / 2);
        Vector2 b = new Vector2(rect.x + rect.width, rect.y + rect.getHeight() / 2);
        return new Segment(a, b);
    }


    public static float getArea(Rectangle rect) {
        return rect.width * rect.height;
    }


    public static Vector2 getCornerPosTmp(Rectangle rect, Direction directionA, Direction directionB) {
        if (directionA.getOrientation() == directionB.getOrientation())
            throw new IllegalArgumentException("Cant get corner on same orientation=" + directionA.getOrientation());
        Direction horizontal = directionA.isHorizontal() ? directionA : directionB;

        Direction vertical = directionA.isVertical() ? directionA : directionB;
        if (vertical == Direction.Top)
            if (horizontal == Direction.Right)
                return getTopRight(rect, tmpV1);
            else
                return getTopLeft(rect, tmpV1);
        if (horizontal == Direction.Right)
            return getBotRight(rect, tmpV1);
        return getBotLeft(rect, tmpV1);
    }


    public static Rectangle setSize(Rectangle rect, Orientation orientation, float size) {
        if (orientation == Orientation.Horizontal)
            rect.setWidth(size);
        else
            rect.setHeight(size);
        return rect;
    }

    public static String printSize(Rectangle rectangle) {
        return "(w=" + rectangle.width + ", h=" + rectangle.getHeight() + ")";
    }

    public static Vector2 getDirectionMiddle(Rectangle rectangle, Direction edge) {
        switch (edge) {
            case Top:
                return getTopMiddle(rectangle);
            case Bot:
                return getBotMiddle(rectangle);
            case Left:
                return getLeftMiddle(rectangle);
            case Right:
                return getRightMiddle(rectangle);
        }
        return null;
    }
}
