package com.fabiitch.nz.data.quadtree.q2;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.IntMap;
import com.fabiitch.nz.math.shapes.Segment;

public class Q2<T> {

    int maxValues;
    int maxDepth;
    long id;
    public Q2Node root;
    IntMap<Q2Data<T>> dataMap = new IntMap<>();

    public Q2(Rectangle rectangle, int maxValues, int maxDepth) {
        root = new Q2Node(this, 0);
    }

    public Q2(int maxValues, int maxDepth) {
        this(new Rectangle(), maxValues, maxDepth);
    }

    public void update() {

    }

    public void update(Q2Data data) {

    }

    public void add(Q2Data data) {

    }

    public void remove(Q2Data data) {

    }

    public void query(Segment segment, float dst) {

    }

    public void query(Segment segment) {

    }

    public void query(Polygon polygon) {

    }

    public void query(Circle circle) {

    }

    public void query(Rectangle rectangle) {

    }
}
