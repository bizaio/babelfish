package io.biza.babelfish.oidc.exceptions;


public class InvalidRequestUriException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public InvalidRequestUriException(String message) {
    super(message);
  }
  
  public InvalidRequestUriException() {
    super();
  }
}
