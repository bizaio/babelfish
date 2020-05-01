package io.biza.babelfish.oauth2.exceptions;


public class UnsupportedResponseTypeException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public UnsupportedResponseTypeException(String message) {
    super(message);
  }
}
