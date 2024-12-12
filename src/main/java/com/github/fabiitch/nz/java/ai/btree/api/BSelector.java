package com.github.fabiitch.nz.java.ai.btree.api;

import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskContext;
import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;

public interface BSelector<E> extends BTask<E> {

    BTaskExecutor<E> select(E entity);

    @Override
    default BTaskContext<E> execute(BTaskContext<E> context) {
        BTaskExecutor<E> select = select(context.getEntity());
        context.setExecutor(select);
        return select.execute(context);
    }
}
