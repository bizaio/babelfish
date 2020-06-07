package io.biza.babelfish.oidc.exceptions.oauth2.registration;

import java.net.URI;

import lombok.Builder;

@Builder
public class InvalidRedirectUriException extends Exception {
  private static final long serialVersionUID = 1L;
  private String errorDescription;
  private URI errorUri;
}
