package com.github.fabiitch.nz.java.ai.btree.impl.task;

import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskContext;
import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;
import com.github.fabiitch.nz.java.ai.btree.BTaskStatus;
import com.github.fabiitch.nz.java.ai.btree.impl.BTreeEntityTest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BCheckTask implements BTask<BTreeEntityTest> {

    private BTaskExecutor<BTreeEntityTest> mainSelector;

    @Override
    public void onStart(BTreeEntityTest entity) {

    }

    @Override
    public void onEnd(BTreeEntityTest entity, BTaskStatus status) {

    }

    @Override
    public BTaskContext<BTreeEntityTest> execute(BTaskContext<BTreeEntityTest> context) {
        BTreeEntityTest entity = context.getEntity();
        if (entity.value == entity.target) {
            return context.end(BTaskStatus.Success);
        }
        return context.setExecutor(mainSelector);
    }
}
