package com.github.fabiitch.nz.java.ai.btree.impl;

import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BSimpleExecutor<E> implements BTaskExecutor<E> {

    private BTask<E> task;
    private BTaskExecutor<E> parent;

    @Override
    public BTask<E> getTask() {
        return task;
    }

    @Override
    public BTaskExecutor<E> getParent() {
        return parent;
    }
}
