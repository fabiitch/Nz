package com.github.fabiitch.nz.java.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;

public class TreeLeaf<L, T extends TreeLeaf<L, ?>> {
    protected T parent;
    protected Array<T> childrens = new Array<>();
    protected Array<L> leafs = new Array<>();

    public TreeLeaf(T parent) {
        this.parent = parent;
    }

    public T getParent() {
        return parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Array<? extends T> getChildrens() {
        return childrens;
    }

    public Array<L> getLeafs() {
        return leafs;
    }

    public void addLeaf(L leaf) {
        leafs.add(leaf);
    }

    public void addChild(T treeLeaf) {
        childrens.add(treeLeaf);
    }

    public void traceAllPath() {
        for (T children : childrens) {
            children.traceAllPath();
        }

        StringBuilder sb = new StringBuilder(this.toString());
        TreeLeaf parent = getParent();
        while (parent != null) {
            sb.insert(0, parent + "/");
            parent = parent.getParent();
        }

        for (L leaf : leafs) {
            System.out.println(sb + "-" + leaf);
        }
    }
}


