package com.github.fabiitch.nz.java.exception;

public class NzBadValueException extends NzRuntimeException {

    public NzBadValueException(String message, Object... params) {
        super(message, params);
    }
}
