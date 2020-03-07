package io.biza.babelfish.cdr.models.payloads.register.recipient;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.register.SoftwareProductStatusType;
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
public class SoftwareProductStatus {
  
  @JsonProperty("softwareProductId")
  @NotEmpty
  @Schema(
      description = "Unique Software Product Identifier")
  String softwareProductId;
  
  @JsonProperty("softwareProductStatus")
  @NotNull
  @Schema(
      description = "Software Product Status")
  SoftwareProductStatusType softwareProductStatus;

}
