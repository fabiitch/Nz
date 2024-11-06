package com.github.fabiitch.nz.java.math.shapes.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import com.github.fabiitch.nz.java.math.utils.direction.OrientationVector;
import com.github.fabiitch.nz.java.math.vectors.V2;

/**
 * D3-----C2
 * |------|
 * |------|
 * A0-----B1
 */
public class RectangleBuilder {
    private RectangleBuilder() {

    }

    public static Rectangle get(Rectangle rectangle) {
        return new Rectangle(rectangle);
    }

    public static Rectangle get(float witdh, float height) {
        return new Rectangle(0, 0, witdh, height);
    }

    public static Rectangle get(Vector2 v2) {
        return new Rectangle(0, 0, v2.x, v2.y);
    }

    public static Rectangle get(float x, float y, float witdh, float height) {
        return new Rectangle(x, y, witdh, height);
    }

    public static Rectangle get(Vector2 pos, float width, float height) {
        return new Rectangle(pos.x, pos.y, width, height);
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

    public static Rectangle[] getRectsAround(Rectangle rect, float sizeRect) {
        return getRectsAround(rect, sizeRect, null);
    }

    /**
     * BOT / TOP / LEFT / RIGHT
     */
    public static Rectangle[] getRectsAround(Rectangle rect, float sizeRect, Orientation orientation) {
        Rectangle[] rects = new Rectangle[4];

        Rectangle rectBot = new Rectangle(rect.x, rect.y - sizeRect, rect.width, sizeRect);
        rects[0] = rectBot;

        Rectangle rectTop = new Rectangle(rect.x, rect.y + rect.height, rect.width, sizeRect);
        rects[1] = rectTop;

        Rectangle rectLeft = new Rectangle(rect.x - sizeRect, rect.y, sizeRect, rect.getHeight());
        rects[2] = rectLeft;

        Rectangle rectRight = new Rectangle(rect.x + rect.getWidth(), rect.y, sizeRect, rect.getHeight());
        rects[3] = rectRight;
        if (orientation == Orientation.Vertical) {
            rectBot.x -= rectRight.getWidth();
            rectBot.width += rectRight.getWidth() * 2;
            rectTop.x -= rectRight.getWidth();
            rectTop.width += rectRight.getWidth() * 2;
        } else if (orientation == Orientation.Horizontal) {
            rectLeft.y -= rectBot.getHeight();
            rectLeft.height += rectBot.getHeight() * 2;
            rectRight.y -= rectBot.getHeight();
            rectRight.height += rectBot.getHeight() * 2;
        }
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

    public static Rectangle withOrientation(Orientation orientation, float orientationSize, float otherSize) {
        float width, height;
        if (orientation == Orientation.Horizontal) {
            width = orientationSize;
            height = otherSize;
        } else {
            width = otherSize;
            height = orientationSize;
        }
        return get(width, height);
    }

    public static Rectangle withOrientationCenter(Orientation orientation, Vector2 position, float orientationSize, float otherSize) {
        return withOrientation(orientation, position, orientationSize, otherSize, true);
    }

    public static Rectangle withOrientation(Orientation orientation, Vector2 position, float orientationSize, float otherSize, boolean centerPos) {
        Rectangle rectangle = withOrientation(orientation, orientationSize, otherSize);
        if (centerPos)
            RectangleUtils.setCenter(rectangle, position);
        else
            rectangle.setPosition(position);
        return rectangle;
    }

    public static Rectangle withOrientation(OrientationVector position, OrientationVector size, boolean centerPos) {
        Rectangle rectangle = get(size.toV2());
        if (centerPos)
            RectangleUtils.setCenter(rectangle, position.toV2());
        else
            rectangle.setPosition(position.toV2());
        return rectangle;
    }

    public static Rectangle getBorderRect(Rectangle rect, Direction direction, float directionSize) {
        Orientation rectOrientation = direction.getOtherOrientation();

        Vector2 centerTmp = RectangleUtils.getCenterTmp(rect);

        direction.addTo(centerTmp, RectangleUtils.getSize(rect, direction.getOrientation()) / 2 + directionSize / 2);
        Rectangle result = withOrientation(rectOrientation, centerTmp, RectangleUtils.getSize(rect, rectOrientation), directionSize, true);
        return result;
    }
}
