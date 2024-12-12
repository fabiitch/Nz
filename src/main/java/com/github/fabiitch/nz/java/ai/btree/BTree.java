package com.github.fabiitch.nz.java.ai.btree;

public abstract class BTree<E> implements BTaskExecutor<E> {

    public BTaskStatus update(float dt, E entity) {
        BTaskExecutor<E> executor = getEntityExecutor(entity);
        if (executor == null) {
            executor = getRootExecutor();
        }

        BTaskContext<E> context = getContext();
        context.setEntity(entity);
        context.setDtLeft(dt);
        context.setExecutor(executor);

        context = executor.execute(context);

        if (context.getExecutor() != null) {
            saveEntityExecutor(entity, context.getExecutor());
        } else {
            removeEntity(entity);
        }
        freeContext(context);
        return context.getStatus();
    }

    @Override
    public BTask<E> getTask() {
        return getRootExecutor().getTask();
    }

    @Override
    public BTaskExecutor<E> getParent() {
        return null;
    }

    @Override
    public void onStart(E entity) {
        BTaskExecutor.super.onStart(entity);
    }

    @Override
    public BTaskContext<E> execute(BTaskContext<E> context) {
        return BTaskExecutor.super.execute(context);
    }

    public abstract void saveEntityExecutor(E entity, BTaskExecutor<E> executor);

    public abstract void removeEntity(E entity);

    public abstract BTaskExecutor<E> getEntityExecutor(E entity);

    public abstract BTaskExecutor<E> getRootExecutor();

    public abstract BTaskContext<E> getContext();

    public abstract void freeContext(BTaskContext<E> context);
}
