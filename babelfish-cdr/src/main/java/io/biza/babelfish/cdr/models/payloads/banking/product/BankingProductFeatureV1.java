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

import io.biza.babelfish.cdr.enumerations.BankingProductFeatureType;
import io.biza.babelfish.common.jackson.UriStringToUriConverter;
import io.biza.babelfish.common.jackson.UriToUriStringConverter;
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
@Schema(description = "A Banking Product Feature", name = "BankingProductFeatureV1")
public class BankingProductFeatureV1
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductFeatureV1 {
  @Schema(description = "The type of feature described", required = true)
  @JsonProperty("featureType")
  @Valid
  @NotNull
  BankingProductFeatureType featureType;

  @Schema(
      description = "Generic field containing additional information relevant to the [featureType](#tocSproductfeaturetypedoc) specified. Whether mandatory or not is dependent on the value of the [featureType.](#tocSproductfeaturetypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(
      description = "Display text providing more information on the feature. Mandatory if the [feature type](#tocSproductfeaturetypedoc) is set to OTHER")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this feature", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;

}
