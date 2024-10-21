package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

public class IntersectorPolygon {
    private IntersectorPolygon() {
    }

    public static Intersector.MinimumTranslationVector tmpTranslationVector = new Intersector.MinimumTranslationVector();
    private static final float[] tmpRectVertices = new float[8];

    public static boolean circle(Circle circle, Polygon polygon) {
        return IntersectorCircle.polygon(circle, polygon);
    }

    public static boolean polygons(Polygon polygonA, Polygon polygonB, Intersector.MinimumTranslationVector translationVector) {
        return Intersector.overlapConvexPolygons(polygonA, polygonB, translationVector);
    }

    public static boolean rectangle(Polygon polygon, Rectangle rectangle) {
        return rectangle(polygon, rectangle, null);
    }

    public static boolean rectangle(Polygon polygon, Rectangle rectangle, Intersector.MinimumTranslationVector translationVector) {
        return Intersector.overlapConvexPolygons(polygon.getTransformedVertices(), RectangleUtils.getAsVertices(rectangle, tmpRectVertices), translationVector);
    }
}
