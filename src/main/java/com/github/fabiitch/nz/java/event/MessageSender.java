package com.github.fabiitch.nz.java.event;

import com.badlogic.gdx.utils.Array;

public class MessageSender<T> {

    private final Array<Listener<T>> listeners = new Array<>();

    public void sendMessage(T message) {
        for (Listener<T> listener : listeners) {
            listener.onEvent(message);
        }
    }

    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener<T> listener) {
        listeners.removeValue(listener, true);
    }

    public void clearListeners() {
        listeners.clear();
    }
}
