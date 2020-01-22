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
package io.biza.babelfish.cdr.model.banking;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
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
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingLoanRepaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Banking Loan Account Details")
public abstract class BankingLoanAccount<T> {
  @Schema(description = "Original Loan Start Date", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("originalStartDate")
  LocalDate originalStartDate;

  public LocalDate originalStartDate() {
    return getOriginalStartDate();
  }

  @SuppressWarnings("unchecked")
  public T originalStartDate(LocalDate originalStartDate) {
    setOriginalStartDate(originalStartDate);
    return (T) this;
  }

  @Schema(description = "Original Loan Value", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("originalLoanAmount")
  BigDecimal originalLoanAmount;

  public BigDecimal originalLoanAmount() {
    return getOriginalLoanAmount();
  }

  @SuppressWarnings("unchecked")
  public T originalLoanAmount(BigDecimal originalLoanAmount) {
    setOriginalLoanAmount(originalLoanAmount);
    return (T) this;
  }

  @Schema(description = "Original Loan Value Currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("originalLoanCurrency")
  Currency originalLoanCurrency = Currency.getInstance("AUD");

  public Currency originalLoanCurrency() {
    return getOriginalLoanCurrency();
  }

  @SuppressWarnings("unchecked")
  public T originalLoanCurrency(Currency originalLoanCurrency) {
    setOriginalLoanCurrency(originalLoanCurrency);
    return (T) this;
  }

  @Schema(description = "Date that the loan is due to be repaid in full", required = true,
      type = "string", format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("loanEndDate")
  LocalDate loanEndDate;

  public LocalDate loanEndDate() {
    return getLoanEndDate();
  }

  @SuppressWarnings("unchecked")
  public T loanEndDate(LocalDate loanEndDate) {
    setLoanEndDate(loanEndDate);
    return (T) this;
  }

  @Schema(description = "Next date that an instalment is required", required = true,
      format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("nextInstalmentDate")
  LocalDate nextInstalmentDate;

  public LocalDate nextInstalmentDate() {
    return getNextInstalmentDate();
  }

  @SuppressWarnings("unchecked")
  public T nextInstalmentDate(LocalDate nextInstalmentDate) {
    setNextInstalmentDate(nextInstalmentDate);
    return (T) this;
  }

  @Schema(description = "Minimum amount of next instalment", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("minInstalmentAmount")
  BigDecimal minInstalmentAmount;

  public BigDecimal minInstalmentAmount() {
    return getMinInstalmentAmount();
  }

  @SuppressWarnings("unchecked")
  public T minInstalmentAmount(BigDecimal minInstalmentAmount) {
    setMinInstalmentAmount(minInstalmentAmount);
    return (T) this;
  }

  @Schema(description = "Minimum amount currency", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("minInstalmentCurrency")
  Currency minInstalmentCurrency = Currency.getInstance("AUD");

  public Currency minInstalmentCurrency() {
    return getMinInstalmentCurrency();
  }

  @SuppressWarnings("unchecked")
  public T minInstalmentCurrency(Currency minInstalmentCurrency) {
    setMinInstalmentCurrency(minInstalmentCurrency);
    return (T) this;
  }

  @Schema(
      description = "Maximum amount of funds that can be redrawn. If not present redraw is not available even if the feature exists for the account",
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("maxRedraw")
  BigDecimal maxRedraw;

  public BigDecimal maxRedraw() {
    return getMaxRedraw();
  }

  @SuppressWarnings("unchecked")
  public T maxRedraw(BigDecimal maxRedraw) {
    setMaxRedraw(maxRedraw);
    return (T) this;
  }

  @Schema(description = "Maximum redraw amount currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("maxRedrawCurrency")
  Currency maxRedrawCurrency = Currency.getInstance("AUD");

  public Currency maxRedrawCurrency() {
    return getMaxRedrawCurrency();
  }

  @SuppressWarnings("unchecked")
  public T maxRedrawCurrency(Currency maxRedrawCurrency) {
    setMaxRedrawCurrency(maxRedrawCurrency);
    return (T) this;
  }

  @Schema(description = "Minimum redraw amount", type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("minRedraw")
  private BigDecimal minRedraw;

  public BigDecimal minRedraw() {
    return getMinRedraw();
  }

  @SuppressWarnings("unchecked")
  public T minRedraw(BigDecimal minRedraw) {
    setMinRedraw(minRedraw);
    return (T) this;
  }

  @Schema(description = "Minimum Redraw currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("minRedrawCurrency")
  Currency minRedrawCurrency = Currency.getInstance("AUD");

  public Currency minRedrawCurrency() {
    return getMinRedrawCurrency();
  }

  @SuppressWarnings("unchecked")
  public T minRedrawCurrency(Currency minRedrawCurrency) {
    setMinRedrawCurrency(minRedrawCurrency);
    return (T) this;
  }

  @Schema(
      description = "Set to true if one or more offset accounts are configured for this loan account")
  @JsonProperty("offsetAccountEnabled")
  Boolean offsetAccountEnabled;

  public Boolean offsetAccountEnabled() {
    return getOffsetAccountEnabled();
  }

  @SuppressWarnings("unchecked")
  public T offsetAccountEnabled(Boolean offsetAccountEnabled) {
    setOffsetAccountEnabled(offsetAccountEnabled);
    return (T) this;
  }

  @Schema(
      description = "The accountIDs of the configured offset accounts attached to this loan. Only offset accounts that can be accessed under the current authorisation should be included. It is expected behaviour that offsetAccountEnabled is set to true but the offsetAccountIds field is absent or empty. This represents a situation where an offset account exists but details can not be accessed under the current authorisation")
  @JsonProperty("offsetAccountIds")
  List<String> offsetAccountIds;

  @Schema(
      description = "Options in place for repayments. If absent defaults to PRINCIPAL_AND_INTEREST")
  @JsonProperty("repaymentType")
  BankingLoanRepaymentType repaymentType = BankingLoanRepaymentType.PRINCIPAL_AND_INTEREST;

  public BankingLoanRepaymentType repaymentType() {
    return getRepaymentType();
  }

  @SuppressWarnings("unchecked")
  public T repaymentType(BankingLoanRepaymentType repaymentType) {
    setRepaymentType(repaymentType);
    return (T) this;
  }

  @Schema(
      description = "The expected or required repayment frequency. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @NotNull
  @JsonSerialize(converter = DurationToStringConverter.class)
  @JsonProperty("repaymentFrequency")
  Duration repaymentFrequency;

  public Duration repaymentFrequency() {
    return getRepaymentFrequency();
  }

  @SuppressWarnings("unchecked")
  public T repaymentFrequency(Duration repaymentFrequency) {
    setRepaymentFrequency(repaymentFrequency);
    return (T) this;
  }
}
