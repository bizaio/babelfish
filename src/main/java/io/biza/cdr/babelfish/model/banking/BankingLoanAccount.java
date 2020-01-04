/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingLoanRepaymentType;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.converters.DurationToStringConverter;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Banking Loan Account Details")
public abstract class BankingLoanAccount {
  @BabelFishModelProperty(description = "Original Loan Start Date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate originalStartDate;

  @BabelFishModelProperty(description = "Original Loan Value", dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal originalLoanAmount;

  @BabelFishModelProperty(description = "Original Loan Value Currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency originalLoanCurrency = Currency.getInstance("AUD");

  @BabelFishModelProperty(description = "Date that the loan is due to be repaid in full",
      required = true, dataType = "java.lang.String")
  @NotNull
  @NonNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate loanEndDate;

  @BabelFishModelProperty(description = "Next date that an instalment is required", required = true)
  @NotNull
  @NonNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate nextInstalmentDate;

  @BabelFishModelProperty(description = "Minimum amount of next instalment",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal minInstalmentAmount;

  @BabelFishModelProperty(description = "Minimum amount currency", dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency minInstalmentCurrency = Currency.getInstance("AUD");

  @BabelFishModelProperty(
      description = "Maximum amount of funds that can be redrawn. If not present redraw is not available even if the feature exists for the account",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal maxRedraw;

  @BabelFishModelProperty(description = "Maximum redraw amount currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency maxRedrawCurrency = Currency.getInstance("AUD");

  @BabelFishModelProperty(description = "Minimum redraw amount", dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal minRedraw;

  @BabelFishModelProperty(description = "Minimum Redraw currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency minRedrawCurrency = Currency.getInstance("AUD");

  @BabelFishModelProperty(
      description = "Set to true if one or more offset accounts are configured for this loan account")
  Boolean offsetAccountEnabled;

  @BabelFishModelProperty(
      description = "The accountIDs of the configured offset accounts attached to this loan. Only offset accounts that can be accessed under the current authorisation should be included. It is expected behaviour that offsetAccountEnabled is set to true but the offsetAccountIds field is absent or empty. This represents a situation where an offset account exists but details can not be accessed under the current authorisation")
  List<String> offsetAccountIds;

  @BabelFishModelProperty(
      description = "Options in place for repayments. If absent defaults to PRINCIPAL_AND_INTEREST")
  BankingLoanRepaymentType repaymentType = BankingLoanRepaymentType.PRINCIPAL_AND_INTEREST;

  @BabelFishModelProperty(
      description = "The expected or required repayment frequency. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @NotNull
  @NonNull
  @JsonSerialize(converter = DurationToStringConverter.class)
  Duration repaymentFrequency;
}
