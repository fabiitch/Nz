package com.github.fabiitch.nz.java.utils.randoms;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RectangleRandoms {

    public static Rectangle get(float minWidth, float maxWidth, float minHeight, float maxHeight) {
        return new Rectangle(0, 0, MathUtils.random(minWidth, maxWidth), MathUtils.random(minHeight, maxHeight));
    }

    public static Vector2 getRandomPos(Rectangle r) {
        return RectangleUtils.getRandomPos(r, new Vector2());
    }

    public static void setRandomPos(Rectangle r, Rectangle toPlace) {
        Vector2 randomPos = getRandomPos(r, toPlace.width, toPlace.height);
        toPlace.setPosition(randomPos.x, randomPos.y);
    }

    public static Vector2 getRandomPos(Rectangle r, float width, float height) {
        float minX = r.x;
        float maxX = r.x + r.width - width;
        float minY = r.y;
        float maxY = r.y + r.height- height;
        return new Vector2(MathUtils.random(minX, maxX), MathUtils.random(minY, maxY));
    }
}
