package io.biza.babelfish.cdr.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlreadyInitialisedException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public String message;
}
