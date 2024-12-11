package com.github.fabiitch.nz.java.ai.btree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BTaskContext<E> {
    private E entity;
    private float dtLeft;
    private BTaskExecutor<E> executor;

    private BTaskStatus status;

    public boolean isTickFinish() {
        return dtLeft == 0 || status.isFinish();
    }
}
