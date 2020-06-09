package io.biza.babelfish.cdr.models.responses.register;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.models.payloads.register.recipient.RegisterDataRecipientV1;
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
@Schema(description = "Response containing a list of Data Recipients in the CDR Register")
public class ResponseRegisterDataRecipientListV1 {
  
  @JsonProperty("data")
  @Schema(
      description = "A list of Data Recipients")
  @NotNull
  List<RegisterDataRecipientV1> data;
  
}
