package com.github.fabiitch.nz.demo.internal.input;

import com.github.fabiitch.nz.java.function.DoIt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KeyBinderFunction {

    private int key;
    private DoIt doit;
    private boolean onKeyUp = false;
    private boolean onKeyDown = true;

    public KeyBinderFunction(int key, DoIt doit) {
        this.key = key;
        this.doit = doit;
    }
}
