package io.biza.babelfish.oidc.exceptions.openid.core;

import lombok.Builder;

@Builder
public class InvalidRequestUriException extends Exception {
  private static final long serialVersionUID = 1L;
}
