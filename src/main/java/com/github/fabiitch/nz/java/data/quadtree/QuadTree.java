package com.github.fabiitch.nz.java.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.data.quadtree.utils.QuadTreeUtils;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import lombok.Getter;


public class QuadTree<T extends QuadRectangleValue> implements Pool.Poolable {

    public QuadTree<T> parent, ne, nw, se, sw;
    public Rectangle boundingRect;
    @Getter
    public int depth, maxDepth, maxValues;

    public final IntMap<T> mapValues = new IntMap<>();
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
        this.maxValues = Math.max(1, maxValues);
        this.maxDepth = Math.max(1, maxDepth);
        this.quadPools = quadPools;
    }

    private void set(QuadTree<T> parent, Rectangle rectangle, int maxValues, int maxDepth, QuadPools<T> quadPools) {
        this.parent = parent;
        this.boundingRect.set(rectangle);
        this.mapValues.clear();
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

    public int countValues() {
        return mapValues.size;
    }

    public void rebuild() {
        regroup();
        if (shouldSplit())
            split();
    }

    public void add(Array<T> values) {
        for (int i = 0; i < values.size; i++) {
            add(values.get(i));
        }
    }

    public QuadTree<T> add(T value) {
        Rectangle rect = value.getBounds();
        QuadTree<T> quad = getQuad(rect);
        if (quad != null) {
            if (quad.shouldSplit()) {
                quad.split();
                return this.add(value);
            }
            quad.mapValues.put(value.getId(), value);
            return quad;
        } else {
            if (parent != null) {
                return parent.add(value);
            } else {
                RectangleUtils.mergeFloorCeil(this.boundingRect, rect);
                this.rebuild();
                return this.add(value);
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
        T remove = mapValues.remove(value.getId());
        if (remove != null && shouldRegroup()) {
            regroup();
            return true;
        }
        if (remove == null && isSplit()) {
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
                || mapValues.size < maxValues) {
            return false;
        }
        return true;
    }

    private boolean shouldRegroup() {
        return isSplit() && mapValues.size < maxValues / 2;
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

        Array<T> values = mapValues.values().toArray();
        mapValues.clear();

        for (T value : values) {
            add(value);
        }
        return this;
    }

    public void regroup() {
        if (!isSplit())
            return;
        Array<T> allValues = quadPools.getValuesArray();
        this.getAllValues(allValues);

        for (T value : allValues) {
            mapValues.put(value.getId(), value);
        }
        quadPools.freeValuesArray(allValues);

        quadPools.free(nw);
        quadPools.free(ne);
        quadPools.free(sw);
        quadPools.free(se);

        this.nw = null;
        this.ne = null;
        this.sw = null;
        this.se = null;
    }

    public Array<T> getAllValuesAndParents(Array<T> valueResults) {
        getAllValues(valueResults);
        QuadTree<T> currentParent = parent;
        while (currentParent != null) {
            valueResults.addAll(parent.mapValues.values().toArray());
            currentParent = currentParent.parent;
        }
        return valueResults;
    }

    public Array<T> getAllValues(Array<T> valueResults) {
        valueResults.addAll(this.mapValues.values().toArray());
        if (isSplit()) {
            this.nw.getAllValues(valueResults);
            this.ne.getAllValues(valueResults);
            this.sw.getAllValues(valueResults);
            this.se.getAllValues(valueResults);
        }
        return valueResults;
    }


    public QuadTree<T> update(T value) {
        QuadTree<T> quad = getQuad(value.getBounds());
        if (quad != this) {
            remove(value);
            add(value);
        }
        return quad;
    }

    public void recompute() {
        Array<T> allValues = quadPools.getValuesArray();
        Array<Rectangle> allRects = quadPools.getRectangleArray();

        getAllValues(allValues);
        for (int i = 0; i < allValues.size; i++) {
            update(allValues.get(i));
        }
        quadPools.freeValuesArray(allValues);
        quadPools.freeRectangleArray(allRects);
    }

    public Array<T> query(Rectangle rectangle, Array<T> result) {
        if (!RectangleUtils.overlapsStick(this.boundingRect, rectangle)) {
            return result;
        }
        for (T value : this.mapValues.values()) {
            if (RectangleUtils.overlapsStick(value.getBounds(), rectangle)) {
                result.add(value);
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

    private QuadTree<T> getQuad(QuadTreeRegion region) {
        switch (region) {
            case TopLeft:
                return this.nw;
            case TopRight:
                return this.ne;
            case BotLeft:
                return this.sw;
            case BotRight:
                return this.se;
            case Out:
                return null;
            case This:
                return this;
        }
        return null;
    }
    private QuadTreeRegion getFastRegion(Rectangle rectangle) {
        Rectangle quadRect = this.boundingRect;
        if (RectangleUtils.containsStick(quadRect, rectangle)) {
            if (!isSplit()) {
                return QuadTreeRegion.This;
            }
            float halfQuadWidth = quadRect.width / 2;
            float halfQuadHeight = quadRect.height / 2;

            if (rectangle.width > halfQuadWidth ||rectangle.height > halfQuadHeight)
                return QuadTreeRegion.This;

            boolean rightRegionX = QuadTreeUtils.isSecondRegion(quadRect.x, halfQuadWidth, rectangle.x);
            boolean rightRegionXMax = QuadTreeUtils.isSecondRegion(quadRect.x, halfQuadWidth, rectangle.x + rectangle.width);
            if (rightRegionX ^ rightRegionXMax) {
                return QuadTreeRegion.This;
            }
            boolean botRegionY = !QuadTreeUtils.isSecondRegion(quadRect.y, halfQuadHeight, rectangle.y);
            boolean botRegionYMax = !QuadTreeUtils.isSecondRegion(quadRect.y, halfQuadHeight, rectangle.y + rectangle.height);
            if (botRegionY ^ botRegionYMax) {
                return QuadTreeRegion.This;
            }
            int index = rightRegionX ? 1 : 0;
            if (botRegionY)
                index += 2;

            return QuadTreeRegion.VALUES[index];
        }
        return QuadTreeRegion.Out;
    }

    public QuadTree<T> getQuad(Rectangle rectangle) {
        QuadTreeRegion region = getFastRegion(rectangle);
        QuadTree<T> result = getQuad(region);
        if (region.isChild())
            result = result.getQuad(rectangle);

        return result;
    }

    private QuadTree<T> getOldQuad(Rectangle rectangle){
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
        int count = this.mapValues.size;
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
    public int getCurrentMaxDepth(){
        if (isSplit()) {
            int max = Math.max(ne.getCurrentMaxDepth(), se.getCurrentMaxDepth());
            max = Math.max(max, se.getCurrentMaxDepth());
            max = Math.max(max, sw.getCurrentMaxDepth());
            return max;
        }
        return this.depth;
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
        this.mapValues.clear();
        this.maxValues = 0;
        this.maxDepth = 0;
    }


}
