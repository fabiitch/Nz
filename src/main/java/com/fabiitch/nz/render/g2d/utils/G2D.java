package com.fabiitch.nz.render.g2d.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class G2D {

    public static void center(Sprite sprite, Vector2 position) {
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
    }

    public static void centerX(Sprite sprite, Vector2 position) {
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y);
    }
}
