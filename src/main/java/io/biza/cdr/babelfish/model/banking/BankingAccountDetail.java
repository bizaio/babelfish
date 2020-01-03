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
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingAccount;
import io.biza.cdr.babelfish.v1.model.common.CommonPhysicalAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description = "Detailed Australian Banking Account Information")
public interface BankingAccountDetail extends BankingAccount {

  @BabelFishModelProperty(
      description = "The unmasked BSB for the account. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonGetter("bsb")
  public String getBsb();

  @JsonSetter("bsb")
  public void setBsb(String bsb);

  public default BankingAccountDetail bsb(String bsb) {
    setBsb(bsb);
    return this;
  }

  @BabelFishModelProperty(
      description = "The unmasked account number for the account. Should not be supplied if the account number is a PAN requiring PCI compliance. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonGetter("accountNumber")
  public String getAccountNumber();

  @JsonSetter("accountNumber")
  public void setAccountNumber(String accountNumber);

  public default BankingAccountDetail accountNumber(String accountNumber) {
    setAccountNumber(accountNumber);
    return this;
  }

  @BabelFishModelProperty(
      description = "Optional field to indicate if this account is part of a bundle that is providing additional benefit for to the customer")
  @JsonGetter("bundleName")
  public String getBundleName();

  @JsonSetter("bundleName")
  public void setBundleName(String bundleName);

  public default BankingAccountDetail bundleName(String bundleName) {
    setBundleName(bundleName);
    return this;
  }

  @BabelFishModelProperty(description = "The type of structure to present account specific fields.")
  @JsonGetter("specificAccountUType")
  public PayloadTypeBankingAccount getAccountType();

  @JsonSetter("specificAccountUType")
  public void setAccountType(PayloadTypeBankingAccount accountType);

  public default BankingAccountDetail accountType(PayloadTypeBankingAccount accountType) {
    setAccountType(accountType);
    return this;
  }

  @BabelFishModelProperty(description = "Term Deposit Account Information")
  @JsonGetter("termDeposit")
  public BankingTermDepositAccount getTermDeposit();

  @JsonSetter("termDeposit")
  public void setTermDeposit(BankingTermDepositAccount termDeposit);

  public default BankingAccountDetail termDeposit(BankingTermDepositAccount termDeposit) {
    setTermDeposit(termDeposit);
    return this;
  }

  @BabelFishModelProperty(description = "Credit Card Account Information")
  @JsonGetter("creditCard")
  public BankingCreditCardAccount getCreditCard();

  @JsonSetter("creditCard")
  public void setCreditCard(BankingCreditCardAccount creditCard);

  public default BankingAccountDetail creditCard(BankingCreditCardAccount creditCard) {
    setCreditCard(creditCard);
    return this;
  }

  @BabelFishModelProperty(description = "Loan Account Information")
  @JsonGetter("loan")
  public BankingLoanAccount getLoan();

  @JsonSetter("loan")
  public void setLoan(BankingLoanAccount loan);

  public default BankingAccountDetail loan(BankingLoanAccount loan) {
    setLoan(loan);
    return this;
  }

  @BabelFishModelProperty(
      description = "current rate to calculate interest earned being applied to deposit balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("depositRate")
  public BigDecimal getDepositRate();

  @JsonSetter("depositRate")
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  public void setDepositRate(BigDecimal depositRate);

  public default BankingAccountDetail depositRate(BigDecimal depositRate) {
    setDepositRate(depositRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "The current rate to calculate interest payable being applied to lending balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("lendingRate")
  public BigDecimal getLendingRate();

  @JsonSetter("lendingRate")
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  public void setLendingRate(BigDecimal lendingRate);

  public default BankingAccountDetail lendingRate(BigDecimal lendingRate) {
    setLendingRate(lendingRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonGetter("depositRates")
  public List<BankingProductDepositRate> getDepositRates();

  @JsonSetter("depositRates")
  public void setDepositRates(List<BankingProductDepositRate> depositRates);

  public default BankingAccountDetail depositRates(List<BankingProductDepositRate> depositRates) {
    setDepositRates(depositRates);
    return this;
  }

  @BabelFishModelProperty(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonGetter("lendingRates")
  public List<BankingProductDepositRate> getLendingRates();

  @JsonSetter("lendingRates")
  public void setLendingRates(List<BankingProductLendingRate> lendingRates);

  public default BankingAccountDetail lendingRates(List<BankingProductLendingRate> lendingRates) {
    setLendingRates(lendingRates);
    return this;
  }

  @BabelFishModelProperty(
      description = "Array of features of the account based on the equivalent structure in Product Reference with Activation fields")
  @JsonGetter("features")
  public List<BankingProductFeatureWithActivated> getFeatures();

  @JsonSetter("features")
  public void setFeatures(List<BankingProductFeatureWithActivated> features);

  public default BankingAccountDetail features(List<BankingProductFeatureWithActivated> features) {
    setFeatures(features);
    return this;
  }

  @BabelFishModelProperty(
      description = "Fees and charges applicable to the account based on the equivalent structure in Product Reference")
  @JsonGetter("fees")
  public List<BankingProductFee> getFees();

  @JsonSetter("fees")
  public void setFees(List<BankingProductFee> fees);

  public default BankingAccountDetail fees(List<BankingProductFee> fees) {
    setFees(fees);
    return this;
  }

  @BabelFishModelProperty(
      description = "The addresses for the account to be used for correspondence")
  @JsonGetter("addresses")
  public List<CommonPhysicalAddress> getAddresses();

  @JsonSetter("addresses")
  public void setAddresses(List<CommonPhysicalAddress> addresses);

  public default BankingAccountDetail addresses(List<CommonPhysicalAddress> addresses) {
    setAddresses(addresses);
    return this;
  }
}
