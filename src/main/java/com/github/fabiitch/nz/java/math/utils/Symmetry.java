package com.github.fabiitch.nz.java.math.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.utils.CircleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.PolygonUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.SegmentUtils;
import com.github.fabiitch.nz.java.math.vectors.v2.V2;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Symmetry {

    private final static Vector2 tmp1V2 = new Vector2();
    private final static Vector2 tmp2V2 = new Vector2();

    public static Vector2 withPoint(Vector2 pos, Vector2 symmetryPoint) {
        Vector2 translation = V2.getTranslation(pos, symmetryPoint, tmp1V2);
        return new Vector2(pos).add(translation).add(translation);
    }

    public static Vector2 withSegment(Vector2 pos, Segment symmetrySegment) {
        Vector2 symmetryPoint = SegmentUtils.closestPoint(symmetrySegment, pos, tmp2V2);
        return withPoint(pos, symmetryPoint);
    }


    public static Rectangle withPoint(Rectangle rect, Vector2 symmetryPoint) {
        Vector2 centerRect = RectangleUtils.getCenter(rect, tmp2V2);
        Vector2 centerSymmetry = withPoint(centerRect, symmetryPoint);
        RectangleUtils.setCenter(rect, centerSymmetry);
        return rect;
    }

    public static Rectangle withSegment(Rectangle rect, Segment symmetrySegment) {
        Vector2 centerRect = RectangleUtils.getCenter(rect, tmp2V2);
        Vector2 symmetryPoint = SegmentUtils.closestPoint(symmetrySegment, centerRect, new Vector2());

        Vector2 centerSymmetry = withPoint(centerRect, symmetryPoint);
        RectangleUtils.setCenter(rect, centerSymmetry);
        return rect;
    }

    public Circle withPoint(Circle circle, Vector2 symmetryPoint) {
        Vector2 circleCenter = CircleUtils.getCenter(circle, tmp2V2);
        Vector2 centerSymmetry = withPoint(circleCenter, symmetryPoint);

        circle.x = centerSymmetry.x;
        circle.y = centerSymmetry.y;
        return circle;
    }

    public Circle withSegment(Circle circle, Segment symmetrySegment) {
        Vector2 circleCenter = CircleUtils.getCenter(circle, tmp2V2);
        Vector2 symmetryPoint = SegmentUtils.closestPoint(symmetrySegment, circleCenter, new Vector2());
        Vector2 centerSymmetry = withPoint(circleCenter, symmetryPoint);

        circle.x = centerSymmetry.x;
        circle.y = centerSymmetry.y;
        return circle;
    }

    public Polygon withPoint(Polygon polygon, Vector2 symmetryPoint) {
        Vector2 centerPolygon = PolygonUtils.getCenter(polygon, tmp2V2);
        Vector2 centerSymmetry = withPoint(centerPolygon, symmetryPoint);
        Vector2 translation = V2.getTranslation(centerPolygon, centerSymmetry, new Vector2());
        polygon.translate(translation.x, translation.y);

        return polygon;
    }


    public Polygon withSegment(Polygon polygon, Segment symmetrySegment) {
        Vector2 centerPolygon = PolygonUtils.getCenter(polygon, tmp2V2);
        Vector2 symmetryPoint = SegmentUtils.closestPoint(symmetrySegment, centerPolygon, new Vector2());
        Vector2 centerSymmetry = withPoint(centerPolygon, symmetryPoint);
        Vector2 translation = V2.getTranslation(centerPolygon, centerSymmetry, new Vector2());
        polygon.translate(translation.x, translation.y);

        return polygon;
    }

}
