package com.fabiitch.nz.data.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.math.utils.RectangleUtils;

import java.util.Arrays;
import java.util.List;

public class QuadTree<T> {
    public QuadTree parent, nw, ne, sw, se;
    public final Rectangle boundingRect;
    public QuadData<T>[] values;
    public int valuesCount;
    public int depth;

    public QuadTreeContainer container;

    public QuadTree(QuadTreeContainer quadTreeContainer) {
        this.container = quadTreeContainer;
        this.boundingRect = new Rectangle();
        this.values = new QuadData[quadTreeContainer.maxValues];
    }

    public void init(QuadTree parent, Rectangle rect, int depth) {
        this.parent = parent;
        this.boundingRect.set(rect);
        this.depth = depth;
    }

    public void add(QuadData<T> quadData) {
        Rectangle rect = quadData.getRectangle();
        QuadTree quadTree = getQuadTree(rect);
        if (quadTree != null)
            quadTree.addValue(quadData);
        else {
            if (parent != null)
                parent.add(quadData);
            else
                container.growQuadTree(rect);
        }
    }

    public boolean remove(QuadData<T> quadData) {
        Rectangle rectangle = quadData.getRectangle();
        QuadTree quadTree = getQuadTree(rectangle);
        if (quadTree == null)
            return false;
        else
            quadTree.removeValue(quadData);
        return true;
    }

    public boolean isSplitted() {
        return ne != null || nw != null || sw != null || se != null;
    }

    public Array<QuadData<T>> getValuesAndSub() {
        Array<QuadData<T>> array = new Array<>();//TODO
        for (int i = 0; i < valuesCount; i++) {
            array.add(values[i]);
        }
        if (isSplitted()) {
            List<QuadTree> childArray = Arrays.asList(nw, ne, sw, se);
            for (QuadTree sub : childArray)
                array.addAll(sub.getValuesAndSub());
        }
        return array;
    }


    public QuadTree<T> getQuadTree(Rectangle rectangle) {
        if (!RectangleUtils.containsStick(this.boundingRect, rectangle))
            return null;
        if (!isSplitted()) {
            return this;
        }
        QuadTree child = nw.getQuadTree(rectangle);
        if (child != null)
            return child;
        child = ne.getQuadTree(rectangle);
        if (child != null)
            return child;
        child = sw.getQuadTree(rectangle);
        if (child != null)
            return child;
        child = se.getQuadTree(rectangle);
        if (child != null)
            return child;
        return this;
    }

    private void addValue(QuadData<T> quadData) {
        if (shouldSplit()) {
            split();
            add(quadData);
        } else {
            if (valuesCount >= container.maxValues)
                values = Arrays.copyOf(values, valuesCount + container.maxValues);
            values[valuesCount++] = quadData;
        }
    }

    private void removeValue(QuadData<T> quadData) {
        int index = -1;
        for (int i = 0, n = valuesCount; i < n; i++) {
            if (values[i] == quadData) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            //ArrayUtils.removeAndDecal(values, index);//TODO a opti
            for (int i = index, n = values.length - 1; i < n; i++) {
                values[i] = values[i + 1];
            }
            values[values.length - 1] = null;
            valuesCount--;
        }
        if (depth > 0 && parent.shouldRegroup()) {
            parent.regroup();
        }
    }

    public int countSubValues() {
        int count = 0;
        if (isSplitted()) {
            List<QuadTree> childArray = Arrays.asList(nw, ne, sw, se);
            for (QuadTree sub : childArray)
                count += sub.countValuesAndSubs();
        }
        return count;
    }

    public int countSubQuad() {
        return 0;
    }

    private void regroup() {
        if (!isSplitted())
            return;
        Array<QuadData<T>> valuesAndSub = getValuesAndSub();
        nw = ne = sw = se = null;
        for (QuadData<T> quadData : valuesAndSub) {
            addValue(quadData);
        }
    }

    public boolean shouldRegroup() {
        if (parent == null || !isSplitted())
            return false;
        return countValuesAndSubs() <= container.maxValues / 2;
    }

    public int countValuesAndSubs() {
        return valuesCount + countSubValues();
    }

    private boolean shouldSplit() {
        if (isSplitted()
                || this.depth == container.maxDepth
                || valuesCount < container.maxValues) {
            return false;
        }
        return valuesCount + 1 - QuadTreeUtils.countValuesCantBeSplitted(this) >= container.maxValues;
    }

    private void split() {
        if (isSplitted())
            return;
        this.nw = new QuadTree(container);
        this.ne = new QuadTree(container);
        this.sw = new QuadTree(container);
        this.se = new QuadTree(container);

        int newDepth = this.depth + 1;

        this.nw.init(this, QuadTreeUtils.getNW(boundingRect, nw.boundingRect), newDepth);
        this.sw.init(this, QuadTreeUtils.getSW(boundingRect, sw.boundingRect), newDepth);
        this.ne.init(this, QuadTreeUtils.getNE(boundingRect, ne.boundingRect), newDepth);
        this.se.init(this, QuadTreeUtils.getSE(boundingRect, se.boundingRect), newDepth);


    }
}
