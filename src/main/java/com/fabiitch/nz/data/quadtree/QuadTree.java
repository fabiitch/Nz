package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.math.shapes.utils.RectangleUtils;

public class QuadTree<T> {

    public QuadTree<T> ne, nw, se, sw;
    public Rectangle boundingRect;

    public int depth;
    public int maxValues;
    public Array<T> values;
    public Array<Rectangle> rectValues;

    public QuadTree(Rectangle rect, int depth, int maxValues) {
        this.boundingRect = new Rectangle(rect);
        this.values = new Array<>(maxValues);
        this.rectValues = new Array<>(maxValues);
        build(rect, depth, maxValues);
    }

    public QuadTree cpy() {
        return null;
    }

    public void build(Rectangle rect, int depth, int maxValues) {
        this.boundingRect.set(rect);
        this.depth = depth;
        this.maxValues = maxValues;
        rebuild();
    }

    public void rebuild() {
        regroup();
        if (shouldSplit())
            split();
    }

    public void add(Array<T> values, Array<Rectangle> rects) {
        for (int i = 0; i < values.size; i++) {
            add(values.get(i), rects.get(i));
        }
    }

    public void add(T value, Rectangle rect) {
        QuadTree quad = getQuad(rect);
        if (quad != null) {
            quad.values.add(value);
            quad.rectValues.add(rect);
            if (quad.shouldSplit())
                split();
        } else {
            RectangleUtils.mergeFloorCeil(this.boundingRect, rect);
            rebuild();
        }
    }

    public void remove(T value) {
        for (int i = 0, n = values.size; i < n; i++) {
            T curVal = values.get(i);
            if (curVal == value) {
                values.removeIndex(i);
                rectValues.removeIndex(i);
                break;
            }
        }
    }

    public boolean isSplitted() { //TODO rename 
        return ne != null || nw != null || sw != null || se != null;
    }

    private boolean shouldSplit() {
        if (isSplitted()
                || this.depth == maxValues
                || values.size + 1 <= maxValues) {
            return false;
        }
        return values.size + 1 >= maxValues;
    }

    private boolean shouldRegroup() {
        return isSplitted() && values.size < maxValues / 2;
    }

    public void split() {
        if (isSplitted())
            return;

        int newDepth = this.depth + 1;
        this.nw = new QuadTree(QuadTreeUtils.getNW(this.boundingRect, new Rectangle()), newDepth, this.maxValues);
        this.ne = new QuadTree(QuadTreeUtils.getNE(this.boundingRect, new Rectangle()), newDepth, this.maxValues);
        this.sw = new QuadTree(QuadTreeUtils.getSW(this.boundingRect, new Rectangle()), newDepth, this.maxValues);
        this.se = new QuadTree(QuadTreeUtils.getSE(this.boundingRect, new Rectangle()), newDepth, this.maxValues);

        Array<T> allValues = new Array<>();
        Array<Rectangle> allRectValues = new Array<>();
        this.getAllValues(allValues, allRectValues);

        this.values.clear();
        this.rectValues.clear();

        for (int i = 0, n = allValues.size; i < n; i++) {
            add(allValues.get(i), allRectValues.get(i));
        }
    }

    public void regroup() {
        if (isSplitted())
            return;

        Array<T> allValues = new Array<>();
        Array<Rectangle> allRectValues = new Array<>();
        this.getAllValues(allValues, allRectValues);
        this.values = allValues;
        this.rectValues = allRectValues;

        this.nw = null;
        this.ne = null;
        this.sw = null;
        this.se = null;
    }

    public void getAllValues(Array<T> valueResults) {
        valueResults.addAll(this.values);
        if (isSplitted()) {
            this.nw.getAllValues(valueResults);
            this.ne.getAllValues(valueResults);
            this.sw.getAllValues(valueResults);
            this.ne.getAllValues(valueResults);
        }
    }

    public void getAllValues(Array<T> valueResults, Array<Rectangle> rectResults) {
        valueResults.addAll(this.values);
        rectResults.addAll(this.rectValues);

        if (isSplitted()) {
            this.nw.getAllValues(valueResults, rectResults);
            this.ne.getAllValues(valueResults, rectResults);
            this.sw.getAllValues(valueResults, rectResults);
            this.ne.getAllValues(valueResults, rectResults);
        }
    }

    public Array<T> query(Array<T> result, Rectangle rectangle) {
        for (int i = 0, n = result.size; i < n; i++) {
            if (RectangleUtils.overlapsStick(rectValues.get(i), rectangle)) {
                result.add(values.get(i));
            }
        }
        if (isSplitted()) {
            this.nw.query(result, rectangle);
            this.ne.query(result, rectangle);
            this.sw.query(result, rectangle);
            this.ne.query(result, rectangle);
        }
        return result;
    }

    public QuadTree getQuad(Rectangle rectangle) {
        if (RectangleUtils.containsStick(this.boundingRect, rectangle)) {
            if (!isSplitted())
                return this;
            QuadTree child = nw.getQuad(rectangle);
            if (child != null)
                return child;
            child = ne.getQuad(rectangle);
            if (child != null)
                return child;
            child = sw.getQuad(rectangle);
            if (child != null)
                return child;
            child = se.getQuad(rectangle);
            if (child != null)
                return child;
            return this;
        } else {
            return null;
        }
    }

    public QuadTree getQuad(Vector2 v) {
        return null;
    }

    public QuadTree getQuad(float x, float y) {
        return null;
    }
}
