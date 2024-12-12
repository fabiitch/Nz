package com.github.fabiitch.nz.java.ai.btree.impl;

import com.badlogic.gdx.utils.ObjectMap;
import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskContext;
import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;
import com.github.fabiitch.nz.java.ai.btree.BTree;

public class BTreeTest extends BTree<BTreeEntityTest> {
    private ObjectMap<BTreeEntityTest, BTaskExecutor<BTreeEntityTest>> mapSave = new ObjectMap<>();

    @Override
    public void saveEntityExecutor(BTreeEntityTest entity, BTaskExecutor<BTreeEntityTest> executor) {
        mapSave.put(entity, executor);
    }

    @Override
    public BTask<BTreeEntityTest> getTask() {
        return null;
    }

    @Override
    public BTaskExecutor<BTreeEntityTest> getParent() {
        return null;
    }

    @Override
    public void removeEntity(BTreeEntityTest entity) {
        mapSave.remove(entity);
    }

    @Override
    public BTaskExecutor<BTreeEntityTest> getEntityExecutor(BTreeEntityTest entity) {
        return mapSave.get(entity);
    }

    @Override
    public BTaskExecutor<BTreeEntityTest> getRootExecutor() {
        return null;
    }

    @Override
    public BTaskContext<BTreeEntityTest> getContext() {
        return new BTaskContext<>();
    }

    @Override
    public void freeContext(BTaskContext<BTreeEntityTest> context) {

    }

}
