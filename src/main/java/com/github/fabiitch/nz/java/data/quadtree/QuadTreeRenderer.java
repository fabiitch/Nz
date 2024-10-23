package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.gdx.render.shape.NzShapeRenderer;

public class QuadTreeRenderer {

    public Color colorQuads = Color.PURPLE;
    public Color colorRects = Color.GREEN;

    public boolean drawQuads = true;
    public boolean drawRects = true;
    public boolean drawUserData = true;
    public boolean drawQuadData = true;

    private NzShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    public QuadTreeRenderer() {
        this.shapeRenderer = new NzShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.spriteBatch = new SpriteBatch();
        this.bitmapFont = new BitmapFont();
    }

    public void render(QuadTree<?> quad, Camera camera) {
        if (drawQuads || drawRects) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin();
            if (drawQuads) {
                shapeRenderer.setColor(colorQuads);
                renderQuad(quad);
            }
            if (drawRects) {
                shapeRenderer.setColor(colorRects);
                renderRects(quad);
            }
            shapeRenderer.end();
        }

        if (drawUserData || drawQuadData) {
            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(camera.combined);
            renderUserData(quad);
            renderQuadData(quad);
            spriteBatch.end();
        }
    }

    private Array tmpArray = new Array();

    protected void renderQuadData(QuadTree<?> quad) {
        tmpArray.clear();
        quad.getAllValues(tmpArray);
        RectangleUtils.getCenter(quad.boundingRect, tmpV2);
        bitmapFont.draw(spriteBatch, "depth " + quad.depth, tmpV2.x, tmpV2.y - 20);
        bitmapFont.draw(spriteBatch, "total " + tmpArray.size, tmpV2.x, tmpV2.y);
        bitmapFont.draw(spriteBatch, "this " + quad.values.size, tmpV2.x, tmpV2.y + 20);
        if (quad.isSplit()) {
            renderQuadData(quad.ne);
            renderQuadData(quad.nw);
            renderQuadData(quad.se);
            renderQuadData(quad.sw);
        }
    }

    private final Vector2 tmpV2 = new Vector2();

    protected void renderUserData(QuadTree<?> quad) {
        for (int i = 0, n = quad.values.size; i < n; i++) {
            Rectangle rect = quad.rectValues.get(i);
            Object o = quad.values.get(i);
            RectangleUtils.getCenter(rect, tmpV2);
            bitmapFont.draw(spriteBatch, o.toString(), tmpV2.x, tmpV2.y);
        }

        if (quad.isSplit()) {
            renderUserData(quad.ne);
            renderUserData(quad.nw);
            renderUserData(quad.se);
            renderUserData(quad.sw);
        }
    }

    protected void renderRects(QuadTree<?> quad) {
        for (Rectangle rectValue : quad.rectValues) {
            shapeRenderer.rect(rectValue);
        }
        if (quad.isSplit()) {
            renderRects(quad.ne);
            renderRects(quad.nw);
            renderRects(quad.se);
            renderRects(quad.sw);
        }
    }

    public void renderQuad(QuadTree<?> quad) {
        if (quad.isSplit()) {
            renderQuad(quad.ne);
            renderQuad(quad.nw);
            renderQuad(quad.se);
            renderQuad(quad.sw);
        } else {
            shapeRenderer.rect(quad.boundingRect);
        }
    }

    public void dispose(){
        shapeRenderer.dispose();
        spriteBatch.dispose();
        bitmapFont.dispose();
    }
}
