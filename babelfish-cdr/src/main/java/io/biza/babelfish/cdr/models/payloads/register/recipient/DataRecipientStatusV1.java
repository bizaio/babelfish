package io.biza.babelfish.cdr.models.payloads.register.recipient;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.register.DataRecipientStatusType;
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
public class DataRecipientStatusV1 {
  
  @JsonProperty("dataRecipientId")
  @NotEmpty
  @Schema(
      description = "Unique Data Recipient Identifier")
  String dataRecipientId;
  
  @JsonProperty("dataRecipientStatus")
  @NotNull
  @Schema(
      description = "Data Recipient Status")
  DataRecipientStatusType dataRecipientStatus;

}
