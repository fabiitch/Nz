package com.github.fabiitch.nz.java.ai.btree.impl;

import com.github.fabiitch.nz.java.ai.btree.impl.seletor.DownSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.seletor.MainSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.seletor.UpSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BFastDownTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BFastUpTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BSlowDownTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BSlowUpTask;

public class BTestMain {

  public static int Target = 100;


    public static void main(String[] args) {
        BTreeTest tree = new BTreeTest();

        DownSelector downSelector = new DownSelector();
        UpSelector upSelector = new UpSelector();

        MainSelector mainSelector = new MainSelector(downSelector, upSelector);

        BFastDownTask fastDownTask = new BFastDownTask();
        BSlowDownTask slowDownTask = new BSlowDownTask();

        BFastUpTask fastUpTask = new BFastUpTask();
        BSlowUpTask slowUpTask = new BSlowUpTask();

        BSimpleExecutor<BTreeEntityTest> mainNode = new BSimpleExecutor<>(mainSelector, tree);
        BSimpleExecutor<BTreeEntityTest> slowDownNode = new BSimpleExecutor<>(slowDownTask, mainNode);
        BSimpleExecutor<BTreeEntityTest> fastDownNode = new BSimpleExecutor<>(fastDownTask, mainNode);
        BSimpleExecutor<BTreeEntityTest> slowUp = new BSimpleExecutor<>();
        BSimpleExecutor<BTreeEntityTest> fastUp = new BSimpleExecutor<>();


    }
}
