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
package io.biza.babelfish.cdr.abstracts.payloads.banking.account;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductDepositRate;
import io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductFeatureWithActivated;
import io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductFee;
import io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductLendingRate;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingAccount;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Detailed Australian Banking Account Information")
public abstract class BankingAccountDetail<T> extends BankingAccount<T> {
  @Schema(
      description = "The unmasked BSB for the account. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonProperty("bsb")
  String bsb;

  public String bsb() {
    return getBsb();
  }

  @SuppressWarnings("unchecked")
  public T bsb(String bsb) {
    setBsb(bsb);
    return (T) this;
  }

  @Schema(
      description = "The unmasked account number for the account. Should not be supplied if the account number is a PAN requiring PCI compliance. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonProperty("accountNumber")
  String accountNumber;

  public String accountNumber() {
    return getAccountNumber();
  }

  @SuppressWarnings("unchecked")
  public T accountNumber(String accountNumber) {
    setAccountNumber(accountNumber);
    return (T) this;
  }

  @Schema(
      description = "Optional field to indicate if this account is part of a bundle that is providing additional benefit for to the customer")
  @JsonProperty("bundleName")
  String bundleName;

  public String bundleName() {
    return getBundleName();
  }

  @SuppressWarnings("unchecked")
  public T bundleName(String bundleName) {
    setBundleName(bundleName);
    return (T) this;
  }

  @Schema(description = "The type of structure to present account specific fields.")
  @JsonProperty("specificAccountUType")
  PayloadTypeBankingAccount specificAccountUType;

  public PayloadTypeBankingAccount specificAccountUType() {
    return getSpecificAccountUType();
  }

  @SuppressWarnings("unchecked")
  public T specificAccountUType(PayloadTypeBankingAccount specificAccountUType) {
    setSpecificAccountUType(specificAccountUType);
    return (T) this;
  }

  @Schema(description = "Term Deposit Account Information")
  @JsonProperty("termDeposit")
  @Valid
  BankingTermDepositAccount<?> termDeposit;

  public BankingTermDepositAccount<?> termDeposit() {
    return getTermDeposit();
  }

  @SuppressWarnings("unchecked")
  public T termDeposit(BankingTermDepositAccount<?> termDeposit) {
    setTermDeposit(termDeposit);
    return (T) this;
  }

  @Schema(description = "Credit Card Account Information")
  @JsonProperty("creditCard")
  @Valid
  BankingCreditCardAccount<?> creditCard;

  public BankingCreditCardAccount<?> creditCard() {
    return getCreditCard();
  }

  @SuppressWarnings("unchecked")
  public T creditCard(BankingCreditCardAccount<?> creditCard) {
    setCreditCard(creditCard);
    return (T) this;
  }

  @Schema(description = "Loan Account Information")
  @JsonProperty("loan")
  @Valid
  BankingLoanAccount<?> loan;

  public BankingLoanAccount<?> loan() {
    return getLoan();
  }

  @SuppressWarnings("unchecked")
  public T loan(BankingLoanAccount<?> loan) {
    setLoan(loan);
    return (T) this;
  }

  @Schema(
      description = "current rate to calculate interest earned being applied to deposit balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("depositRate")
  @Valid
  BigDecimal depositRate;

  public BigDecimal depositRate() {
    return getDepositRate();
  }

  @SuppressWarnings("unchecked")
  public T depositRate(BigDecimal depositRate) {
    setDepositRate(depositRate);
    return (T) this;
  }

  @Schema(
      description = "The current rate to calculate interest payable being applied to lending balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("lendingRate")
  @Valid
  BigDecimal lendingRate;

  public BigDecimal lendingRate() {
    return getLendingRate();
  }

  @SuppressWarnings("unchecked")
  public T lendingRate(BigDecimal lendingRate) {
    setLendingRate(lendingRate);
    return (T) this;
  }

  @Schema(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonProperty("depositRates")
  @Valid
  List<BankingProductDepositRate<?>> depositRates;

  @Schema(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonProperty("lendingRates")
  @Valid
  List<BankingProductLendingRate<?>> lendingRates;

  @Schema(
      description = "Array of features of the account based on the equivalent structure in Product Reference with the following additional field")
  @JsonProperty("features")
  @Valid
  List<BankingProductFeatureWithActivated<?>> features;

  @Schema(
      description = "Fees and charges applicable to the account based on the equivalent structure in Product Reference")
  @JsonProperty("fees")
  @Valid
  List<BankingProductFee<?>> fees;

  @Schema(description = "The addresses for the account to be used for correspondence")
  @JsonProperty("addresses")
  @Valid
  List<CommonPhysicalAddress> addresses;
}
