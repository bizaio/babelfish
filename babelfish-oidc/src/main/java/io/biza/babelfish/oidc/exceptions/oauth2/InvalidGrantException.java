package io.biza.babelfish.oidc.exceptions.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class InvalidGrantException extends Exception {
  private static final long serialVersionUID = 1L;
  private String errorDescription;  
}
