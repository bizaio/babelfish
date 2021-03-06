package io.biza.babelfish.cdr.models.payloads.register.recipient;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.cdr.enumerations.register.DataRecipientBrandStatusType;
import io.biza.babelfish.common.jackson.UriStringToUriConverter;
import io.biza.babelfish.common.jackson.UriToUriStringConverter;
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
public class DataRecipientBrandMetaDataV1 {
  
  @JsonProperty("dataRecipientBrandId")
  @NotEmpty
  @Schema(
      description = "Unique id of the Data Recipient brand")
  String dataRecipientBrandId;
  
  @JsonProperty("brandName")
  @NotEmpty
  @Schema(
      description = "Data Recipient Brand name")
  String brandName;
  
  @JsonProperty("logoUri")
  @Schema(description = "Data Recipient Brand logo URI", type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  URI logoUri;
  
  @JsonProperty("softwareProducts")
  @Schema(description = "Data Recipient Brand Software Products")
  List<SoftwareProductMetaDataV1> softwareProducts;
  
  @JsonProperty("status")
  @Schema(description = "Data Recipient Brand Status")
  DataRecipientBrandStatusType status;
  
}
