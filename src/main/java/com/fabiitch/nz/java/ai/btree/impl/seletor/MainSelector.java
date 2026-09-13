package com.fabiitch.nz.java.ai.btree.impl.seletor;

import com.fabiitch.nz.java.ai.btree.BTaskExecutor;
import com.fabiitch.nz.java.ai.btree.BTaskStatus;
import com.fabiitch.nz.java.ai.btree.api.BSelector;
import com.fabiitch.nz.java.ai.btree.impl.BTreeEntityTest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainSelector implements BSelector<BTreeEntityTest> {
    DownSelector downSelector;
    UpSelector upSelector;

    @Override
    public BTaskExecutor<BTreeEntityTest> select(BTreeEntityTest entity) {
        if (entity.value > entity.target)
            return downSelector.select(entity);
        return upSelector.select(entity);
    }

    @Override
    public void onStart(BTreeEntityTest entity) {

    }

    @Override
    public void onEnd(BTreeEntityTest entity, BTaskStatus status) {

    }
}
