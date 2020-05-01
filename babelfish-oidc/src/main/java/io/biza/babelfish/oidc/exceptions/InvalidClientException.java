package io.biza.babelfish.oidc.exceptions;

import lombok.Builder;

@Builder
public class InvalidClientException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public InvalidClientException() {
    super();
  }
  
  public InvalidClientException(String message) {
    super(message);
  }
}
