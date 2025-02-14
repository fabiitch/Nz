package com.github.fabiitch.nz.java.event;

import com.badlogic.gdx.utils.Array;

public class MessageSenderThreadSafe<T> implements IMessageDispatcher<T> {
    private final Array<Listener<T>> listeners = new Array<>();

    @Override
    public void dispatch(T message) {
        synchronized (listeners) {
            for (Listener<T> listener : listeners) {
                listener.onEvent(message);
            }
        }
    }

    @Override
    public void addListener(Listener<T> listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(Listener<T> listener) {
        synchronized (listeners) {
            listeners.removeValue(listener, true);
        }
    }

    @Override
    public void clearListeners() {
        synchronized (listeners) {
            listeners.clear();
        }
    }
}
