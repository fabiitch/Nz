package com.github.fabiitch.nz.gdx.scene2D.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.github.fabiitch.nz.java.function.DoIt;


public interface SimpleClickListener extends EventListener, DoIt {

    default boolean handle(Event e) {
        if (!(e instanceof InputEvent)) return false;

        InputEvent event = (InputEvent) e;
        if (event.getType() == InputEvent.Type.touchDown)
            onClick();
        return false;
    }

    void onClick();

    @Override
    default void doIt() {
        onClick();
    }
}
