package com.fabiitch.nz.algo;

import com.badlogic.gdx.utils.Array;

import java.util.List;

/**
 *
 */
public interface InitOrdererAlgo {

    List<InitOrdererAlgo> waitingList();

    void init();

    void afterAllInit();

    /**
     * call InitWaitOther.init() on all, init is called if all in waitingList are initialised
     * @param initWaitOtherList
     * @throws Exception
     */
    static void initAll(Array<InitOrdererAlgo> initWaitOtherList) throws Exception {
        Array<InitOrdererAlgo> toInitArray = new Array<>(); //cpy
        Array<InitOrdererAlgo> rdyArray = new Array<>(); //cpy

        while (rdyArray.size < initWaitOtherList.size) {
            int sizeRdyBefore = rdyArray.size;

            toInitArray.addAll(initWaitOtherList);
            toInitArray.removeAll(rdyArray, true);
            for (InitOrdererAlgo toInit : toInitArray) {
                boolean canInit = true;
                List<InitOrdererAlgo> waitingServicesList = toInit.waitingList();
                if (waitingServicesList != null)
                    for (InitOrdererAlgo serviceWait : waitingServicesList) {
                        if (!rdyArray.contains(serviceWait, true)) {
                            canInit = false;
                            break;
                        }
                    }

                if (canInit) {
                    toInit.init();
                    rdyArray.add(toInit);
                }

            }
            if (sizeRdyBefore == rdyArray.size) {
                String blockedList = "";
                for (InitOrdererAlgo service : toInitArray) {
                    blockedList += service.getClass().getSimpleName() + " ,";
                }
                throw new Exception("Init blocking :" + blockedList);
            }
            toInitArray.clear();
        }

        for (InitOrdererAlgo toEndInit : initWaitOtherList)
            toEndInit.afterAllInit();
    }
}
