package com.github.fabiitch.nz.gdx.render.g2d.fonts;

import com.badlogic.gdx.graphics.Color;
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

    public void drawCenter(float x, float y, String str) {
        layout.setText(font, str);
        font.draw(sb, str,
                x - layout.width / 2, y + layout.height / 2);
    }

    public void drawCenter(Vector2 position, String str) {
        drawCenter(position.x, position.y, str);
    }

    public void drawCenter(Vector2 position, String str, Color color) {
        drawCenter(position.x, position.y, str, color);
    }

    public void drawCenter(float x, float y, String str, Color color) {
        Color oldColor = font.getColor();
        font.setColor(color);

        layout.setText(font, str);
        font.draw(sb, str,
                x - layout.width / 2, y + layout.height / 2);

        font.setColor(oldColor);
    }


    public float getTextWidth(String str) {
        layout.setText(font, str);
        return layout.width;
    }

    public float getTextHeight(String str) {
        layout.setText(font, str);
        return layout.height;
    }

    public Vector2 getTextSize(String str) {
        layout.setText(font, str);
        return new Vector2(layout.width, layout.height);
    }
}
