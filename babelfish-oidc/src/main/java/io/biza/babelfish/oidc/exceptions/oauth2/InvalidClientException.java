package io.biza.babelfish.oidc.exceptions.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidClientException extends Exception {
  private String wwwAuthenticate;
  private String errorDescription;
  private static final long serialVersionUID = 1L;
  
}
