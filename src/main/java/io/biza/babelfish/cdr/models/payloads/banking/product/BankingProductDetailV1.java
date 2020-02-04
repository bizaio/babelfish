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
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Valid
@ToString
@EqualsAndHashCode
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Banking Product Detailed Information", name = "BankingProductDetailV1")
public class BankingProductDetailV1 {
  
  @Schema(
      description = "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
      required = true)
  @NotNull
  @JsonProperty("productId")
  String productId;

  @Schema(
      description = "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("effectiveFrom")
  OffsetDateTime effectiveFrom;

  @Schema(
      description = "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("effectiveTo")
  OffsetDateTime effectiveTo;

  @Schema(
      description = "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
      required = true, type = "string", format = "date-time")
  @NotNull
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastUpdated")
  OffsetDateTime lastUpdated;

  @Schema(required = true)
  @NotNull
  @JsonProperty("productCategory")
  BankingProductCategory productCategory;

  @Schema(description = "The display name of the product", required = true)
  @NotNull
  @JsonProperty("name")
  String name;

  @Schema(description = "A description of the product", required = true)
  @NotNull
  @JsonProperty("description")
  String description;

  @Schema(
      description = "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
      required = true)
  @NotNull
  @JsonProperty("brand")
  String brand;

  @Schema(description = "An optional display name of the brand")
  @JsonProperty("brandName")
  String brandName;

  @Schema(description = "A link to an application web page where this product can be applied for.",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("applicationUri")
  URI applicationUri;

  @Schema(
      description = "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
      required = true)
  @NotNull
  @JsonProperty("isTailored")
  Boolean tailored;

  @Schema(description = "Additional Information for Banking Product")
  @JsonProperty("additionalInformation")
  @Valid
  BankingProductAdditionalInformationV1 additionalInformation;

  @Schema(
      description = "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also")
  @JsonProperty("bundles")
  @Valid
  List<BankingProductBundleV1> bundles;

  @Schema(description = "Array of features available for the product")
  @JsonProperty("features")
  @Valid
  List<BankingProductFeatureV1> features;

  @Schema(
      description = "Constraints on the application for or operation of the product such as minimum balances or limit thresholds")
  @JsonProperty("constraints")
  @Valid
  List<BankingProductConstraintV1> constraints;

  @Schema(description = "Eligibility criteria for the product")
  @JsonProperty("eligibility")
  @Valid
  List<BankingProductEligibilityV1> eligibility;

  @Schema(description = "Fees applicable for the product")
  @JsonProperty("fees")
  @Valid
  List<BankingProductFeeV1> fees;

  @Schema(description = "Interest rates available for deposits")
  @JsonProperty("depositRates")
  @Valid
  List<BankingProductDepositRateV1> depositRates;

  @Schema(description = "Interest rates charged against lending balances")
  @JsonProperty("lendingRates")
  @Valid
  List<BankingProductLendingRateV1> lendingRates;
}
