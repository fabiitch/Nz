package com.github.fabiitch.nz.java.math.shapes.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.vectors.V2;

public class RectangleBuilder {
    private RectangleBuilder() {

    }

    public static Rectangle get(Rectangle rectangle) {
        return new Rectangle(rectangle);
    }

    public static Rectangle get(float witdh, float height) {
        return new Rectangle(0, 0, witdh, height);
    }

    public static Rectangle get(float x, float y, float witdh, float height) {
        return new Rectangle(x, y, witdh, height);
    }

    public static Rectangle get(Vector2 pos, float witdh, float height) {
        return new Rectangle(pos.x, pos.y, witdh, height);
    }

    public static Rectangle get(float x, float y, Vector2 size) {
        return new Rectangle(x, y, size.x, size.y);
    }

    public static Rectangle fromCenter(float centerX, float centerY, float width, float height) {
        return new Rectangle(centerX - width / 2, centerY - height / 2, width, height);
    }

    public static Rectangle fromCenter(Vector2 center, float width, float height) {
        return fromCenter(center.x, center.y, width, height);
    }

    /**
     * BOT / TOP / LEFT / RIGHT
     */
    public static Rectangle[] getRectsAround(Rectangle rect, float sizeRect) {
        Rectangle[] rects = new Rectangle[4];

        Rectangle rectBot = new Rectangle(rect.x, rect.y - sizeRect, rect.width, sizeRect);
        rects[0] = rectBot;

        Rectangle rectTop = new Rectangle(rect.x, rect.y + rect.height, rect.width, sizeRect);
        rects[1] = rectTop;

        Rectangle rectLeft = new Rectangle(rect.x - sizeRect, rect.y, sizeRect, rect.getHeight());
        rects[2] = rectLeft;

        Rectangle rectRight = new Rectangle(rect.x + rect.getWidth(), rect.y, sizeRect, rect.getHeight());
        rects[3] = rectRight;
        return rects;
    }

    public static Rectangle randomInside(Rectangle rectangle) {
        Vector2 randomPos = RectangleUtils.getRandomPos(rectangle, V2.tmp);
        float maxWidth = RectangleUtils.getXMax(rectangle) - randomPos.x;
        float maxHeight = RectangleUtils.getYMax(rectangle) - randomPos.y;

        float width = Math.min(MathUtils.random(maxWidth), maxWidth);
        float height = Math.min(MathUtils.random(maxHeight), maxHeight);
        return new Rectangle(randomPos.x, randomPos.y, width, height);
    }

    public static Rectangle screen() {
        return screen(false);
    }

    public static Rectangle screen(boolean centerAs0) {
        Rectangle rect = new Rectangle();
        if (centerAs0) {
            return RectangleBuilder.fromCenter(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            return rect.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public static Rectangle screen(Camera camera, boolean centerAs0) {
        Rectangle rect = new Rectangle();
        if (centerAs0) {
            return RectangleBuilder.fromCenter(camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);
        } else {
            return rect.set(camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);
        }
    }
}
