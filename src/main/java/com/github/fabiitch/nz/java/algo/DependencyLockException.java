package com.github.fabiitch.nz.java.algo;


import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.log.StrFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DependencyLockException extends RuntimeException {
    private Array<DependantSorter<?>> blockers;

    public DependencyLockException(String message, DependantSorter... blockers) {
        super(message);
        this.blockers = new Array<>(blockers);
    }

    private String getBlockers() {
        Function<DependantSorter, String> function = DependantSorter::name;
        return StrFormat.join(", ", function, blockers);
    }

}
