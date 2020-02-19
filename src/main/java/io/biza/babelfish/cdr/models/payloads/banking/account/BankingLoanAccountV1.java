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
package io.biza.babelfish.cdr.models.payloads.banking.account;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.DurationToStringConverter;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.PeriodToStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.converters.StringToPeriodConverter;
import io.biza.babelfish.cdr.enumerations.BankingLoanRepaymentType;
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
@Schema(description = "Banking Loan Account Details", name = "BankingLoanAccountV1")
public class BankingLoanAccountV1 {
  @Schema(description = "Original Loan Start Date", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("originalStartDate")
  LocalDate originalStartDate;

  @Schema(description = "Original Loan Value", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("originalLoanAmount")
  BigDecimal originalLoanAmount;

  @Schema(description = "Original Loan Value Currency", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty(value = "originalLoanCurrency", defaultValue = "AUD")
  @Builder.Default
  Currency originalLoanCurrency = Currency.getInstance("AUD");

  @Schema(description = "Date that the loan is due to be repaid in full", required = true,
      type = "string", format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("loanEndDate")
  LocalDate loanEndDate;

  @Schema(description = "Next date that an instalment is required", required = true,
      format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("nextInstalmentDate")
  LocalDate nextInstalmentDate;

  @Schema(description = "Minimum amount of next instalment", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("minInstalmentAmount")
  BigDecimal minInstalmentAmount;

  @Schema(description = "Minimum amount currency", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty(value = "minInstalmentCurrency", defaultValue = "AUD")
  @Builder.Default
  Currency minInstalmentCurrency = Currency.getInstance("AUD");

  @Schema(
      description = "Maximum amount of funds that can be redrawn. If not present redraw is not available even if the feature exists for the account",
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("maxRedraw")
  BigDecimal maxRedraw;

  @Schema(description = "Maximum redraw amount currency", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty(value = "maxRedrawCurrency", defaultValue = "AUD")
  @Builder.Default
  Currency maxRedrawCurrency = Currency.getInstance("AUD");

  @Schema(description = "Minimum redraw amount", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("minRedraw")
  BigDecimal minRedraw;

  @Schema(description = "Minimum Redraw currency", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty(value = "minRedrawCurrency", defaultValue = "AUD")
  @Builder.Default
  Currency minRedrawCurrency = Currency.getInstance("AUD");

  @Schema(
      description = "Set to true if one or more offset accounts are configured for this loan account")
  @JsonProperty("offsetAccountEnabled")
  Boolean offsetAccountEnabled;

  @Schema(
      description = "The accountIDs of the configured offset accounts attached to this loan. Only offset accounts that can be accessed under the current authorisation should be included. It is expected behaviour that offsetAccountEnabled is set to true but the offsetAccountIds field is absent or empty. This represents a situation where an offset account exists but details can not be accessed under the current authorisation")
  @JsonProperty("offsetAccountIds")
  List<String> offsetAccountIds;

  @Schema(
      description = "Options in place for repayments. If absent defaults to PRINCIPAL_AND_INTEREST")
  @JsonProperty(value = "repaymentType", defaultValue = "PRINCIPAL_AND_INTEREST")
  @Builder.Default
  BankingLoanRepaymentType repaymentType = BankingLoanRepaymentType.PRINCIPAL_AND_INTEREST;

  @Schema(
      description = "The expected or required repayment frequency. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string",
      required = true)
  @NotNull
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("repaymentFrequency")
  Period repaymentFrequency;
  
}
