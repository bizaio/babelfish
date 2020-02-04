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

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountType;
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
@Schema(name = "BankingProductDiscount", description = "Banking Product Discount Specification")
public class BankingProductDiscountV1 {
  @Schema(description = "Description of the discount", required = true)
  @NotNull
  @JsonProperty("description")
  String description;

  @Schema(
      description = "The type of discount. See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonProperty("discountType")
  BankingProductDiscountType discountType;

  @Schema(
      description = "Value of the discount. When following properties include one of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("amount")
  BigDecimal amount;

  @Schema(
      description = "A discount rate calculated based on a proportion of the balance. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("balanceRate")
  BigDecimal balanceRate;

  @Schema(
      description = "A discount rate calculated based on a proportion of a transaction. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("transactionRate")
  BigDecimal transactionRate;

  @Schema(
      description = "A discount rate calculated based on a proportion of the calculated interest accrued on the account. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("accruedRate")
  BigDecimal accruedRate;

  @Schema(
      description = "A discount rate calculated based on a proportion of the fee to which this discount is attached. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("feeRate")
  BigDecimal feeRate;

  @Schema(
      description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(description = "Display text providing more information on the discount")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this discount",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;

  @Schema(description = "Eligibility constraints that apply to this discount")
  @JsonProperty("eligibility")
  @Valid
  List<BankingProductFeeDiscountEligibilityV1> eligibility;

  @AssertTrueBabelfish(
      message = "Eligibility Criteria must be populated when Discount type is ELIGIBILITY_ONLY",
      fields = {"eligibility"})
  private boolean isEligibilityPopulated() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays
            .asList(new BankingProductDiscountType[] {BankingProductDiscountType.ELIGIBILITY_ONLY})
            .contains(discountType()) ? (eligibility() != null && eligibility().size() > 0) : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Discount type is BALANCE, DEPOSITS or PAYMENTS",
      fields = {"additionalValue"})
  private boolean isValueAmount() {
    return FormatChecker.isDefined(discountType()) ? (Arrays
        .asList(new BankingProductDiscountType[] {BankingProductDiscountType.BALANCE,
            BankingProductDiscountType.DEPOSITS, BankingProductDiscountType.PAYMENTS})
        .contains(discountType())
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Duration String when Discount type is FEE_CAP",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays.asList(new BankingProductDiscountType[] {BankingProductDiscountType.FEE_CAP})
            .contains(discountType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Discount Type is ELIGIBILITY_ONLY",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays
            .asList(new BankingProductDiscountType[] {BankingProductDiscountType.ELIGIBILITY_ONLY})
            .contains(discountType()) ? !FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }
}
