package io.biza.babelfish.spring.exceptions;

import javax.validation.constraints.NotNull;
import io.biza.babelfish.cdr.enumerations.register.RegistrationErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
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
  RegistrationErrorType errorType;
  String errorDescription;
  
  
  @Schema(description = "Throwable cause object for further inspection")
  Throwable errorCause;

}
