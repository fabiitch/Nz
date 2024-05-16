package com.github.fabiitch.nz.java.function;

import com.badlogic.gdx.utils.Array;

@FunctionalInterface
public interface DoIt {
    void doIt();

    class Multi implements DoIt { //TODO a voir si utilisé
        public Array<DoIt> doItArray = new Array<>();

        @Override
        public void doIt() {
            for (DoIt doIt : doItArray) {
                doIt.doIt();
            }
        }
    }
}
