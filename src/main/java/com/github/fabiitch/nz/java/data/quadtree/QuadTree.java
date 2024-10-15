package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.math.shapes.utils.rectangle.RectangleUtils;

public class QuadTree<T> {

    public QuadTree<T> parent, ne, nw, se, sw;
    public Rectangle boundingRect;
    public int depth;
    public int maxDepth;
    public int maxValues;
    public Array<T> values;
    public Array<Rectangle> rectValues;

    public QuadTree() {
        this(null, Rectangle.tmp, 10, 5);
    }

    public QuadTree(Rectangle rect, int maxValues, int maxDepth) {
        this(null, rect, maxValues, maxDepth);
    }

    protected QuadTree(QuadTree<T> parent, Rectangle rect, int maxValues, int maxDepth) {
        this.parent = parent;
        this.boundingRect = new Rectangle(rect);
        this.values = new Array<>(maxValues);
        this.rectValues = new Array<>(maxValues);
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
    }

    public QuadTree cpy() {
        //TODO fab
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
            if (parent != null) {
                parent.add(value, rect);
            } else {
                RectangleUtils.mergeFloorCeil(this.boundingRect, rect);
                this.rebuild();
                this.add(value, rect);
            }
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

    public boolean isSplit() {
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
        nw = new QuadTree(this, QuadTreeUtils.getNW(boundingRect, new Rectangle()), maxValues, maxDepth);
        ne = new QuadTree(this, QuadTreeUtils.getNE(boundingRect, new Rectangle()), maxValues, maxDepth);
        sw = new QuadTree(this, QuadTreeUtils.getSW(boundingRect, new Rectangle()), maxValues, maxDepth);
        se = new QuadTree(this, QuadTreeUtils.getSE(boundingRect, new Rectangle()), maxValues, maxDepth);
        nw.depth = newDepth;
        ne.depth = newDepth;
        sw.depth = newDepth;
        se.depth = newDepth;

        Array<T> allValues = new Array<>(values);
        Array<Rectangle> allRectValues = new Array<>(rectValues);
        values.clear();
        rectValues.clear();

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

    public Array<T> getAllValuesAndParents(Array<T> valueResults) {
        getAllValues(valueResults);
        while (parent != null){
            valueResults.addAll(parent.values);
        }
        return valueResults;
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

    //reset pas un update
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
            return Math.max(maxNorth, maxSouth);
        }
        return depthStart;
    }

    public QuadTree getQuad(Vector2 pos) {
        return getQuad(pos.x, pos.y);
    }

    public QuadTree getQuad(float x, float y) {
        if (RectangleUtils.containsStick(this.boundingRect, x, y)) {
            if (!isSplit())
                return this;
            QuadTree child = nw.getQuad(x, y);
            if (child != null)
                return child;
            child = ne.getQuad(x, y);
            if (child != null)
                return child;
            child = sw.getQuad(x, y);
            if (child != null)
                return child;
            child = se.getQuad(x, y);
            if (child != null)
                return child;
            return this;
        } else {
            return null;
        }
    }

}
