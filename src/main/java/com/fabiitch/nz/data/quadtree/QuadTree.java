package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class QuadTree<T> {

    public QuadTree<T> ne, nw, se, sw;
    public Rectangle boundingRect;

    public int depth;
    public float maxValues;
    public Array<T> values;
    public Array<Rectangle> rectValues;

    public QuadTree (Rectangle rect, int depth, int maxValues) {
        this.boundingRect.set(rect);
        this.depth = depth;
        this.maxValues =maxValues;
        values= new Array<>(maxValues);
        rectValues  = new Array<>(maxValues);
    }

    public void add(Rectangle rect, T value) {
        rectValues.add(rect);
        values.add(value);
        if (shouldSplit())
            split();
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

    public void regroup() {

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
        return isSplitted() &&   values.size < maxValues/2;
    }

    public void split() {
        if (isSplitted())
            return;
    }

    public QuadTree getQuad(Rectangle rectangle) {
        return null;
    }

    public QuadTree getQuad(Vector2 v) {
        return null;
    }

    public QuadTree getQuad(float x, float y) {
        return null;
    }
}
