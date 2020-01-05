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
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(modelName = "BankingProductDiscount",
    description = "Banking Product Discount Specification")
public abstract class BankingProductFeeDiscount<T extends BankingProductFeeDiscount<T>> {
  @BabelFishModelProperty(description = "Description of the discount", required = true)
  String description;

  public String description() {
    return getDescription();
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    setDescription(description);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The type of discount. See the next section for an overview of valid values and their meaning",
      required = true)
  @NonNull
  @NotNull
  BankingProductDiscountType discountType;

  public BankingProductDiscountType discountType() {
    return getDiscountType();
  }

  @SuppressWarnings("unchecked")
  public T discountType(BankingProductDiscountType discountType) {
    setDiscountType(discountType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Value of the discount. When following properties include one of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
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
      description = "A discount rate calculated based on a proportion of the balance. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
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
      description = "A discount rate calculated based on a proportion of a transaction. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
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
      description = "A discount rate calculated based on a proportion of the calculated interest accrued on the account. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
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
      description = "A discount rate calculated based on a proportion of the fee to which this discount is attached. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  private BigDecimal feeRate;

  public BigDecimal feeRate() {
    return getFeeRate();
  }

  @SuppressWarnings("unchecked")
  public T feeRate(BigDecimal feeRate) {
    setFeeRate(feeRate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Discount type is BALANCE, DEPOSITS or PAYMENTS")
  private boolean isValueAmount() {
    return Arrays
        .asList(new BankingProductDiscountType[] {BankingProductDiscountType.BALANCE,
            BankingProductDiscountType.DEPOSITS, BankingProductDiscountType.PAYMENTS})
        .contains(discountType) ? FormatChecker.isDecimal(additionalValue) : true;
  }

  @AssertTrue(message = "Additional Value must be an Duration String when Discount type is FEE_CAP")
  private boolean isValueDuration() {
    return Arrays.asList(new BankingProductDiscountType[] {BankingProductDiscountType.FEE_CAP})
        .contains(discountType) ? FormatChecker.isDuration(additionalValue) : true;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the discount")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Link to a web page with more information on this discount",
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

  @BabelFishModelProperty(description = "Eligibility constraints that apply to this discount")
  List<BankingProductFeeDiscountEligibility<?>> eligibility;
}
