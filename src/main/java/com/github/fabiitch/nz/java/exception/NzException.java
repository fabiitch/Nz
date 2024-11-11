package com.github.fabiitch.nz.java.exception;

import com.github.fabiitch.nz.gdx.log.StrFormat;

/**
 * Just call StrFormat.format(message)
 */
public class NzException extends Exception {

    public NzException(String message, Object... params) {
        super(StrFormat.format(message, params));
    }

    public NzException(String message, Throwable cause, Object... params) {
        super(StrFormat.format(message, params), cause);
    }
}
