package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class QuadTreeContainer<T> {

    public QuadTree<T> root;

    public int maxValues;
    public int maxDepth;

    public QuadTreeContainer(Rectangle rect, int maxValues, int maxDepth) {
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
        this.root = new QuadTree<>(this);
        root.init(null, rect, 0);
    }


    public void add(QuadData<T> value) {
        root.add(value);
    }

    public void remove(QuadData<T> value) {
        root.remove(value);
    }

    private QuadTree<T> getQuadTree(Rectangle rectangle) {
        return root.getQuadTree(rectangle);
    }


    public QuadTree<T> getPart(QuadData<T> value) {
        return null;
    }

    public Array<QuadTree<T>> getValues() {
        return null;
    }

    public QuadTree<T> getPart(Vector2 position) {
        return null;
    }

    public Array<QuadTree<T>> getParts() {
        return null;
    }

    public Array<T> query(Rectangle rectangle) {
        return null;
    }

    public Array<T> query(Circle circle) {
        return null;
    }

    public Array<T> query(Polygon polygon) {
        return null;
    }

    public void growQuadTree(Rectangle rect) {
    }
}
