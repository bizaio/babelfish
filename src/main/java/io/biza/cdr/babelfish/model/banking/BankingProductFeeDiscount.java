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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(modelName = "BankingProductDiscount",
    description = "Banking Product Discount Specification")
public interface BankingProductFeeDiscount {

  @BabelFishModelProperty(description = "Description of the discount", required = true)
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(String description);

  public default BankingProductFeeDiscount description(String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(
      description = "The type of discount. See the next section for an overview of valid values and their meaning",
      required = true)
  @JsonGetter("discountType")
  public BankingProductDiscountType getType();

  @JsonSetter("discountType")
  public void setType(@NotNull BankingProductDiscountType type);

  public default BankingProductFeeDiscount type(@NotNull BankingProductDiscountType type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(
      description = "Value of the discount. When following properties include one of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("amount")
  public void setAmount(BigDecimal amount);

  public default BankingProductFeeDiscount amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @BabelFishModelProperty(
      description = "A discount rate calculated based on a proportion of the balance. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("balanceRate")
  public BigDecimal getBalanceRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("balanceRate")
  public void setBalanceRate(BigDecimal balanceRate);

  public default BankingProductFeeDiscount balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "A discount rate calculated based on a proportion of a transaction. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("transactionRate")
  public BigDecimal getTransactionRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("transactionRate")
  public void setTransactionRate(BigDecimal transactionRate);

  public default BankingProductFeeDiscount transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "A discount rate calculated based on a proportion of the calculated interest accrued on the account. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("accruedRate")
  public BigDecimal getAccruedRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("accruedRate")
  public void setAccruedRate(BigDecimal accruedRate);

  public default BankingProductFeeDiscount accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "A discount rate calculated based on a proportion of the fee to which this discount is attached. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("feeRate")
  public BigDecimal getFeeRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("feeRate")
  public void setFeeRate(BigDecimal feeRate);

  public default BankingProductFeeDiscount feeRate(BigDecimal feeRate) {
    setFeeRate(feeRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductFeeDiscount additionalValue(@NotNull String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the discount")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductFeeDiscount additionalInfo(@NotNull String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @BabelFishModelProperty(description = "Link to a web page with more information on this discount",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductFeeDiscount additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  @BabelFishModelProperty(description = "Eligibility constraints that apply to this discount")
  @JsonGetter("eligibility")
  public List<BankingProductFeeDiscountEligibility> getEligibility();

  @JsonSetter("eligibility")
  public void setEligibility(List<BankingProductFeeDiscountEligibility> eligibility);

  public default BankingProductFeeDiscount eligibility(
      List<BankingProductFeeDiscountEligibility> eligibility) {
    setEligibility(eligibility);
    return this;
  }

}
