package com.fabiitch.nz.math.g2d.rectangle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleBuilder {
    public static Rectangle createFromCenter(float centerX, float centerY, float width, float height) {
        return new Rectangle(centerX - width / 2, centerY - height / 2, width, height);
    }

    public static Rectangle createFromCenter(Vector2 center, float width, float height) {
        return createFromCenter(center.x, center.y, width, height);
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
}
