package com.github.fabiitch.nz.java.ai.btree;

public interface BTaskExecutor<E> {

     BTask<E> getTask();
     BTaskExecutor<E> getParent();

     default void onStart(E entity){
         getTask().onStart(entity);
     }

    default BTaskContext<E> execute(BTaskContext<E> context) {
        while (!context.isTickFinish()) {
            context = getTask().execute(context);
        }
        if (context.getStatus().isFinish()) {
            context.setExecutor(getParent());
        }
        return context;
    }
}
