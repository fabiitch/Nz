package com.github.fabiitch.nz.java.data.quadtree.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.render.shape.Frustum2D;
import com.github.fabiitch.nz.gdx.render.shape.NzShapeRenderer;
import com.github.fabiitch.nz.java.data.quadtree.QuadTree;
import com.github.fabiitch.nz.java.data.quadtree.QuadRectangleValue;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.Getter;

public class QuadTreeRenderer<T extends QuadRectangleValue> {

    @Getter
    private final QuadTreeRenderConfig<T> config;
    private final NzShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final BitmapFont fontValuesData, fontQuadsData;
    private final Frustum2D frustum2D = new Frustum2D();
    public QuadTreeRenderer(QuadTreeRenderConfig<T> config) {
        this.config = config;
        this.shapeRenderer = config.getShapeRenderer() != null ? config.getShapeRenderer() : new NzShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);

        this.spriteBatch = config.getSpriteBatch() != null ? config.getSpriteBatch() : new SpriteBatch();

        this.fontValuesData = config.getFontValuesData() != null ? config.getFontValuesData() : new BitmapFont();
        this.fontQuadsData = config.getFontQuadsData() != null ? config.getFontQuadsData() : new BitmapFont();
    }

    public QuadTreeRenderer() {
        this(new QuadTreeRenderConfig<>());
    }


    public void render(QuadTree<T> quad, OrthographicCamera camera) {
        frustum2D.update(camera);

        if (config.isDrawQuads() || config.isDrawRectangles()) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin();
            if (config.isDrawQuads()) {
                shapeRenderer.setColor(config.getColorQuad());
                renderQuad(quad);
            }
            if (config.isDrawRectangles()) {
                shapeRenderer.setColor(config.getColorRects());
                renderRects(quad);
            }
            shapeRenderer.end();
        }

        if (config.isDrawValuesData() || config.isDrawQuadData()) {
            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(camera.combined);
            if (config.isDrawValuesData())
                renderUserData(quad);
            if (config.isDrawQuadData())
                renderQuadData(quad);
            spriteBatch.end();
        }
    }

    protected void renderQuadData(QuadTree<?> quad) {
        if (frustum2D.isInside(quad.boundingRect)) {
            RectangleUtils.getCenter(quad.boundingRect, tmpV2);
            fontQuadsData.draw(spriteBatch, "depth " + quad.depth, tmpV2.x, tmpV2.y - 20);
            fontQuadsData.draw(spriteBatch, "total " + quad.countAllValues(), tmpV2.x, tmpV2.y);
            fontQuadsData.draw(spriteBatch, "this " + quad.countValues(), tmpV2.x, tmpV2.y + 20);
            if (quad.isSplit()) {
                renderQuadData(quad.ne);
                renderQuadData(quad.nw);
                renderQuadData(quad.se);
                renderQuadData(quad.sw);
            }
        }
    }

    private final Vector2 tmpV2 = new Vector2();

    protected void renderUserData(QuadTree<?> quad) {
        if (frustum2D.isInside(quad.boundingRect)) {
            for (QuadRectangleValue quadValue : quad.getAllValues(new Array<>())) {
                RectangleUtils.getCenter(quadValue.getBounds(), tmpV2);
                fontValuesData.draw(spriteBatch, quadValue.toString(), tmpV2.x, tmpV2.y);
            }

            if (quad.isSplit()) {
                renderUserData(quad.ne);
                renderUserData(quad.nw);
                renderUserData(quad.se);
                renderUserData(quad.sw);
            }
        }
    }

    protected void renderRects(QuadTree<?> quad) {
        if (frustum2D.isInside(quad.boundingRect)) {
            for (QuadRectangleValue quadValue : quad.getAllValues(new Array<>())) {
                shapeRenderer.rect(quadValue.getBounds());
            }
            if (quad.isSplit()) {
                renderRects(quad.ne);
                renderRects(quad.nw);
                renderRects(quad.se);
                renderRects(quad.sw);
            }
        }
    }

    public void renderQuad(QuadTree<?> quad) {
        if (frustum2D.isInside(quad.boundingRect)) {
            if (quad.isSplit()) {
                renderQuad(quad.ne);
                renderQuad(quad.nw);
                renderQuad(quad.se);
                renderQuad(quad.sw);
            } else {
                shapeRenderer.rect(quad.boundingRect);
            }
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
        fontValuesData.dispose();
        fontQuadsData.dispose();
    }
}
