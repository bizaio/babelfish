package io.biza.babelfish.cdr.models.payloads.register.registration;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.register.RegistrationErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "An individual software product with status")
public class RegistrationErrorV1 {

  @JsonProperty("error")
  @NotNull
  @Schema(description = "Predefined error code as described in section 3.3 OIDC Dynamic Client Registration")
  RegistrationErrorType error;
  
  @JsonProperty("error_description")
  @NotEmpty
  @Schema(description = "Additional text description of the error for debugging.")
  String errorDescription;

}
