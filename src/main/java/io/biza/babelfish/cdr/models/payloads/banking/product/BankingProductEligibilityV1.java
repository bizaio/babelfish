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
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BankingProductEligibilityType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Eligibility criteria to obtain a particular banking product", name = "BankingProductEligibilityV1")
public class BankingProductEligibilityV1 {
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
  
  @AssertTrueBabelfish(
      message = "Additional Information must be populated when Eligibility type is OTHER",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.OTHER})
            .contains(eligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE",
      fields = {"additionalValue"})
  private boolean isValuePositiveInteger() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.MIN_AGE,
            BankingProductEligibilityType.MAX_AGE}).contains(eligibilityType())
                ? FormatChecker.isPositiveInteger(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER",
      fields = {"additionalValue"})
  private boolean isValueAmount() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {
            BankingProductEligibilityType.MIN_INCOME, BankingProductEligibilityType.MIN_TURNOVER})
            .contains(eligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isAmountString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must String when Eligibility type is EMPLOYMENT_STATUS or RESIDENCY_STATUS",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(eligibilityType()) ? (Arrays.asList(
        new BankingProductEligibilityType[] {BankingProductEligibilityType.EMPLOYMENT_STATUS,
            BankingProductEligibilityType.RESIDENCY_STATUS})
        .contains(eligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true) : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Eligibility type is STAFF, STUDENT, NATURAL_PERSON, BUSINESS, PENSION_RECIPIENT or OTHER",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.STAFF,
            BankingProductEligibilityType.NATURAL_PERSON, BankingProductEligibilityType.OTHER,
            BankingProductEligibilityType.BUSINESS, BankingProductEligibilityType.PENSION_RECIPIENT,
            BankingProductEligibilityType.STUDENT}).contains(eligibilityType())
                ? !FormatChecker.isNotEmpty(additionalValue())
                : true)
        : true;
  }
}
