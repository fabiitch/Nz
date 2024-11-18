package com.github.fabiitch.nz.demo.screens.quadtree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.demo.internal.BaseDemoScreen;
import com.github.fabiitch.nz.gdx.debug.huddebug.HudDebug;
import com.github.fabiitch.nz.java.data.quadtree.QuadTree;
import com.github.fabiitch.nz.java.data.quadtree.QuadTreeRenderer;
import com.github.fabiitch.nz.java.math.shapes.builders.RectangleBuilder;

import java.util.ArrayList;


public abstract class BaseDemoQuadTree<T> extends BaseDemoScreen {

    public QuadTree<QuadData<T>> quadT;
    protected QuadTreeRenderer quadRender;

    public volatile int indexQuad = 1;

    public ArrayList<QuadData<T>> values = new ArrayList<>();

    public BaseDemoQuadTree() {
        super();
        quadRender = new QuadTreeRenderer();

        quadT = new QuadTree<>(RectangleBuilder.screen(camera, true), 2, 5);

        hudMsg("arrow for move, mouse for zoom");
        hudMsg("Left for create rect");
        hudMsg("Right Click destroy");
    }

    @Override
    public void show() {
        super.show();
        HudDebug.addTopMiddle("QuadEntryCount", "", Color.RED);
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        HudDebug.update("QuadEntryCount", values.size());
        quadRender.render(quadT, camera);
    }

    protected void quadRemove(Array<QuadData<T>> quadDatas) {
        for (QuadData<T> quadData : quadDatas)
            quadRemove(quadData);
    }

    protected void quadRemove(QuadData<T> quadData) {
        this.values.remove(quadData);
        quadT.remove(quadData);
    }

    protected void quadAdd(T data, Rectangle rect) {
        indexQuad += 1;
        QuadData quadData = new QuadData(indexQuad, rect, data);
        this.values.add(quadData);
        this.quadT.add(quadData, rect);
    }

    class QuadData<T> {
        public int index;
        public Rectangle rectangle;
        public T data;

        public QuadData(int index, Rectangle rectangle, T data) {
            this.index = index;
            this.rectangle = rectangle;
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + index;
        }
    }
}
