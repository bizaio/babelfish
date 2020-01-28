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
package io.biza.babelfish.cdr.model.banking.product;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductDiscountType;
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
@Schema(name = "BankingProductDiscount", description = "Banking Product Discount Specification")
@JsonDeserialize(as = io.biza.babelfish.cdr.v1.model.banking.BankingProductDiscount.class)
public abstract class BankingProductDiscount<T> {
  @Schema(description = "Description of the discount", required = true)
  @NotNull
  @JsonProperty("description")
  String description;

  public String description() {
    return getDescription();
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    setDescription(description);
    return (T) this;
  }

  @Schema(
      description = "The type of discount. See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonProperty("discountType")
  BankingProductDiscountType discountType;

  public BankingProductDiscountType discountType() {
    return getDiscountType();
  }

  @SuppressWarnings("unchecked")
  public T discountType(BankingProductDiscountType discountType) {
    setDiscountType(discountType);
    return (T) this;
  }

  @Schema(
      description = "Value of the discount. When following properties include one of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("amount")
  BigDecimal amount;

  public BigDecimal amount() {
    return getAmount();
  }

  @SuppressWarnings("unchecked")
  public T amount(BigDecimal amount) {
    setAmount(amount);
    return (T) this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the balance. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("balanceRate")
  BigDecimal balanceRate;

  public BigDecimal balanceRate() {
    return getBalanceRate();
  }

  @SuppressWarnings("unchecked")
  public T balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return (T) this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of a transaction. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("transactionRate")
  BigDecimal transactionRate;

  public BigDecimal transactionRate() {
    return getTransactionRate();
  }

  @SuppressWarnings("unchecked")
  public T transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return (T) this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the calculated interest accrued on the account. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("accruedRate")
  BigDecimal accruedRate;

  public BigDecimal accruedRate() {
    return getAccruedRate();
  }

  @SuppressWarnings("unchecked")
  public T accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return (T) this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the fee to which this discount is attached. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("feeRate")
  BigDecimal feeRate;

  public BigDecimal feeRate() {
    return getFeeRate();
  }

  @SuppressWarnings("unchecked")
  public T feeRate(BigDecimal feeRate) {
    setFeeRate(feeRate);
    return (T) this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
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

  @Schema(description = "Display text providing more information on the discount")
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

  @Schema(description = "Link to a web page with more information on this discount",
      type = "string", format = "uri")
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

  @Schema(description = "Eligibility constraints that apply to this discount")
  @JsonProperty("eligibility")
  @Valid
  List<BankingProductFeeDiscountEligibility<?>> eligibility;

  public List<BankingProductFeeDiscountEligibility<?>> eligibility() {
    return getEligibility();
  }

  @SuppressWarnings("unchecked")
  public T eligibility(List<BankingProductFeeDiscountEligibility<?>> eligibility) {
    setEligibility(eligibility);
    return (T) this;
  }
}
