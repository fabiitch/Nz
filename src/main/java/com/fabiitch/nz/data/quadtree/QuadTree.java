package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.math.shapes.utils.RectangleUtils;

public class QuadTree<T> {

    public QuadTree<T> ne, nw, se, sw;
    public Rectangle boundingRect;

    public int depth;

    public int maxDepth;
    public int maxValues;
    public Array<T> values;
    public Array<Rectangle> rectValues;

    public QuadTree() {
        this(Rectangle.tmp, 10, 5);
    }

    public QuadTree(Rectangle rect, int maxValues, int maxDepth) {
        this.boundingRect = new Rectangle(rect);
        this.values = new Array<>(maxValues);
        this.rectValues = new Array<>(maxValues);
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
    }

    public QuadTree cpy() {
        return null;
    }

    public void build(Rectangle rect, int maxDepth, int maxValues) {
        this.boundingRect.set(rect);
        this.maxDepth = maxDepth;
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

    public QuadTree add(T value, Rectangle rect) {
        QuadTree quad = getQuad(rect);
        if (quad != null) {
            quad.values.add(value);
            quad.rectValues.add(rect);
            if (quad.shouldSplit())
                quad.split();
        } else {
            RectangleUtils.mergeFloorCeil(this.boundingRect, rect);
            this.rebuild();
            this.add(value, rect);
        }
        return quad;
    }

    /**
     * true if one
     */
    public boolean remove(Array<T> values) {
        boolean result = false;
        for (int i = 0, n = values.size; i < n; i++) {
            result |= remove(values.get(i));
        }
        return result;
    }

    public boolean remove(T value) {
        for (int i = 0, n = values.size; i < n; i++) {
            T curVal = values.get(i);
            if (curVal == value) {
                values.removeIndex(i);
                rectValues.removeIndex(i);
                if (shouldRegroup())
                    regroup();
                return true;
            }
        }
        if (isSplit()) {
            if (nw.remove(value))
                return true;
            if (ne.remove(value))
                return true;
            if (sw.remove(value))
                return true;
            if (se.remove(value))
                return true;
        }
        return false;
    }

    public boolean isSplit() { //TODO rename
        return ne != null || nw != null || sw != null || se != null;
    }

    private boolean shouldSplit() {
        if (isSplit()
                || this.depth >= maxDepth
                || values.size < maxValues) {
            return false;
        }
        return true;
    }

    private boolean shouldRegroup() {
        return isSplit() && values.size < maxValues / 2;
    }

    public QuadTree split() {
        if (isSplit())
            return this;

        int newDepth = this.depth + 1;
        this.nw = new QuadTree(QuadTreeUtils.getNW(this.boundingRect, new Rectangle()), this.maxValues, this.maxDepth);
        this.ne = new QuadTree(QuadTreeUtils.getNE(this.boundingRect, new Rectangle()), this.maxValues, this.maxDepth);
        this.sw = new QuadTree(QuadTreeUtils.getSW(this.boundingRect, new Rectangle()), this.maxValues, this.maxDepth);
        this.se = new QuadTree(QuadTreeUtils.getSE(this.boundingRect, new Rectangle()), this.maxValues, this.maxDepth);
        this.nw.depth = newDepth;
        this.ne.depth = newDepth;
        this.sw.depth = newDepth;
        this.se.depth = newDepth;

        Array<T> allValues = new Array<>(this.values);
        Array<Rectangle> allRectValues = new Array<>(this.rectValues);
        this.values.clear();
        this.rectValues.clear();

        for (int i = 0, n = allValues.size; i < n; i++) {
            add(allValues.get(i), allRectValues.get(i));
        }
        return this;
    }

    public void regroup() {
        if (!isSplit())
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

    public Array<T> getAllValues(Array<T> valueResults) {
        valueResults.addAll(this.values);
        if (isSplit()) {
            this.nw.getAllValues(valueResults);
            this.ne.getAllValues(valueResults);
            this.sw.getAllValues(valueResults);
            this.se.getAllValues(valueResults);
        }
        return valueResults;
    }

    public Array<T> getAllValues(Array<T> valueResults, Array<Rectangle> rectResults) {
        valueResults.addAll(this.values);
        rectResults.addAll(this.rectValues);
        if (isSplit()) {
            this.nw.getAllValues(valueResults, rectResults);
            this.ne.getAllValues(valueResults, rectResults);
            this.sw.getAllValues(valueResults, rectResults);
            this.se.getAllValues(valueResults, rectResults);
        }
        return valueResults;
    }


    public Rectangle getRectangle(T value) {
        for (int i = 0, n = values.size; i < n; i++) {
            if (values.get(i) == value)
                return rectValues.get(i);
        }
        if (isSplit()) {
            Rectangle rectangle;
            rectangle = this.nw.getRectangle(value);
            if (rectangle != null)
                return rectangle;
            rectangle = this.ne.getRectangle(value);
            if (rectangle != null)
                return rectangle;
            rectangle = this.sw.getRectangle(value);
            if (rectangle != null)
                return rectangle;
            rectangle = this.se.getRectangle(value);
            if (rectangle != null)
                return rectangle;
        }
        return null;
    }

    public void update(T value) {
        update(value, getRectangle(value));
    }

    public void update(T value, Rectangle rectangle) {
        QuadTree quad = getQuad(rectangle);
        if (quad != this || quad == null) {
            remove(value);
            add(value, rectangle);
        }
    }

    public void update() {
        Array<T> allValues = new Array<>();
        Array<Rectangle> allRects = new Array<>();
        getAllValues(allValues, allRects);
        for (int i = 0; i < allValues.size; i++) {
            update(allValues.get(i), allRects.get(i));
        }
    }

    public Array<T> query(Array<T> result, Rectangle rectangle) {
        for (int i = 0, n = this.values.size; i < n; i++) {
            if (RectangleUtils.overlapsStick(rectValues.get(i), rectangle)) {
                result.add(values.get(i));
            }
        }
        if (isSplit()) {
            this.nw.query(result, rectangle);
            this.ne.query(result, rectangle);
            this.sw.query(result, rectangle);
            this.se.query(result, rectangle);
        }
        return result;
    }

    public QuadTree getQuad(Rectangle rectangle) {
        if (RectangleUtils.containsStick(this.boundingRect, rectangle)) {
            if (!isSplit())
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

    public int getCurrentMaxDepth(int depthStart) {
        depthStart++;
        if (isSplit()) {
            int dephtNw = nw.getCurrentMaxDepth(depthStart);
            int dephtNe = ne.getCurrentMaxDepth(depthStart);
            int dephtSw = sw.getCurrentMaxDepth(depthStart);
            int dephtSe = se.getCurrentMaxDepth(depthStart);
            int maxNorth = Math.max(dephtNw, dephtNe);
            int maxSouth = Math.max(dephtSw, dephtSe);
            return Math.max(maxNorth,maxSouth);
        }
        return depthStart;
    }

    public QuadTree getQuad(Vector2 v) {
        return null;
    }

    public QuadTree getQuad(float x, float y) {
        return null;
    }

}
