package com.fabiitch.nz.scene2D;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;


public interface SimpleClickListener extends EventListener {

    default boolean handle(Event e) {
        if (!(e instanceof InputEvent)) return false;

        InputEvent event = (InputEvent) e;
        if (event.getType() == InputEvent.Type.touchDown)
            onClick();
        return false;
    }

    void onClick();
}
