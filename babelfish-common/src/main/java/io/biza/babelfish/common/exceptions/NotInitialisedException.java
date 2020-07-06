package io.biza.babelfish.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotInitialisedException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public String message;
}
