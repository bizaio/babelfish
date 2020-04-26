package io.biza.babelfish.cdr.models.payloads.register.recipient;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
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
@Schema(description = "Endpoints related to Data Holder Brand services")
public class SoftwareProductMetaDataV1 {
  
  @JsonProperty("softwareProductId")
  @NotEmpty
  @Schema(
      description = "Unique Software Product Identifier")
  String softwareProductId;
  
  @JsonProperty("softwareProductName")
  @NotEmpty
  @Schema(
      description = "Name of the software product")
  String softwareProductName;
  
  @JsonProperty("softwareProductDescription")
  @NotEmpty
  @Size(max = 8192)
  @Schema(
      description = "Description of the software product")
  String softwareProductDescription;
  
  @JsonProperty("logoUri")
  @Schema(description = "Data Recipient Brand logo URI", type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  URI logoUri;
  
  @JsonProperty("softwareProductStatus")
  @NotNull
  @Schema(
      description = "Software Product Status")
  SoftwareProductStatusType status;
  
}
