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
import java.net.URI;
import java.time.Period;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.PeriodToStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.converters.StringToPeriodConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductFeeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Schema(description = "Banking Product Fee Definition")
public abstract class BankingProductFee<T> {
  @Schema(description = "Name of the fee", required = true)
  @NotNull
  @JsonProperty("name")
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @Schema(description = "The type of fee", required = true)
  @NotNull
  @JsonProperty("feeType")
  BankingProductFeeType feeType;

  public BankingProductFeeType feeType() {
    return getFeeType();
  }

  @SuppressWarnings("unchecked")
  public T feeType(BankingProductFeeType feeType) {
    setFeeType(feeType);
    return (T) this;
  }

  @Schema(
      description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("amount")
  private BigDecimal amount;

  public BigDecimal amount() {
    return getAmount();
  }

  @SuppressWarnings("unchecked")
  public T amount(BigDecimal amount) {
    setAmount(amount);
    return (T) this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("balanceRate")
  private BigDecimal balanceRate;

  public BigDecimal balanceRate() {
    return getBalanceRate();
  }

  @SuppressWarnings("unchecked")
  public T balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return (T) this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("transactionRate")
  private BigDecimal transactionRate;

  public BigDecimal transactionRate() {
    return getTransactionRate();
  }

  @SuppressWarnings("unchecked")
  public T transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return (T) this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("accruedRate")
  private BigDecimal accruedRate;

  public BigDecimal accruedRate() {
    return getAccruedRate();
  }

  @SuppressWarnings("unchecked")
  public T accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return (T) this;
  }

  @Schema(
      description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("accrualFrequency")
  Period accrualFrequency;

  public Period accrualFrequency() {
    return getAccrualFrequency();
  }

  @SuppressWarnings("unchecked")
  public T accrualFrequency(Period accrualFrequency) {
    setAccrualFrequency(accrualFrequency);
    return (T) this;
  }

  @Schema(description = "The currency the fee will be charged in", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("currency")
  Currency currency = Currency.getInstance("AUD");

  public Currency currency() {
    return getCurrency();
  }

  @SuppressWarnings("unchecked")
  public T currency(Currency currency) {
    setCurrency(currency);
    return (T) this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @Schema(description = "Display text providing more information on the fee")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @Schema(description = "Link to a web page with more information on this fee", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;

  public URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return (T) this;
  }

  @Schema(description = "An optional list of discounts to this fee that may be available")
  @JsonProperty("discounts")
  @Valid
  List<BankingProductDiscount<?>> discounts;

  public List<BankingProductDiscount<?>> discounts() {
    return getDiscounts();
  }

  @SuppressWarnings("unchecked")
  public T discounts(List<BankingProductDiscount<?>> discounts) {
    setDiscounts(discounts);
    return (T) this;
  }


}
