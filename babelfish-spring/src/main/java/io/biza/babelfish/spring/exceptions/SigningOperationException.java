package io.biza.babelfish.spring.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SigningOperationException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public String message;
}
