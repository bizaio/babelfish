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
package io.biza.babelfish.cdr.banking.product.interfaces;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductDiscountType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(name = "BankingProductDiscount", description = "Banking Product Discount Specification")
public interface BankingProductDiscountV1 {
  @Schema(description = "Description of the discount", required = true)
  @NotNull
  @JsonGetter("description")
  public String getDescription();

  public default String description() {
    return getDescription();
  }

  @JsonSetter("description")
  public void setDescription(String description);

  public default BankingProductDiscountV1 description(String description) {
    setDescription(description);
    return this;
  }

  @Schema(
      description = "The type of discount. See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonGetter("discountType")
  public BankingProductDiscountType getType();

  public default BankingProductDiscountType type() {
    return getType();
  }

  @JsonSetter("discountType")
  public void setType(BankingProductDiscountType type);

  public default BankingProductDiscountV1 type(BankingProductDiscountType discountType) {
    setType(discountType);
    return this;
  }

  @Schema(
      description = "Value of the discount. When following properties include one of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
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

  public default BankingProductDiscountV1 amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the balance. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
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

  public default BankingProductDiscountV1 balanceRate(BigDecimal balanceRate) {
    setBalanceRate(balanceRate);
    return this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of a transaction. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory",
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

  public default BankingProductDiscountV1 transactionRate(BigDecimal transactionRate) {
    setTransactionRate(transactionRate);
    return this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the calculated interest accrued on the account. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
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

  public default BankingProductDiscountV1 accruedRate(BigDecimal accruedRate) {
    setAccruedRate(accruedRate);
    return this;
  }

  @Schema(
      description = "A discount rate calculated based on a proportion of the fee to which this discount is attached. Note that the currency of the fee discount is expected to be the same as the currency of the fee itself. One of amount, balanceRate, transactionRate, accruedRate and feeRate is mandatory. Unless noted in additionalInfo, assumes the application and calculation frequency are the same as the corresponding fee",
      type = "string")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("feeRate")
  public BigDecimal getFeeRate();

  public default BigDecimal feeRate() {
    return getFeeRate();
  }

  @JsonSetter("feeRate")
  public void setFeeRate(BigDecimal feeRate);

  public default BankingProductDiscountV1 feeRate(BigDecimal feeRate) {
    setFeeRate(feeRate);
    return this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  public default String additionalValue() {
    return getAdditionalValue();
  }

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductDiscountV1 additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @Schema(description = "Display text providing more information on the discount")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductDiscountV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @Schema(description = "Link to a web page with more information on this discount",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductDiscountV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  @Schema(description = "Eligibility constraints that apply to this discount")
  @JsonGetter("eligibility")
  @Valid
  public List<BankingProductFeeDiscountEligibilityV1> getEligibility();

  public default List<BankingProductFeeDiscountEligibilityV1> eligibility() {
    return getEligibility();
  }

  @JsonSetter("eligibility")
  public void setEligibility(List<BankingProductFeeDiscountEligibilityV1> eligibility);

  public default BankingProductDiscountV1 eligibility(
      List<BankingProductFeeDiscountEligibilityV1> eligibility) {
    setEligibility(eligibility);
    return this;
  }
}
