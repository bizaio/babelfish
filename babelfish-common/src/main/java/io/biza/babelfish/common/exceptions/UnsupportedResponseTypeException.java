package io.biza.babelfish.common.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UnsupportedResponseTypeException extends Exception {
  private static final long serialVersionUID = 1L;
  
  String message;
  
  public String getMessage() {
	  return message();
  }
  
}
