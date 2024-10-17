package com.github.fabiitch.nz.java.math.shapes.utils.rectangle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.RectCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RectCenterUtils {


    public static RectCenter fromRect(Rectangle r) {
        return new RectCenter(r.x + r.width / 2, r.y + r.height / 2, r.width, r.height);
    }

    public static RectCenter set(Rectangle r) {
        return new RectCenter(r.x + r.width / 2, r.y + r.height / 2, r.width, r.height);
    }


    public static Vector2 getBotLeft(RectCenter r) {
        return new Vector2(r.getX() - r.getWidth() / 2, r.getY() - r.getHeight() / 2);
    }

    public static Vector2 getBotRight(RectCenter r) {
        return new Vector2(r.getX() + r.getWidth() / 2, r.getY() - r.getHeight() / 2);
    }

    public static Vector2 getTopLeft(RectCenter r) {
        return new Vector2(r.getX() - r.getWidth() / 2, r.getY() + r.getHeight() / 2);
    }


    public static Vector2 getTopRight(RectCenter r) {
        return new Vector2(r.getX() + r.getWidth() / 2, r.getY() + r.getHeight() / 2);
    }

    public static Vector2 getTopMiddle(RectCenter r) {
        return new Vector2(r.getX(), r.getY() + r.getHeight() / 2);
    }

    public static Vector2 getBotMiddle(RectCenter r) {
        return new Vector2(r.getX(), r.getY() - r.getHeight() / 2);
    }

    public static Vector2 getRightMiddle(RectCenter r) {
        return new Vector2(r.getX() + r.getWidth() / 2, r.getY());
    }

    public static Vector2 getLeftMiddle(RectCenter r) {
        return new Vector2(r.getX() - r.getWidth() / 2, r.getY());
    }
}
