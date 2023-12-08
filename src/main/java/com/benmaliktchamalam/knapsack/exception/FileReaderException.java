package com.benmaliktchamalam.knapsack.exception;

/**
 * An exception thrown when a failure takes place while reading a file.
 *
 * @author ben-malik
 */
public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Exception exception) {
        super(message, exception);
    }
}
