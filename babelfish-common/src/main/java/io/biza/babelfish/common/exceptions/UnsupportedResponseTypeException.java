package io.biza.babelfish.common.exceptions;

import lombok.Builder;

@Builder
public class UnsupportedResponseTypeException extends Exception {
  private static final long serialVersionUID = 1L;  
}
