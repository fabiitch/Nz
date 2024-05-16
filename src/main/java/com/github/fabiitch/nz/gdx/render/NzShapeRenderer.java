package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.java.math.shapes.Segment;
import com.github.fabiitch.nz.java.math.shapes.Triangle;
import com.github.fabiitch.nz.java.utils.Randoms;

public class NzShapeRenderer extends ShapeRenderer {
    public NzShapeRenderer() {
        super();
    }

    public NzShapeRenderer(int maxVertices) {
        super(maxVertices);
    }

    public NzShapeRenderer(int maxVertices, ShaderProgram defaultShader) {
        super(maxVertices, defaultShader);
    }

    public void segment(Segment segment) {
        line(segment.a, segment.b);
    }

    public void polyline(Polyline polyline) {
        polyline(polyline.getTransformedVertices());
    }

    public void triangle(Triangle triangle) {
        this.polygon(triangle.getTransformedVertices());
    }

    public void circle(Circle circle) {
        this.circle(circle.x, circle.y, circle.radius);
    }

    public void circle(Vector2 pos, float radius) {
        this.circle(pos.x, pos.y, radius);
    }

    public void polygon(Polygon polygon) {
        this.polygon(polygon.getTransformedVertices());
    }

    public void rect(Rectangle rect) {
        this.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public void randomColor() {
        this.setColor(Randoms.color(this.getColor()));
    }

    //TODO https://stackoverflow.com/questions/15733442/drawing-filled-polygon-with-libgdx
    //    https://stackoverflow.com/questions/16102692/libgdx-how-do-i-draw-a-filled-polygon-with-the-shaperenderer
    public void triangleFilled(Triangle triangle) {
        ImmediateModeRenderer renderer = getRenderer();
        ShapeType currentType = getCurrentType();
        Color color = getColor();
        if (currentType != ShapeType.Filled && currentType != ShapeType.Line)
            throw new GdxRuntimeException(
                    "Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        flush(); //TODO commit merge sur shapeRenderer
        float[] vertices = triangle.getTransformedVertices();
        final float firstX = vertices[0];
        final float firstY = vertices[1];
        if (currentType == ShapeType.Line) {
            super.polygon(vertices);
        } else {
            for (int i = 0, n = 6; i < n; i += 4) {
                final float x1 = vertices[i];
                final float y1 = vertices[i + 1];

                if (i + 2 >= 6) {
                    break;
                }
                final float x2 = vertices[i + 2];
                final float y2 = vertices[i + 3];

                final float x3;
                final float y3;

                if (i + 4 >= 6) {
                    x3 = firstX;
                    y3 = firstY;
                } else {
                    x3 = vertices[i + 4];
                    y3 = vertices[i + 5];
                }

                renderer.color(color);
                renderer.vertex(x1, y1, 0);
                renderer.color(color);
                renderer.vertex(x2, y2, 0);
                renderer.color(color);
                renderer.vertex(x3, y3, 0);
            }
        }
    }

    public NzShapeRenderer line() {
        this.set(ShapeType.Line);
        return this;
    }

    public NzShapeRenderer filled() {
        this.set(ShapeType.Filled);
        return this;
    }

    public NzShapeRenderer point() {
        this.set(ShapeType.Point);
        return this;
    }
}
