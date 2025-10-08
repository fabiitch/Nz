package com.github.fabiitch.nz.gdx.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TextureUtils {

    public static TextureRegionDrawable getDrawable(Texture texture) {
        return new TextureRegionDrawable(texture);
    }

    public static Texture createVerticalGradient(int width, int height, Color top, Color bottom) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        for (int y = 0; y < height; y++) {
            float t = (float) y / (height - 1);
            float r = MathUtils.lerp(bottom.r, top.r, t);
            float g = MathUtils.lerp(bottom.g, top.g, t);
            float b = MathUtils.lerp(bottom.b, top.b, t);
            float a = MathUtils.lerp(bottom.a, top.a, t);
            pixmap.setColor(r, g, b, a);
            pixmap.drawLine(0, y, width, y);
        }

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


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
}
