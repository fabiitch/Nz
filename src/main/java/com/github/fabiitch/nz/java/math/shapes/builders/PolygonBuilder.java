package com.github.fabiitch.nz.java.math.shapes.builders;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;

public class PolygonBuilder {

    private PolygonBuilder() {

    }

    public static Polygon rectangle(Rectangle rect, boolean setCenterRect) {
        return new Polygon(RectangleUtils.toVertices(rect, setCenterRect));
    }

    public static Polygon rectangle(float width, float height, boolean setCenterRect) {
        return new Polygon(RectangleUtils.toVertices(width, height, setCenterRect));
    }

    public static Polygon get(float... points) {
        return new Polygon(points);
    }

    public static Polygon get(Vector2... verticesV) {
        float[] vertices = new float[verticesV.length * 2];
        for (int i = 0; i < verticesV.length; i++) {
            vertices[i * 2] = verticesV[i].x;
            vertices[i * 2 + 1] = verticesV[i].y;
        }
        return new Polygon(vertices);
    }

    public static Polygon square(float witdh, boolean setCenterRect) {
        return rectangle(witdh, witdh, setCenterRect);
    }
}
