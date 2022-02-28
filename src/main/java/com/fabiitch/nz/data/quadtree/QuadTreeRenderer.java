package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.fabiitch.nz.render.NzShapeRenderer;

public class QuadTreeRenderer {

    public Color colorQuads = Color.PURPLE;
    public Color colorRects = Color.GREEN;

    public boolean drawQuads =true;
    public boolean drawRects = true;
    public boolean drawUserData = false;

    public NzShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;

    public QuadTreeRenderer() {
        this.shapeRenderer  = new NzShapeRenderer();
    }

    public void render(QuadTree<?> quad, Camera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(colorQuads);
        shapeRenderer.begin();
        renderQuad(quad);
        shapeRenderer.end();
    }

    public void renderQuad(QuadTree<?> quad) {
        if (quad.isSplitted()) {
            renderQuad(quad.ne);
            renderQuad(quad.nw);
            renderQuad(quad.se);
            renderQuad(quad.sw);
        } else {
            shapeRenderer.rect(quad.boundingRect);
        }
    }
}
