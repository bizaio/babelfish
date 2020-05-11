package io.biza.babelfish.common.exceptions;

import javax.validation.constraints.NotNull;

import io.biza.babelfish.common.enumerations.RegistrationErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@Schema(description = "Describes a Dynamic Registration Failure")
@ToString
public class RegistrationErrorException extends Exception {
  private static final long serialVersionUID = 1L;

  @Schema(description = "Registration Error Type")
  @NotNull
  @NonNull
  RegistrationErrorType errorType;
  
  @Schema(description = "Registration Error Description")
  String errorDescription;
  
  @Schema(description = "Throwable cause object for further inspection")
  Throwable errorCause;

}
