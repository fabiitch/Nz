package com.fabiitch.nz.java.algo;

import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public interface DependantSorter {

    <T extends DependantSorter> List<T> waitingList();

    static <T extends DependantSorter> List<T> sort(List<T> dependantsList) throws SequenceLockException {
        List<T> notInits = new ArrayList<>(dependantsList);
        List<T> res = new ArrayList<>(dependantsList.size());
        while (!notInits.isEmpty()) {
            T sequenceToInit = null;

            for (T sequence : notInits) {
                List<DependantSorter> needs = sequence.waitingList();
                if (needs == null || needs.isEmpty()) {
                    sequenceToInit = sequence;
                    break;
                }
                needs = needs.stream().filter(s -> s != null && s != sequence).collect(Collectors.toList());
                if (res.containsAll(needs)) {
                    sequenceToInit = sequence;
                    break;
                }
            }
            if (sequenceToInit == null) {
                StringBuilder sb = new StringBuilder("");
                for (DependantSorter sequence : notInits) {
                    sb.append(sequence.getClass().getSimpleName(), ",");
                }
                throw new SequenceLockException("DependantSorter" + sb);
            } else {
                res.add(sequenceToInit);
                notInits.remove(sequenceToInit);
            }
        }
        return res;
    }
}
