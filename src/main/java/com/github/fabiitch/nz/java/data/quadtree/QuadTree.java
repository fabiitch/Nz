package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.Getter;

public class QuadTree<T> implements Pool.Poolable {

    public QuadTree<T> parent, ne, nw, se, sw;
    public Rectangle boundingRect;
    @Getter
    public int depth, maxDepth, maxValues;

    public final Array<T> values;
    public final Array<Rectangle> rectangles;
    public QuadPools<T> quadPools;

    public QuadTree() {
        this(null, Rectangle.tmp, 10, 5, new QuadPools<>());
    }

    public QuadTree(Rectangle rect, int maxValues, int maxDepth) {
        this(null, rect, maxValues, maxDepth, new QuadPools<>());
    }

    public QuadTree(Rectangle rect, int maxValues, int maxDepth, QuadPools<T> quadPools) {
        this(null, rect, maxValues, maxDepth, quadPools);
    }

    protected QuadTree(QuadTree<T> parent, Rectangle rect, int maxValues, int maxDepth, QuadPools<T> quadPools) {
        this.parent = parent;
        this.boundingRect = new Rectangle(rect);
        this.values = new Array<>(maxValues);
        this.rectangles = new Array<>(maxValues);
        this.maxValues = Math.max(1, maxValues);
        this.maxDepth = Math.max(1, maxDepth);
        this.quadPools = quadPools;
    }

    private void set(QuadTree<T> parent, Rectangle rectangle, int maxValues, int maxDepth, QuadPools<T> quadPools) {
        this.parent = parent;
        this.boundingRect.set(rectangle);
        this.values.clear();
        this.rectangles.clear();
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
        this.quadPools = quadPools;
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

    public QuadTree<T> add(T value, Rectangle rect) {
        QuadTree<T> quad = getQuad(rect);
        if (quad != null) {
            if (quad.shouldSplit()) {
                quad.split();
                return this.add(value, rect);
            }
            quad.values.add(value);
            quad.rectangles.add(rect);
            return quad;
        } else {
            if (parent != null) {
                return parent.add(value, rect);
            } else {
                RectangleUtils.mergeFloorCeil(this.boundingRect, rect);
                this.rebuild();
                return this.add(value, rect);
            }
        }
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
                rectangles.removeIndex(i);
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

    public QuadTree<T> split() {
        if (isSplit())
            return this;

        int newDepth = this.depth + 1;
        nw = quadPools.getQuad();
        nw.set(this, QuadTreeUtils.getNW(boundingRect, new Rectangle()), maxValues, maxDepth, this.quadPools);

        ne = quadPools.getQuad();
        ne.set(this, QuadTreeUtils.getNE(boundingRect, new Rectangle()), maxValues, maxDepth, this.quadPools);

        sw = quadPools.getQuad();
        sw.set(this, QuadTreeUtils.getSW(boundingRect, new Rectangle()), maxValues, maxDepth, this.quadPools);

        se = quadPools.getQuad();
        se.set(this, QuadTreeUtils.getSE(boundingRect, new Rectangle()), maxValues, maxDepth, this.quadPools);

        nw.depth = newDepth;
        ne.depth = newDepth;
        sw.depth = newDepth;
        se.depth = newDepth;

        Array<T> allValues = quadPools.getArray();
        Array<Rectangle> allRectValues = quadPools.getArrayRectangle();

        allValues.addAll(this.values);
        allRectValues.addAll(this.rectangles);

        this.values.clear();
        this.rectangles.clear();

        for (int i = 0, n = allValues.size; i < n; i++) {
            add(allValues.get(i), allRectValues.get(i));
        }
        quadPools.free(allValues);
        quadPools.freeArrayRect(allRectValues);
        return this;
    }

    public void regroup() {
        if (!isSplit())
            return;

        Array<T> allValues = quadPools.getArray();
        Array<Rectangle> allRectangles = quadPools.getArrayRectangle();
        this.getAllValues(allValues, allRectangles);

        this.values.clear();
        this.values.addAll(allValues);
        this.rectangles.clear();
        this.rectangles.addAll(allRectangles);

        quadPools.free(allValues);
        quadPools.freeArrayRect(allRectangles);

        quadPools.free(nw);
        quadPools.free(ne);
        quadPools.free(sw);
        quadPools.free(se);

        this.nw = null;
        this.ne = null;
        this.sw = null;
        this.se = null;
    }

    public Array<T> getValues(QuadQueryParams params, Array<T> result) {
        if (params.isCurrent())
            result.addAll(this.values);
        if (params.isParentOverlap())
            result.addAll(parent.query(this.boundingRect, result));
        return result;
    }

    public Array<T> getAllValuesAndParents(Array<T> valueResults) {
        getAllValues(valueResults);
        QuadTree<T> currentParent = parent;
        while (currentParent != null) {
            valueResults.addAll(parent.values);
            currentParent = currentParent.parent;
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
        rectResults.addAll(this.rectangles);
        if (isSplit()) {
            this.nw.getAllValues(valueResults, rectResults);
            this.ne.getAllValues(valueResults, rectResults);
            this.sw.getAllValues(valueResults, rectResults);
            this.se.getAllValues(valueResults, rectResults);
        }
        return valueResults;
    }


    public QuadTree update(T value) {
        return update(value, getRectangle(value));
    }

    public Rectangle getRectangle(T value) {
        for (int i = 0, n = values.size; i < n; i++) {
            if (values.get(i) == value)
                return rectangles.get(i);
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

    public QuadTree<T> update(T value, Rectangle rectangle) {
        QuadTree<T> quad = getQuad(rectangle);
        if (quad != this) {
            remove(value);
            add(value, rectangle);
        }
        return quad;
    }

    public void recompute() {
        Array<T> allValues = quadPools.getArray();
        Array<Rectangle> allRects = quadPools.getArrayRectangle();

        getAllValues(allValues, allRects);
        for (int i = 0; i < allValues.size; i++) {
            update(allValues.get(i), allRects.get(i));
        }
        quadPools.free(allValues);
        quadPools.freeArrayRect(allRects);
    }

    public Array<T> query(Rectangle rectangle, Array<T> result) {
        if (!RectangleUtils.overlapsStick(this.boundingRect, rectangle)) {
            return result;
        }
        for (int i = 0, n = this.values.size; i < n; i++) {
            if (RectangleUtils.overlapsStick(rectangles.get(i), rectangle)) {
                result.add(values.get(i));
            }
        }
        if (isSplit()) {
            this.nw.query(rectangle, result);
            this.ne.query(rectangle, result);
            this.sw.query(rectangle, result);
            this.se.query(rectangle, result);
        }
        return result;
    }

    public QuadTree<T> getQuad(Rectangle rectangle) {
        if (RectangleUtils.containsStick(this.boundingRect, rectangle)) {
            if (!isSplit())
                return this;
            QuadTree<T> child = nw.getQuad(rectangle);
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
            int depthNw = nw.getCurrentMaxDepth(depthStart);
            int depthNe = ne.getCurrentMaxDepth(depthStart);
            int depthSw = sw.getCurrentMaxDepth(depthStart);
            int depthSe = se.getCurrentMaxDepth(depthStart);
            int maxNorth = Math.max(depthNw, depthNe);
            int maxSouth = Math.max(depthSw, depthSe);
            return Math.max(maxNorth, maxSouth);
        }
        return depthStart;
    }


    public QuadTree<T> getQuad(Vector2 pos) {
        return getQuad(pos.x, pos.y);
    }

    public QuadTree<T> getQuad(float x, float y) {
        if (RectangleUtils.containsStick(this.boundingRect, x, y)) {
            if (!isSplit())
                return this;
            QuadTree<T> child = nw.getQuad(x, y);
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

    public int countAllValues() {
        int count = this.values.size;
        if (isSplit()) {
            count += nw.countAllValues();
            count += ne.countAllValues();
            count += sw.countAllValues();
            count += se.countAllValues();
        }
        return count;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        if (isSplit()) {
            nw.setMaxDepth(maxDepth);
            ne.setMaxDepth(maxDepth);
            sw.setMaxDepth(maxDepth);
            se.setMaxDepth(maxDepth);
        }
        recompute();
    }

    public void setMaxValues(int maxValues) {
        this.maxValues = maxValues;
        if (isSplit()) {
            nw.setMaxValues(maxValues);
            ne.setMaxValues(maxValues);
            sw.setMaxValues(maxValues);
            se.setMaxValues(maxValues);
        }
        recompute();
    }

    @Override
    public void reset() {
        this.parent = this.ne = this.nw = this.se = this.sw = null;
        this.boundingRect.set(0, 0, 0, 0);
        this.values.clear();
        this.rectangles.clear();
        this.maxValues = 0;
        this.maxDepth = 0;
    }


}
