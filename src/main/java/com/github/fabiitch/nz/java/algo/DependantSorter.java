package com.github.fabiitch.nz.java.algo;

import com.badlogic.gdx.utils.StringBuilder;
import com.github.fabiitch.nz.gdx.log.StrFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public interface DependantSorter<T extends DependantSorter<T>> {

    List<T> dependencies();

    String name();

    static <T extends DependantSorter> List<T> sort(List<T> dependencies) throws DependencyLockException {
        List<T> notInits = new ArrayList<>(dependencies);
        List<T> res = new ArrayList<>(dependencies.size());
        while (!notInits.isEmpty()) {
            T sequenceToInit = null;

            for (T sequence : notInits) {
                List<DependantSorter> needs = sequence.dependencies();
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
                Function<DependantSorter, String> toStr = DependantSorter::toString;
                throw new DependencyLockException(StrFormat.join(", ", toStr, notInits));
            } else {
                res.add(sequenceToInit);
                notInits.remove(sequenceToInit);
            }
        }
        return res;
    }
}
