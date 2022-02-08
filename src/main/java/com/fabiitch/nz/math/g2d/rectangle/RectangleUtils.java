package com.fabiitch.nz.math.g2d.rectangle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleUtils {

    public static Vector2 getCenter(Rectangle rect) { //TODO a voir si pas mal utilisé (new)
        return getCenter(rect, new Vector2());
    }

    public static Vector2 getCenter(Rectangle rect, Vector2 center) {
        return center.set(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

    public static Vector2 getRandomPos(Rectangle rect, Vector2 posReturn) {
        return posReturn.set(MathUtils.random(rect.x, rect.x + rect.width), rect.y + rect.height);
    }

}
