package com.github.fabiitch.nz.java.ai.btree.impl.task;

import com.github.fabiitch.nz.java.ai.btree.BTask;
import com.github.fabiitch.nz.java.ai.btree.BTaskContext;
import com.github.fabiitch.nz.java.ai.btree.BTaskStatus;
import com.github.fabiitch.nz.java.ai.btree.impl.BTestMain;
import com.github.fabiitch.nz.java.ai.btree.impl.BTreeEntityTest;

public class BFastUpTask implements BTask<BTreeEntityTest> {


    @Override
    public BTaskContext<BTreeEntityTest> execute(BTaskContext<BTreeEntityTest> context) {
        BTreeEntityTest entity = context.getEntity();
        entity.value += BTestMain.FAST;
        if (entity.value >= entity.target) {
            context.end(BTaskStatus.Success);
        } else
            context.end(BTaskStatus.Run);
        return context;
    }

    @Override
    public void onStart(BTreeEntityTest entity) {

    }

    @Override
    public void onEnd(BTreeEntityTest entity, BTaskStatus status) {

    }
}
