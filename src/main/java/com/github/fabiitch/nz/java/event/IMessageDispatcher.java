package com.github.fabiitch.nz.java.event;

public interface IMessageDispatcher<T> {

    void dispatch(T message);

    void addListener(Listener<T> listener);

    void removeListener(Listener<T> listener);

    void clearListeners();
}
