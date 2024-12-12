package com.github.fabiitch.nz.java.ai.btree;

public interface BTaskExecutor<E> extends BTask<E>{

     BTask<E> getTask();
     BTaskExecutor<E> getParent();

     default void onStart(E entity){
         getTask().onStart(entity);
     }

    default void onEnd(E entity, BTaskStatus status){
         getTask().onEnd(entity, status);
    }

    default BTaskContext<E> execute(BTaskContext<E> context) {
        while (!context.isTickFinish()) {
            context = getTask().execute(context);
        }
        if (context.getStatus().isFinish()) {
            onEnd(context.getEntity(), context.getStatus());
            context.setExecutor(getParent());
        }
        return context;
    }
}
