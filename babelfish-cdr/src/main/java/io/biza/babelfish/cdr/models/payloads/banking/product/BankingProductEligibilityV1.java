/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.models.payloads.banking.product;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BankingProductEligibilityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Eligibility criteria to obtain a particular banking product",
    name = "BankingProductEligibilityV1")
public class BankingProductEligibilityV1
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductEligibilityV1 {
  @Schema(
      description = "The type of eligibility criteria described.  See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonProperty("eligibilityType")
  BankingProductEligibilityType eligibilityType;

  @Schema(
      description = "Generic field containing additional information relevant to the [eligibilityType](#tocSproducteligibilitytypedoc) specified.  Whether mandatory or not is dependent on the value of [eligibilityType](#tocSproducteligibilitytypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(
      description = "Display text providing more information on the eligibility criteria. Mandatory if the [eligibilityType](#tocSproducteligibilitytypedoc) field is set to OTHER")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this eligibility criteria",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  @Valid
  URI additionalInfoUri;

}
