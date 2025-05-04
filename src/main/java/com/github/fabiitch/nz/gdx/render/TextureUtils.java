package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TextureUtils {

    public static Texture getColorTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    public static Texture getColorTexture(Color color, float alpha) {
        return getColorTexture(ColorUtils.get(color, alpha));
    }

    public static  void drawCenter(float x, float y, Texture texture , SpriteBatch spriteBatch) {


    }
}
