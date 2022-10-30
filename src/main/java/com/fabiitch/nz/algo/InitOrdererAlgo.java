package com.fabiitch.nz.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface InitOrdererAlgo {

    List<InitOrdererAlgo> waitingList();

    void init();

    void afterAllInit();

    /**
     *
     * @param initWaitOtherList
     * @throws Exception
     */
    static void initAll(List<InitOrdererAlgo> initWaitOtherList) throws Exception {
        List<InitOrdererAlgo> toInitArray = new ArrayList<>(initWaitOtherList);
        List<InitOrdererAlgo> rdyArray = new ArrayList<>();

        Map<InitOrdererAlgo, List<InitOrdererAlgo>> mapWaitingList = new HashMap<>();
        for (InitOrdererAlgo init : toInitArray) {
            List<InitOrdererAlgo> waitingList = init.waitingList();
            if (waitingList != null)
                waitingList.remove(init);
            mapWaitingList.put(init, waitingList);
        }

        while (!toInitArray.isEmpty()) {
            InitOrdererAlgo toAdd = null;
            for (InitOrdererAlgo tryToInit : toInitArray) {
                List<InitOrdererAlgo> servicesWaitingForThis = mapWaitingList.get(tryToInit);
                if (servicesWaitingForThis == null || servicesWaitingForThis.isEmpty() || rdyArray.containsAll(servicesWaitingForThis)) {
                    toAdd = tryToInit;
                }
            }
            if (toAdd != null) {
                toAdd.init();
                rdyArray.add(toAdd);
                toInitArray.remove(toAdd);
            } else {
                String blockedList = "";
                for (InitOrdererAlgo service : toInitArray) {
                    blockedList += service.getClass().getSimpleName() + " ,";
                }
                throw new Exception("Init blocking :" + blockedList);
            }
        }

        for (InitOrdererAlgo toInit : rdyArray)
            toInit.afterAllInit();
    }

}
