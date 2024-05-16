package com.github.fabiitch.nz.gdx.render.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FontUtils {

    private static GlyphLayout glyphLayout = new GlyphLayout();

    public static Vector2 toDrawCenter(BitmapFont font, Vector2 pos, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(pos.x - glyphLayout.width / 2, pos.y + glyphLayout.height / 2);
    }

    public static Vector2 toDrawCenter(BitmapFont font, float x, float y, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }

    public static void drawCenter(float x, float y, String str, BitmapFont font, SpriteBatch sb) {
        glyphLayout.setText(font, str);
        font.draw(sb, str,
                x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }
}
