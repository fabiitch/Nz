package com.github.fabiitch.nz.gdx.input;

import com.badlogic.gdx.utils.Array;

//TODO
public class TouchInputResolver {

    public Array<TouchInputArea> areaArray = new Array<>();

    public TouchInputResolver() {

    }

    public TouchInputResolver add(TouchInputArea touchInputArea) {
        return this;
    }
}
