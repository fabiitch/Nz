package com.github.fabiitch.nz.demo.internal.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class InputKeyBinder extends InputAdapter {

    private IntMap<Array<KeyBinderFunction>> functionIntMap = new IntMap<>();

    public void add(KeyBinderFunction function) {
        Array<KeyBinderFunction> functionsForKey = functionIntMap.get(function.getKey());
        if (functionsForKey == null) {
            functionsForKey = new Array<>();
            functionIntMap.put(function.getKey(), functionsForKey);
        }
        functionsForKey.add(function);
    }

    public void remove(KeyBinderFunction function) {
        Array<KeyBinderFunction> functionsForKey = functionIntMap.get(function.getKey());
        if (functionsForKey != null) {
            functionsForKey.removeValue(function, true);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Array<KeyBinderFunction> keyBinderFunctions = functionIntMap.get(keycode);
        if (keyBinderFunctions != null) {
            for (KeyBinderFunction function : keyBinderFunctions) {
                if (function.isOnKeyDown())
                    function.getDoit().doIt();
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Array<KeyBinderFunction> keyBinderFunctions = functionIntMap.get(keycode);
        if (keyBinderFunctions != null) {
            for (KeyBinderFunction function : keyBinderFunctions) {
                if (function.isOnKeyUp())
                    function.getDoit().doIt();
            }
        }
        return false;
    }
}
