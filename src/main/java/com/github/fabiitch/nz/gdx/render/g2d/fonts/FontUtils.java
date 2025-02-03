package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

//TODO remove
public class FontUtils {

    private static final GlyphLayout glyphLayout = new GlyphLayout();

    public static Vector2 toDrawCenter(BitmapFont font, Vector2 pos, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(pos.x - glyphLayout.width / 2, pos.y + glyphLayout.height / 2);
    }

    public static Vector2 toDrawCenter(BitmapFont font, float x, float y, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }

    public static void drawCenter(Vector2 center, String str, BitmapFont font, SpriteBatch sb) {
        drawCenter(center.x, center.y, str, font, sb);
    }

    public static void drawCenter(float x, float y, String str, BitmapFont font, SpriteBatch sb) {
        glyphLayout.setText(font, str);
        font.draw(sb, str,
                x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }

}
