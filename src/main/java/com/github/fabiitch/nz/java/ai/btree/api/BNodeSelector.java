package com.github.fabiitch.nz.java.ai.btree.api;

import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BNodeSelector<E> implements BTaskExecutor<E> {
    BSelector<E> selector;
    BTaskExecutor<E> parent;
    @Override
    public BTask<E> getTask() {
        return selector;
    }

    @Override
    public BTaskExecutor<E> getParent() {
        return parent;
    }
}
