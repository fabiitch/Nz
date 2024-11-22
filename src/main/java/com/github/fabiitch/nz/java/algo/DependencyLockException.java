package com.github.fabiitch.nz.java.algo;


import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.gdx.log.StrFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DependencyLockException extends RuntimeException {

    public DependencyLockException(String message) {
        super(message);
    }


}
