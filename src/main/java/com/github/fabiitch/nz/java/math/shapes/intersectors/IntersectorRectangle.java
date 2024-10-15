package com.github.fabiitch.nz.java.math.shapes.intersectors;

import com.badlogic.gdx.math.*;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.RectangleUtils;

public class IntersectorRectangle { //TODO group tmp
    private static final Vector2 tmp1 = new Vector2();
    private static final Vector2 tmp2 = new Vector2();
    private static final Vector2 tmp3 = new Vector2();

    private static final float[] tmpVerticesArray = new float[8];
    private static final float[] tmp2VerticesArray = new float[8];

    public static boolean overlapStickCircle(Rectangle r, Circle c) {
        return IntersectorCircle.overlapStickRectangle(c, r);
    }

    public static boolean polygon(Rectangle rectangle, Polygon polygon) {
        return Intersector.overlapConvexPolygons(RectangleUtils.getAsVertices(rectangle, tmpVerticesArray), polygon.getTransformedVertices(), null);
    }

    public static boolean polygon(Rectangle rectangle, Polygon polygon, Intersector.MinimumTranslationVector translationVector) {
        return Intersector.overlapConvexPolygons(RectangleUtils.getAsVertices(rectangle, tmpVerticesArray), polygon.getTransformedVertices(), translationVector);
    }

    //TODO faire la methode maison je crois
    public static boolean rectangles(Rectangle rectangle1, Rectangle rectangle2, Intersector.MinimumTranslationVector translationVector) {
        translationVector.normal.setZero();
        if (RectangleUtils.overlapsStick(rectangle1, rectangle2)) {
            Intersector.overlapConvexPolygons(RectangleUtils.getAsVertices(rectangle1, tmpVerticesArray), RectangleUtils.getAsVertices(rectangle2, tmp2VerticesArray), translationVector);
            return true;
        }
        return false;
    }

    public static boolean rectangles(Rectangle rectangle1, Rectangle rectangle2, Rectangle result) {//TODO mergeRect??
        if (RectangleUtils.overlapsStick(rectangle1, rectangle2)) {
            result.x = Math.max(rectangle1.x, rectangle2.x);
            result.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - result.x;
            result.y = Math.max(rectangle1.y, rectangle2.y);
            result.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - result.y;
            return true;
        }
        return false;
    }
}
