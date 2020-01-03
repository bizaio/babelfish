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
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Product Fee Definition")
public interface BankingProductFee {

  @BabelFishModelProperty(description = "Name of the fee", required = true)
  @JsonGetter("name")
  public String getName();

  @JsonSetter("name")
  public void setName(@NotNull String name);

  public default BankingProductFee name(@NotNull String name) {
    setName(name);
    return this;
  }

  @BabelFishModelProperty(description = "The type of fee", required = true)
  @JsonGetter("feeType")
  public BankingProductFeeType getFeeType();

  @JsonSetter("feeType")
  public void setFeeType(BankingProductFeeType feeType);

  public default BankingProductFee feeType(@NotNull BankingProductFeeType feeType) {
    setFeeType(feeType);
    return this;
  }

  @BabelFishModelProperty(
      description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("amount")
  public void setAmount(BigDecimal amount);

  public default BankingProductFee amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("balanceRate")
  public BigDecimal getBalanceRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("balanceRate")
  public void setBalanceRate(BigDecimal balanceRate);

  public default BankingProductFee balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("transactionRate")
  public BigDecimal getTransactionRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("transactionRate")
  public void setTransactionRate(BigDecimal transactionRate);

  public default BankingProductFee transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("accruedRate")
  public BigDecimal getAccruedRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("accruedRate")
  public void setAccruedRate(BigDecimal accruedRate);

  public default BankingProductFee accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonGetter("accrualFrequency")
  public Period getAccrualFrequency();

  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonSetter("accrualFrequency")
  public void setAccrualFrequency(Period accrualFrequency);

  public default BankingProductFee accrualFrequency(Period accrualFrequency) {
    setAccrualFrequency(accrualFrequency);
    return this;
  }

  @BabelFishModelProperty(description = "The currency the fee will be charged in")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("currency")
  public Currency getCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("currency")
  public void setCurrency(Currency currency);

  public default BankingProductFee currency(Currency currency) {
    setCurrency(currency);
    return this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductFee additionalValue(@NotNull String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the fee")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductFee additionalInfo(@NotNull String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @BabelFishModelProperty(description = "Link to a web page with more information on this fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductFee additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  @BabelFishModelProperty(
      description = "An optional list of discounts to this fee that may be available")
  @JsonGetter("discounts")
  public List<BankingProductFeeDiscount> getDiscounts();

  @JsonSetter("discounts")
  public void setDiscounts(List<BankingProductFeeDiscount> discounts);

  public default BankingProductFee discounts(List<BankingProductFeeDiscount> discounts) {
    setDiscounts(discounts);
    return this;
  }

  public default void addDiscount(BankingProductFeeDiscount discount) {
    getDiscounts().add(discount);
  }
}
