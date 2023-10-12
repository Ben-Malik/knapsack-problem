package com.temizcode.exception;

/**
 * An exception thrown when a given input's format is invalid.
 *
 * @author ben-malik
 */
public class InputFormatException extends RuntimeException {

    public InputFormatException(String message) {
        super(message);
    }

}
