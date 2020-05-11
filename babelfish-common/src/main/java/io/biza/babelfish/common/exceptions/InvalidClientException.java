package io.biza.babelfish.common.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidClientException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public String message;
  
  @Override
  public String getMessage() {
	  return message();
  }
}
