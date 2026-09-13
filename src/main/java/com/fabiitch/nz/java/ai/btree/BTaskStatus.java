package com.fabiitch.nz.java.ai.btree;

public enum BTaskStatus {
    Run, Fail, Success;

    public boolean isFinish() {
        return this == Success || this == Fail;
    }
}
