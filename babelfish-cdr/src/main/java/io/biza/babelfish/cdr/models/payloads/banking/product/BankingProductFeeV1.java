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
import java.time.Period;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.cdr.enumerations.BankingProductFeeType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.biza.babelfish.common.jackson.BigDecimalToRateStringConverter;
import io.biza.babelfish.common.jackson.CurrencyToStringConverter;
import io.biza.babelfish.common.jackson.PeriodToStringConverter;
import io.biza.babelfish.common.jackson.RateStringToBigDecimalConverter;
import io.biza.babelfish.common.jackson.StringToCurrencyConverter;
import io.biza.babelfish.common.jackson.StringToPeriodConverter;
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
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Banking Product Fee Definition", name = "BankingProductFeeV1")
public class BankingProductFeeV1 {
  @Schema(description = "Name of the fee", required = true)
  @NotEmpty(message = "Name is Required")
  @JsonProperty("name")
  String name;

  @Schema(description = "The type of fee", required = true)
  @NotNull
  @JsonProperty("feeType")
  BankingProductFeeType feeType;

  @Schema(
      description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("amount")
  BigDecimal amount;

  @Schema(
      description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("balanceRate")
  BigDecimal balanceRate;

  @Schema(
      description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("transactionRate")
  BigDecimal transactionRate;

  @Schema(
      description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("accruedRate")
  BigDecimal accruedRate;

  @Schema(
      description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("accrualFrequency")
  Period accrualFrequency;

  @Schema(description = "The currency the fee will be charged in", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("currency")
  @Builder.Default
  Currency currency = Currency.getInstance("AUD");

  @Schema(
      description = "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(description = "Display text providing more information on the fee")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this fee", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;

  @Schema(description = "An optional list of discounts to this fee that may be available")
  @JsonProperty("discounts")
  @Valid
  List<BankingProductDiscountV1> discounts;

  @AssertTrueBabelfish(
      message = "Additional Value must be a Duration String when Fee type is PERIODIC",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(feeType())
        ? (Arrays.asList(new BankingProductFeeType[] {BankingProductFeeType.PERIODIC})
            .contains(feeType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "One of amount, balanceRate, transactionRate or accruedRate is MANDATORY",
      fields = {"amount", "balanceRate", "accruedRate", "transactionRate"})
  private boolean isAmountOrRateSet() {
    return FormatChecker.isDefined(amount()) || FormatChecker.isDefined(balanceRate())
        || FormatChecker.isDefined(transactionRate()) || FormatChecker.isDefined(accruedRate());
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Fee Type is TRANSACTION, WITHDRAWAL, DEPOSIT, PAYMENT, PURCHASE, EVENT, UPFRONT or EXIT",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(feeType()) ? (Arrays
        .asList(new BankingProductFeeType[] {BankingProductFeeType.TRANSACTION,
            BankingProductFeeType.WITHDRAWAL, BankingProductFeeType.DEPOSIT,
            BankingProductFeeType.PAYMENT, BankingProductFeeType.PURCHASE,
            BankingProductFeeType.EVENT, BankingProductFeeType.UPFRONT, BankingProductFeeType.EXIT})
        .contains(feeType()) ? !FormatChecker.isDefined(additionalValue()) : true) : true;
  }


}
