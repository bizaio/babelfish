package io.biza.babelfish.oidc.exceptions.openid.core;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationNotSupportedException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public String message;
  
  @Override
  public String getMessage() {
	  return message();
  }
}
