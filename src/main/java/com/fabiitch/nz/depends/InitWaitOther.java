package com.fabiitch.nz.depends;

import com.badlogic.gdx.utils.Array;

import java.util.List;

/**
 *
 */
public interface InitWaitOther {

    List<InitWaitOther> waitingList();

    void create();

    void afterAllInit();

    /**
     * call InitWaitOther.init() on all, init is called if all in waitingList are initialised
     * @param initWaitOtherList
     * @throws Exception
     */
    static void initAll(Array<InitWaitOther> initWaitOtherList) throws Exception {
        Array<InitWaitOther> toInitArray = new Array<>(); //cpy
        Array<InitWaitOther> rdyArray = new Array<>(); //cpy

        while (rdyArray.size < initWaitOtherList.size) {
            int sizeRdyBefore = rdyArray.size;

            toInitArray.addAll(initWaitOtherList);
            toInitArray.removeAll(rdyArray, true);
            for (InitWaitOther toInit : toInitArray) {
                boolean canInit = true;
                List<InitWaitOther> waitingServicesList = toInit.waitingList();
                if (waitingServicesList != null)
                    for (InitWaitOther serviceWait : waitingServicesList) {
                        if (!rdyArray.contains(serviceWait, true)) {
                            canInit = false;
                            break;
                        }
                    }

                if (canInit) {
                    toInit.create();
                    rdyArray.add(toInit);
                }

            }
            if (sizeRdyBefore == rdyArray.size) {
                String blockedList = "";
                for (InitWaitOther service : toInitArray) {
                    blockedList += service.getClass().getSimpleName() + " ,";
                }
                throw new Exception("Init blocking :" + blockedList);
            }
            toInitArray.clear();
        }

        for (InitWaitOther toEndInit : initWaitOtherList)
            toEndInit.afterAllInit();
    }
}
