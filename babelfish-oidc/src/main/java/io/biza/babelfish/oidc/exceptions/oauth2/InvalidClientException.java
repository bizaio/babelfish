package io.biza.babelfish.oidc.exceptions.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidClientException extends Exception {
  private String wwwAuthenticate;
  private String errorDescription;
  private static final long serialVersionUID = 1L;
  
}
