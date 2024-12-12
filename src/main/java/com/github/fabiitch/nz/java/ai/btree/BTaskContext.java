package com.github.fabiitch.nz.java.ai.btree;

import lombok.Getter;

@Getter
public class BTaskContext<E> {
    private E entity;
    private float dtLeft;
    private BTaskExecutor<E> executor;

    private BTaskStatus status;

    public boolean isTickFinish() {
        return dtLeft == 0 || status.isFinish();
    }

    public BTaskContext<E> setDtLeft(float dtLeft) {
        this.dtLeft = dtLeft;
        return this;
    }

    public BTaskContext<E> setExecutor(BTaskExecutor<E> executor) {
        this.executor = executor;
        return this;
    }

    public BTaskContext<E> setStatus(BTaskStatus status) {
        this.status = status;
        return this;
    }

    public BTaskContext<E> end(BTaskStatus status) {
        this.status = status;
        this.dtLeft = 0;
        return this;
    }
}
