package com.github.fabiitch.nz.demo.screens.quadtree;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.demo.internal.input.KeyBinderFunction;
import com.github.fabiitch.nz.gdx.debug.huddebug.internal.HudDebugPosition;
import com.github.fabiitch.nz.java.data.quadtree.QuadRectangleValue;
import com.github.fabiitch.nz.java.data.quadtree.QuadTree;
import com.github.fabiitch.nz.java.data.quadtree.render.QuadTreeRenderer;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;

import java.util.ArrayList;


public abstract class BaseDemoQuadTree<T> extends BaseDemoScreen {

    public QuadTree<QuadData<T>> quadTree;
    protected QuadTreeRenderer quadRender;

    public volatile int indexQuad = 1;

    public ArrayList<QuadData<T>> values = new ArrayList<>();

    public BaseDemoQuadTree() {
        super();
        quadRender = new QuadTreeRenderer();

        quadTree = new QuadTree<>(RectangleBuilder.screen(camera, true), 2, 5);

        hudMsg("arrow for move, mouse for zoom");
        hudMsg("Left for create rect");
        hudMsg("Right Click destroy");
        hudMsg("T/Y for max values changes");
        hudMsg("G/H for max depth changes");

        hudDebugTracker.track("Quad total",
                () -> quadTree.countAllValues(), HudDebugPosition.TOP_MIDDLE, Color.RED);

        inputKeyBinder.add(new KeyBinderFunction(Input.Keys.T, () ->
                quadTree.setMaxValues(quadTree.getMaxValues() + 1)));
        inputKeyBinder.add(new KeyBinderFunction(Input.Keys.Y, () ->
                quadTree.setMaxValues(quadTree.getMaxValues() - 1)));
        hudDebugTracker.track("Quad Max Values", () -> quadTree.getMaxValues());

        inputKeyBinder.add(new KeyBinderFunction(Input.Keys.G, () ->
                quadTree.setMaxDepth(quadTree.getMaxDepth() + 1)));
        inputKeyBinder.add(new KeyBinderFunction(Input.Keys.H, () ->
                quadTree.setMaxDepth(quadTree.getMaxDepth() - 1)));
        hudDebugTracker.track("Quad Max Depth", () -> quadTree.getMaxDepth());
    }


    @Override
    public void doRender(float dt) {
        quadRender.render(quadTree, (OrthographicCamera) camera);
    }

    @Override
    public void doDispose() {

    }

    protected void quadRemove(Array<QuadData<T>> quadDatas) {
        for (QuadData<T> quadData : quadDatas)
            quadRemove(quadData);
    }

    protected void quadRemove(QuadData<T> quadData) {
        this.values.remove(quadData);
        quadTree.remove(quadData);
    }

    protected void quadAdd(T data, Rectangle rect) {
        indexQuad += 1;
        QuadData<T> quadData = new QuadData<>(indexQuad, rect, data);
        this.values.add(quadData);
        this.quadTree.add(quadData);
    }

    public static class QuadData<T> implements QuadRectangleValue {
        public int id;
        public Rectangle rectangle;
        public T data;

        public QuadData(int id, Rectangle rectangle, T data) {
            this.id = id;
            this.rectangle = rectangle;
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + id;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public Rectangle getBounds() {
            return rectangle;
        }
    }
}
