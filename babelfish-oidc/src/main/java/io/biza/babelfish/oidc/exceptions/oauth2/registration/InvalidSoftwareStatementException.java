package io.biza.babelfish.oidc.exceptions.oauth2.registration;

import lombok.Builder;

@Builder
public class InvalidSoftwareStatementException extends Exception {
  private static final long serialVersionUID = 1L;
}
