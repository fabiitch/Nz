package com.github.fabiitch.nz.java.ai.btree;

public interface BTask<E> {

    void onStart(E entity);
    void onEnd(E entity, BTaskStatus status);

    BTaskContext<E> execute(BTaskContext<E> context);
}
