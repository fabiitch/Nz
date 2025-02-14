package com.github.fabiitch.nz.java.event;

import com.badlogic.gdx.utils.Array;

public class MessageDispatcher<T> implements IMessageDispatcher<T> {

    private final Array<Listener<T>> listeners = new Array<>();

    @Override
    public void dispatch(T message) {
        for (Listener<T> listener : listeners) {
            listener.onEvent(message);
        }
    }

    @Override
    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener<T> listener) {
        listeners.removeValue(listener, true);
    }

    @Override
    public void clearListeners() {
        listeners.clear();
    }
}
