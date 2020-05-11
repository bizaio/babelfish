package io.biza.babelfish.common.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EncryptionOperationException extends Exception {
  private static final long serialVersionUID = 1L;
  
  private String message;
}
