package com.github.fabiitch.nz.java.ai.btree.impl;

import com.github.fabiitch.nz.java.ai.btree.impl.seletor.DownSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.seletor.MainSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.seletor.UpSelector;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BFastDownTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BFastUpTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BSlowDownTask;
import com.github.fabiitch.nz.java.ai.btree.impl.task.BSlowUpTask;

public class BTestMain {

    public static int FAST = 20;
    public static int SLOW = 1;


    public static void main(String[] args) {
        BTreeTest tree = new BTreeTest();
//        tree.setRootTask();

        BFastDownTask fastDownTask = new BFastDownTask();
        BSlowDownTask slowDownTask = new BSlowDownTask();
        BFastUpTask fastUpTask = new BFastUpTask();
        BSlowUpTask slowUpTask = new BSlowUpTask();

        DownSelector downSelector = new DownSelector();
        UpSelector upSelector = new UpSelector();
        MainSelector mainSelector = new MainSelector(downSelector, upSelector);

//        BCheckTask bCheckTask = new BCheckTask(mainSelector);


        BSimpleExecutor<BTreeEntityTest> mainSelectorNode = new BSimpleExecutor<>(mainSelector, tree);

        BSimpleExecutor<BTreeEntityTest> mainSelectorExecutor = new BSimpleExecutor<>(mainSelector, tree);
        BSimpleExecutor<BTreeEntityTest> downSelectorNode = new BSimpleExecutor<>(downSelector, mainSelectorNode);
        BSimpleExecutor<BTreeEntityTest> upSelectorNode = new BSimpleExecutor<>(upSelector, mainSelectorNode);


        BSimpleExecutor<BTreeEntityTest> slowDownNode = new BSimpleExecutor<>(slowDownTask, mainSelectorNode);
        BSimpleExecutor<BTreeEntityTest> fastDownNode = new BSimpleExecutor<>(fastDownTask, mainSelectorNode);
        BSimpleExecutor<BTreeEntityTest> slowUpNode = new BSimpleExecutor<>(slowUpTask, mainSelectorNode);
        BSimpleExecutor<BTreeEntityTest> fastUpNode = new BSimpleExecutor<>(fastUpTask, mainSelectorNode);




    }
}
