package com.fabiitch.nz.data.quadtree.q2;

public class Q2Node<T> {

    public Q2 q2;
    public Q2Node ne, nw, se, sw;
    public int depth;

    public boolean isSplit() {
        return false;
    }

    public void split() {

    }

    public void regroup() {

    }

    public boolean shouldSplit() {
        return false;
    }

    public boolean shouldGroup() {
        return false;
    }
    public void add(){

    }
    public void remove(){

    }

    public void getAll(){

    }
}

