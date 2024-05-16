package com.github.fabiitch.nz.gdx.render.g2d;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FontDrawer {
    private final BitmapFont font;
    private final SpriteBatch sb;
    private final GlyphLayout layout = new GlyphLayout();

    public FontDrawer(BitmapFont font, SpriteBatch sb) {
        this.font = font;
        this.sb = sb;
    }

    public void drawAt(Vector2 pos, String str) {
        drawAt(pos.x, pos.y, str);
    }

    public void drawAt(float x, float y, String str) {
        font.draw(sb, str, x, y);
    }

    public void drawCenter(Vector2 pos, String str) {
        drawCenter(pos.x, pos.y, str);
    }

    public void drawCenter(float x, float y, String str) {
        layout.setText(font, str);
        font.draw(sb, str,
                x - layout.width / 2, y + layout.height / 2);
    }

    public float getWitdh(String str) {
        layout.setText(font, str);
        return layout.width;
    }


}
