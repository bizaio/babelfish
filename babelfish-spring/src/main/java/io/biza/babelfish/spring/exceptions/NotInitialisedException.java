package io.biza.babelfish.spring.exceptions;

import lombok.Builder;

@Builder
public class NotInitialisedException extends Exception {
  private static final long serialVersionUID = 1L;
}
