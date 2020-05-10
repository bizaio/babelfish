package io.biza.babelfish.oidc.exceptions;

import lombok.Builder;

@Builder
public class UnsupportedGrantTypeException extends Exception {
  private static final long serialVersionUID = 1L;
}
