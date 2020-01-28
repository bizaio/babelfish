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
package io.biza.babelfish.interfaces.cdr.banking.product;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Period;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.BigDecimalToRateStringConverter;
import io.biza.babelfish.converter.cdr.CurrencyToStringConverter;
import io.biza.babelfish.converter.cdr.PeriodToStringConverter;
import io.biza.babelfish.converter.cdr.RateStringToBigDecimalConverter;
import io.biza.babelfish.converter.cdr.StringToCurrencyConverter;
import io.biza.babelfish.converter.cdr.StringToPeriodConverter;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.biza.babelfish.enumerations.cdr.BankingProductFeeType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Fee Definition")
public interface BankingProductFeeV1 {
  @Schema(description = "Name of the fee", required = true)
  @NotNull
  @JsonGetter("name")
  public String getName();

  public default String name() {
    return getName();
  }
  
  @JsonSetter("name")
  public void setName(String name);
  
  public default BankingProductFeeV1 name(String name) {
    setName(name);
    return this;
  }

  @Schema(description = "The type of fee", required = true)
  @NotNull
  @JsonGetter("feeType")
  public BankingProductFeeType getType();

  public default BankingProductFeeType type() {
    return getType();
  }
  
  @JsonSetter("feeType")
  public void setType(BankingProductFeeType type);

  public default BankingProductFeeV1 type(BankingProductFeeType feeType) {
    setType(feeType);
    return this;
  }

  @Schema(
      description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  public default BigDecimal amount() {
    return getAmount();
  }

  @JsonSetter("amount")
  public void setAmount(BigDecimal amount);
  
  public default BankingProductFeeV1 amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("balanceRate")
  public BigDecimal getBalanceRate();

  public default BigDecimal balanceRate() {
    return getBalanceRate();
  }
  
  @JsonSetter("balanceRate")
  public void setBalanceRate(BigDecimal balanceRate);
  
  public default BankingProductFeeV1 balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("transactionRate")
  public BigDecimal getTransactionRate();

  public default BigDecimal transactionRate() {
    return getTransactionRate();
  }
  
  @JsonSetter("transactionRate")
  public void setTransactionRate(BigDecimal transactionRate);

  public default BankingProductFeeV1 transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return this;
  }

  @Schema(
      description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("accruedRate")
  public BigDecimal getAccruedRate();

  public default BigDecimal accruedRate() {
    return getAccruedRate();
  }

  @JsonSetter("accruedRate")
  public void setAccruedRate(BigDecimal accruedRate);
  
  public default BankingProductFeeV1 accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return this;
  }

  @Schema(
      description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonGetter("accrualFrequency")
  public Period getAccrualFrequency();

  public default Period accrualFrequency() {
    return getAccrualFrequency();
  }
  
  @JsonSetter("accrualFrequency")
  public void setAccrualFrequency(Period accrualFrequency);

  public default BankingProductFeeV1 accrualFrequency(Period accrualFrequency) {
    setAccrualFrequency(accrualFrequency);
    return this;
  }

  @Schema(description = "The currency the fee will be charged in", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonGetter("currency")
  public Currency getCurrency();

  public default Currency currency() {
    return getCurrency();
  }

  public void setCurrency(Currency currency);
  
  public default BankingProductFeeV1 currency(Currency currency) {
    setCurrency(currency);
    return this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  public default String additionalValue() {
    return getAdditionalValue();
  }

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductFeeV1 additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @Schema(description = "Display text providing more information on the fee")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductFeeV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @Schema(description = "Link to a web page with more information on this fee", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductFeeV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  @Schema(description = "An optional list of discounts to this fee that may be available")
  @Valid
  @JsonGetter("discounts")
  public List<BankingProductDiscountV1> getDiscounts();

  public default List<BankingProductDiscountV1> discounts() {
    return getDiscounts();
  }

  @JsonSetter("discounts")
  public void setDiscounts(List<BankingProductDiscountV1> discounts);
  
  public default BankingProductFeeV1 discounts(List<BankingProductDiscountV1> discounts) {
    setDiscounts(discounts);
    return this;
  }


}
