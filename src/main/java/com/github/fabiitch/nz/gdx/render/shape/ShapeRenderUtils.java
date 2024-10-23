package com.github.fabiitch.nz.gdx.render.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.java.math.shapes.Triangle;
import com.github.fabiitch.nz.java.math.vectors.V2;

public class ShapeRenderUtils {
    private final static Vector2 tmp1V2 = new Vector2();
    private final static Vector2 tmp2V2 = new Vector2();

    //TODO https://stackoverflow.com/questions/15733442/drawing-filled-polygon-with-libgdx
    //    https://stackoverflow.com/questions/16102692/libgdx-how-do-i-draw-a-filled-polygon-with-the-shaperenderer
    public static void triangleFilled(ShapeRenderer shapeRenderer, Triangle triangle) {
        ImmediateModeRenderer renderer = shapeRenderer.getRenderer();
        ShapeRenderer.ShapeType currentType = shapeRenderer.getCurrentType();
        Color color = shapeRenderer.getColor();
        if (currentType != ShapeRenderer.ShapeType.Filled && currentType != ShapeRenderer.ShapeType.Line)
            throw new GdxRuntimeException(
                    "Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        shapeRenderer.flush(); //TODO commit merge sur shapeRenderer
        float[] vertices = triangle.getTransformedVertices();
        final float firstX = vertices[0];
        final float firstY = vertices[1];
        if (currentType == ShapeRenderer.ShapeType.Line) {
            shapeRenderer.polygon(vertices);
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

    public static void crossPlus(NzShapeRenderer shapeRenderer, Vector2 position, float lenghtCross) {
        shapeRenderer.line(position.x - lenghtCross, position.y, position.x + lenghtCross, position.y);
        shapeRenderer.line(position.x, position.y - lenghtCross, position.x, position.y + lenghtCross);
    }

    public static void crossX(NzShapeRenderer shapeRenderer, Vector2 position, float lenghtCross) {
        Vector2 tmpDir = V2.tmp(1, 1).scl(lenghtCross);
        tmp1V2.set(position).add(tmpDir);
        tmp2V2.set(position).sub(tmpDir);
        shapeRenderer.line(tmp1V2, tmp2V2);

        tmpDir = V2.tmp(1, -1).scl(lenghtCross);
        tmp1V2.set(position).add(tmpDir);
        tmp2V2.set(position).sub(tmpDir);

        shapeRenderer.line(tmp1V2, tmp2V2);
    }

}
