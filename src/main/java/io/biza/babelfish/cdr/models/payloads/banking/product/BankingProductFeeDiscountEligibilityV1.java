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
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountEligibilityType;
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
@Schema(description = "Banking Product Discount Eligibility Details", name = "BankingProductFeeDiscountEligibilityV1")
public class BankingProductFeeDiscountEligibilityV1 {
  @Schema(description = "The type of the specific eligibility constraint for a discount",
      required = true)
  @JsonProperty("discountEligibilityType")
  @NotNull
  BankingProductDiscountEligibilityType discountEligibilityType;

  @Schema(description = "Additional Value for Eligibility Constraint related to Discount")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(description = "Display text providing more information on this eligibility constraint")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this eligibility constraint",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;
  
  @AssertTrueBabelfish(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE",
      fields = {"additionalValue"})
  private boolean isValuePositiveInteger() {
    return FormatChecker
        .isDefined(discountEligibilityType())
            ? (Arrays
                .asList(new BankingProductDiscountEligibilityType[] {
                    BankingProductDiscountEligibilityType.MIN_AGE,
                    BankingProductDiscountEligibilityType.MAX_AGE})
                .contains(discountEligibilityType())
                    ? FormatChecker.isPositiveInteger(additionalValue())
                    : true)
            : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER",
      fields = {"additionalValue"})
  private boolean isValueAmount() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays
            .asList(new BankingProductDiscountEligibilityType[] {
                BankingProductDiscountEligibilityType.MIN_INCOME,
                BankingProductDiscountEligibilityType.MIN_TURNOVER})
            .contains(discountEligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isAmountString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Duration String when Eligibility type is INTRODUCTORY",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays
            .asList(new BankingProductDiscountEligibilityType[] {
                BankingProductDiscountEligibilityType.INTRODUCTORY})
            .contains(discountEligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be String when Discount Eligibility Type is EMPLOYMENT_STATUS or RESIDENCY_STATUS",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays.asList(new BankingProductDiscountEligibilityType[] {
            BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS,
            BankingProductDiscountEligibilityType.RESIDENCY_STATUS}).contains(
                discountEligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }


  @AssertTrueBabelfish(
      message = "Additional Value should be null when Discount Eligibility type is BUSINESS, STAFF, NATURAL_PERSON or OTHER",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker
        .isDefined(discountEligibilityType())
            ? (Arrays
                .asList(new BankingProductDiscountEligibilityType[] {
                    BankingProductDiscountEligibilityType.BUSINESS,
                    BankingProductDiscountEligibilityType.STAFF,
                    BankingProductDiscountEligibilityType.NATURAL_PERSON,
                    BankingProductDiscountEligibilityType.OTHER})
                .contains(discountEligibilityType()) ? !FormatChecker.isNotEmpty(additionalValue())
                    : true)
            : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Information must be populated when Discount Eligibility type is OTHER",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(discountEligibilityType()) ? (Arrays.asList(
        new BankingProductDiscountEligibilityType[] {BankingProductDiscountEligibilityType.OTHER})
        .contains(discountEligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }
}
