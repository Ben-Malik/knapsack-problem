package com.mobiquity.exception;

/**
 * The API exception thrown during any sort of failure in the app.
 * 
 * @author ben-malik
 */
public class APIException extends Exception {

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }
}
