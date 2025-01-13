package com.github.fabiitch.nz.gdx.render.g2d.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpriteUtils {
    public static void size(Sprite sprite, Vector2 size) {
        sprite.setSize(size.x, size.y);
    }

    public static void center(Sprite sprite, Vector2 position) {
        center(sprite, position.x, position.y);
    }

    public static void center(Sprite sprite, float posX, float poxY) {
        sprite.setPosition(posX - sprite.getWidth() / 2, poxY - sprite.getHeight() / 2);
    }

    public static void centerX(Sprite sprite, Vector2 position) {
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y);
    }


}
