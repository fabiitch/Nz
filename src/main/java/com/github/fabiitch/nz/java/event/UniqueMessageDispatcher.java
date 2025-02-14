package com.github.fabiitch.nz.java.event;

public class UniqueMessageDispatcher<T> implements IMessageDispatcher<T> {
    private Listener<T> listener;

    @Override
    public void dispatch(T message) {
        if (listener != null)
            listener.onEvent(message);
    }

    @Override
    public void addListener(Listener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void removeListener(Listener<T> listener) {
        this.listener = null;
    }

    @Override
    public void clearListeners() {
        this.listener = null;
    }
}
