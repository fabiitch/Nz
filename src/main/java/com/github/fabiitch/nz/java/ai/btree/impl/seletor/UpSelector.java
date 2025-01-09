package com.github.fabiitch.nz.java.ai.btree.impl.seletor;

import com.github.fabiitch.nz.java.ai.btree.BTaskExecutor;
import com.github.fabiitch.nz.java.ai.btree.BTaskStatus;
import com.github.fabiitch.nz.java.ai.btree.api.BSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.BTestMain;
import com.github.fabiitch.nz.java.ai.btree.impl.BTreeEntityTest;

public class UpSelector implements BSelector<BTreeEntityTest> {
    BTaskExecutor<BTreeEntityTest> fastUpTask;
    BTaskExecutor<BTreeEntityTest> slowUpTask;

    @Override
    public BTaskExecutor<BTreeEntityTest> select(BTreeEntityTest entity) {
        if (Math.abs(entity.value - entity.target) > BTestMain.FAST)
            return fastUpTask;
        return slowUpTask;
    }

    @Override
    public void onStart(BTreeEntityTest entity) {

    }

    @Override
    public void onEnd(BTreeEntityTest entity, BTaskStatus status) {

    }
}
