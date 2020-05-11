package io.biza.babelfish.common.exceptions;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class UnsupportedGrantTypeException extends Exception {
  private static final long serialVersionUID = 1L;
}
