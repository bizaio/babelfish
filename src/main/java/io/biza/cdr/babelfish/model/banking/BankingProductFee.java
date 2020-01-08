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
import java.net.URI;
import java.time.Period;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
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

@BabelFishModel(description = "Banking Product Fee Definition")
public abstract class BankingProductFee<T> {
  @BabelFishModelProperty(description = "Name of the fee", required = true)
  @NonNull
  @NotNull
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The type of fee", required = true)
  @NonNull
  @NotNull
  BankingProductFeeType feeType;

  public BankingProductFeeType feeType() {
    return getFeeType();
  }

  @SuppressWarnings("unchecked")
  public T feeType(BankingProductFeeType feeType) {
    setFeeType(feeType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  private BigDecimal amount;

  public BigDecimal amount() {
    return getAmount();
  }

  @SuppressWarnings("unchecked")
  public T amount(BigDecimal amount) {
    setAmount(amount);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  private BigDecimal balanceRate;

  public BigDecimal balanceRate() {
    return getBalanceRate();
  }

  @SuppressWarnings("unchecked")
  public T balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  private BigDecimal transactionRate;

  public BigDecimal transactionRate() {
    return getTransactionRate();
  }

  @SuppressWarnings("unchecked")
  public T transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  private BigDecimal accruedRate;

  public BigDecimal accruedRate() {
    return getAccruedRate();
  }

  @SuppressWarnings("unchecked")
  public T accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  Period accrualFrequency;

  public Period accrualFrequency() {
    return getAccrualFrequency();
  }

  @SuppressWarnings("unchecked")
  public T accrualFrequency(Period accrualFrequency) {
    setAccrualFrequency(accrualFrequency);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The currency the fee will be charged in")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency currency = Currency.getInstance("AUD");

  public Currency currency() {
    return getCurrency();
  }

  @SuppressWarnings("unchecked")
  public T currency(Currency currency) {
    setCurrency(currency);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the fee")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Link to a web page with more information on this fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI additionalInfoUri;

  public URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "An optional list of discounts to this fee that may be available")
  List<BankingProductFeeDiscount<?>> discounts;

}
