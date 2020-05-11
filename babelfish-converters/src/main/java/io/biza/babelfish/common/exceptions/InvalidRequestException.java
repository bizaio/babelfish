package io.biza.babelfish.common.exceptions;

import lombok.Builder;
import lombok.NoArgsConstructor;

public class InvalidRequestException extends Exception {
  public InvalidRequestException(String message) {
    super(message);
  }

  private static final long serialVersionUID = 1L;
}
