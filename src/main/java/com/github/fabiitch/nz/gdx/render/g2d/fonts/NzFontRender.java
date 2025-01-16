package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NzFontRender {
    private final GlyphLayout layout = new GlyphLayout();
    private final BitmapFont font;
    private final SpriteBatch sb;

    public NzFontRender(SpriteBatch sb, BitmapFont font) {
        this.sb = sb;
        this.font = font;
    }

    public Vector2 toDrawCenter(Vector2 pos, String str, Vector2 result) {
        layout.setText(font, str);
        return result.set(pos.x - layout.width / 2, pos.y + layout.height / 2);
    }

    public Vector2 toDrawCenter(float x, float y, String str, Vector2 result) {
        layout.setText(font, str);
        return result.set(x - layout.width / 2, y + layout.height / 2);
    }

    public void drawCenter(float x, float y, String str) {
        layout.setText(font, str);
        font.draw(sb, str,
                x - layout.width / 2, y + layout.height / 2);
    }

    public void drawCenter(Vector2 position, String str) {
        drawCenter(position.x, position.y, str);
    }

    public float getTextWidth(String str) {
        layout.setText(font, str);
        return layout.width;
    }

}
