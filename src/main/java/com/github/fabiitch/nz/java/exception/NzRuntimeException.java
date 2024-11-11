package com.github.fabiitch.nz.java.exception;

import com.github.fabiitch.nz.gdx.log.StrFormat;

/**
 * Just call StrFormat.format(message)
 */
public class NzRuntimeException extends RuntimeException {

    public NzRuntimeException(String message, Object... params) {
        super(StrFormat.format(message, params));
    }

    public NzRuntimeException(String message, Throwable cause, Object... params) {
        super(StrFormat.format(message, params), cause);
    }

}
