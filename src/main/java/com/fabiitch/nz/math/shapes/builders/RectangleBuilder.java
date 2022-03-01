package com.fabiitch.nz.math.shapes.builders;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fabiitch.nz.math.shapes.utils.RectangleUtils;
import com.fabiitch.nz.math.vectors.V2;

public class RectangleBuilder {
    private RectangleBuilder() {

    }

    public static Rectangle randomInside(Rectangle rectangle) {
        Vector2 randomPos = RectangleUtils.getRandomPos(rectangle, V2.tmp);
        float maxWidth = RectangleUtils.getXMax(rectangle) - randomPos.x;
        float maxHeight = RectangleUtils.getYMax(rectangle) - randomPos.y;

        float width = Math.min(MathUtils.random(maxWidth), maxWidth);
        float height = Math.min(MathUtils.random(maxHeight), maxHeight);
        System.out.println(new Rectangle(randomPos.x, randomPos.y, width, height));
        return new Rectangle(randomPos.x, randomPos.y, width, height);
    }
}
