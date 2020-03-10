package io.biza.babelfish.spring.exceptions;

import javax.validation.constraints.NotNull;
import io.biza.babelfish.cdr.enumerations.register.RegistrationErrorType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class RegistrationErrorException extends Exception {
  private static final long serialVersionUID = 1L;

  @NotNull
  @NonNull
  RegistrationErrorType error;
  String errorDescription;

}
