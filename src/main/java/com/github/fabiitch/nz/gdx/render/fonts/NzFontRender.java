package com.github.fabiitch.nz.gdx.render.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NzFontRender {
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final BitmapFont font;
    private final SpriteBatch sb;

    public NzFontRender(SpriteBatch sb, BitmapFont font) {
        this.sb = sb;
        this.font = font;
    }

    public Vector2 toDrawCenter(Vector2 pos, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(pos.x - glyphLayout.width / 2, pos.y + glyphLayout.height / 2);
    }

    public Vector2 toDrawCenter(float x, float y, String str, Vector2 result) {
        glyphLayout.setText(font, str);
        return result.set(x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }

    public void drawCenter(float x, float y, String str) {
        glyphLayout.setText(font, str);
        font.draw(sb, str,
                x - glyphLayout.width / 2, y + glyphLayout.height / 2);
    }

    public void drawCenter(Vector2 position, String str) {
        drawCenter(position.x, position.y, str);
    }
}
