package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.fabiitch.nz.gdx.render.shape.NzShapeRenderer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@Getter
@NoArgsConstructor

public class QuadTreeRenderConfig<T> {

    private boolean drawQuads = true;
    private boolean drawRectangles = true;
    private boolean drawValuesData = false;
    private boolean drawQuadData = true;

    private Color colorQuad = Color.PURPLE;
    private Color colorRects = Color.GREEN;
    private Color colorValuesData = Color.WHITE;
    private Color colorQuadsData = Color.WHITE;


    private Function<T, String> toStringFunction;

    private NzShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private BitmapFont fontValuesData;
    private BitmapFont fontQuadsData;

    public QuadTreeRenderConfig<T> drawQuads(boolean drawQuads) {
        this.drawQuads = drawQuads;
        return this;
    }

    public QuadTreeRenderConfig<T> drawRectangles(boolean drawRectangles) {
        this.drawRectangles = drawRectangles;
        return this;
    }

    public QuadTreeRenderConfig<T> drawValuesData(boolean drawValuesData) {
        this.drawValuesData = drawValuesData;
        return this;
    }

    public QuadTreeRenderConfig<T> drawQuadData(boolean drawQuadData) {
        this.drawQuadData = drawQuadData;
        return this;
    }

    public QuadTreeRenderConfig<T> colorRects(Color colorRects) {
        this.colorRects = colorRects;
        return this;
    }

    public QuadTreeRenderConfig<T> colorQuad(Color colorQuad) {
        this.colorQuad = colorQuad;
        return this;
    }

    public QuadTreeRenderConfig<T> colorValuesData(Color colorValuesData) {
        this.colorValuesData = colorValuesData;
        return this;
    }

    public QuadTreeRenderConfig<T> colorQuadsData(Color colorQuadsData) {
        this.colorQuadsData = colorQuadsData;
        return this;
    }

    public QuadTreeRenderConfig<T> setToStringFunction(Function<T, String> toStringFunction) {
        this.toStringFunction = toStringFunction;
        return this;
    }


    public QuadTreeRenderConfig<T> shapeRender(NzShapeRenderer shapeRender) {
        this.shapeRenderer = shapeRender;
        return this;
    }

    public QuadTreeRenderConfig<T> spriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        return this;
    }

    public QuadTreeRenderConfig<T> fontValuesData(BitmapFont fontValuesData) {
        this.fontValuesData = fontValuesData;
        return this;
    }

    public QuadTreeRenderConfig<T> fontQuadsData(BitmapFont fontQuadsData) {
        this.fontQuadsData = fontQuadsData;
        return this;
    }
}
