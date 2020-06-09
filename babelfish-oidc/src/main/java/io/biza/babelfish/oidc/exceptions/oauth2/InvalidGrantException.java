package io.biza.babelfish.oidc.exceptions.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidGrantException extends Exception {
  private static final long serialVersionUID = 1L;
  private String errorDescription;
}
