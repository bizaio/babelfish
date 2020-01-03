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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingLoanRepaymentType;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToDurationConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.converters.DurationToStringConverter;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Loan Account Details")
public interface BankingLoanAccount {

  @BabelFishModelProperty(description = "Original Loan Start Date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("originalStartDate")
  public LocalDate getOriginalStartDate();

  @JsonSetter("originalStartDate")
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  public void setOriginalStartDate(LocalDate originalStartDate);

  public default BankingLoanAccount originalStartDate(LocalDate originalStartDate) {
    setOriginalStartDate(originalStartDate);
    return this;
  }

  @BabelFishModelProperty(description = "Original Loan Value", dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("originalLoanAmount")
  public BigDecimal getOriginalLoanAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("originalLoanAmount")
  public void setOriginalLoanAmount(BigDecimal originalLoanAmount);

  public default BankingLoanAccount originalLoanAmount(BigDecimal originalLoanAmount) {
    setOriginalLoanAmount(originalLoanAmount);
    return this;
  }

  @BabelFishModelProperty(description = "Original Loan Value Currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("originalLoanCurrency")
  public Currency getOriginalLoanCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("originalLoanCurrency")
  public void setOriginalLoanCurrency(Currency originalLoanCurrency);

  public default BankingLoanAccount originalLoanCurrency(Currency originalLoanCurrency) {
    setOriginalLoanCurrency(originalLoanCurrency);
    return this;
  }

  @BabelFishModelProperty(description = "Date that the loan is due to be repaid in full",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("loanEndDate")
  public LocalDate getLoanEndDate();

  @JsonSetter("loanEndDate")
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  public void setLoanEndDate(@NotNull LocalDate loanEndDate);

  public default BankingLoanAccount loanEndDate(@NotNull LocalDate loanEndDate) {
    setLoanEndDate(loanEndDate);
    return this;
  }

  @BabelFishModelProperty(description = "Next date that an instalment is required", required = true)
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("nextInstalmentDate")
  public LocalDate getNextInstalmentDate();

  @JsonSetter("nextInstalmentDate")
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  public void setNextInstalmentDate(@NotNull LocalDate nextInstalmentDate);

  public default BankingLoanAccount nextInstalmentDate(@NotNull LocalDate nextInstalmentDate) {
    setNextInstalmentDate(nextInstalmentDate);
    return this;
  }

  @BabelFishModelProperty(description = "Minimum amount of next instalment",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("minInstalmentAmount")
  public BigDecimal getMinInstalmentAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("minInstalmentAmount")
  public void setMinInstalmentAmount(BigDecimal minInstalmentAmount);

  public default BankingLoanAccount minInstalmentAmount(BigDecimal minInstalmentAmount) {
    setMinInstalmentAmount(minInstalmentAmount);
    return this;
  }

  @BabelFishModelProperty(description = "Minimum amount currency", dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("minInstalmentCurrency")
  public Currency getMinInstalmentCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("minInstalmentCurrency")
  public void setMinInstalmentCurrency(Currency minInstalmentCurrency);

  public default BankingLoanAccount minInstalmentCurrency(Currency minInstalmentCurrency) {
    setMinInstalmentCurrency(minInstalmentCurrency);
    return this;
  }

  @BabelFishModelProperty(
      description = "Maximum amount of funds that can be redrawn. If not present redraw is not available even if the feature exists for the account",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("maxRedraw")
  public BigDecimal getMaxRedraw();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("maxRedraw")
  public void setMaxRedraw(BigDecimal maxRedraw);

  public default BankingLoanAccount maxRedraw(BigDecimal maxRedraw) {
    setMaxRedraw(maxRedraw);
    return this;
  }

  @BabelFishModelProperty(description = "Maximum redraw amount currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("maxRedrawCurrency")
  public Currency getMaxRedrawCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("maxRedrawCurrency")
  public void setMaxRedrawCurrency(Currency maxRedrawCurrency);

  public default BankingLoanAccount maxRedrawCurrency(Currency maxRedrawCurrency) {
    setMaxRedrawCurrency(maxRedrawCurrency);
    return this;
  }

  @BabelFishModelProperty(description = "Minimum redraw amount", dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("minRedraw")
  public BigDecimal getMinRedraw();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("minRedraw")
  public void setMinRedraw(BigDecimal minRedraw);

  public default BankingLoanAccount minRedraw(BigDecimal minRedraw) {
    setMinRedraw(minRedraw);
    return this;
  }

  @BabelFishModelProperty(description = "Minimum Redraw currency")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("minRedrawCurrency")
  public Currency getMinRedrawCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("minRedrawCurrency")
  public void setMinRedrawCurrency(Currency minRedrawCurrency);

  public default BankingLoanAccount minRedrawCurrency(Currency minRedrawCurrency) {
    setMinRedrawCurrency(minRedrawCurrency);
    return this;
  }

  @BabelFishModelProperty(
      description = "Set to true if one or more offset accounts are configured for this loan account")
  @JsonGetter("offsetAccountEnabled")
  public Boolean isOffsetAccountEnabled();

  @JsonSetter("offsetAccountEnabled")
  public void setIsOffsetAccountEnabled(Boolean isOffsetAccountEnabled);

  public default BankingLoanAccount offsetAccountEnabled(Boolean isOffsetAccountEnabled) {
    setIsOffsetAccountEnabled(isOffsetAccountEnabled);
    return this;
  }

  @BabelFishModelProperty(
      description = "The accountIDs of the configured offset accounts attached to this loan. Only offset accounts that can be accessed under the current authorisation should be included. It is expected behaviour that offsetAccountEnabled is set to true but the offsetAccountIds field is absent or empty. This represents a situation where an offset account exists but details can not be accessed under the current authorisation")
  @JsonGetter("offsetAccountIds")
  public List<String> getOffsetAccountIds();

  @JsonSetter("offsetAccountIds")
  public void setOffsetAccountIds(List<String> offsetAccountIds);

  public default BankingLoanAccount offsetAccountIds(List<String> offsetAccountIds) {
    setOffsetAccountIds(offsetAccountIds);
    return this;
  }

  @BabelFishModelProperty(
      description = "Options in place for repayments. If absent defaults to PRINCIPAL_AND_INTEREST")
  @JsonGetter("repaymentType")
  public BankingLoanRepaymentType getRepaymentType();

  @JsonSetter("repaymentType")
  public void setRepaymentType(BankingLoanRepaymentType repaymentType);

  public default BankingLoanAccount repaymentType(BankingLoanRepaymentType repaymentType) {
    setRepaymentType(repaymentType);
    return this;
  }

  @BabelFishModelProperty(
      description = "The expected or required repayment frequency. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @JsonGetter("repaymentFrequency")
  @JsonSerialize(converter = DurationToStringConverter.class)
  public Duration getRepaymentFrequency();

  @JsonSetter("repaymentFrequency")
  @JsonDeserialize(converter = StringToDurationConverter.class)
  public void setRepaymentFrequency(@NotNull Duration repaymentFrequency);

  public default BankingLoanAccount repaymentFrequency(@NotNull Duration repaymentFrequency) {
    setRepaymentFrequency(repaymentFrequency);
    return this;
  }


}
