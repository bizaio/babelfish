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
package io.biza.babelfish.cdr.models.payloads.banking.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.enumerations.BankingAccountStatus;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingAccount;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeatureWithActivatedV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductLendingRateV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressV1;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detailed Australian Banking Account Information")
public class BankingAccountDetailV1 {
  
  @Schema(description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @NotNull
  @JsonProperty("accountId")
  String accountId;

  @Schema(description = "Date that the account was created (if known)", type = "string",
      format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("creationDate")
  LocalDate creationDate;

  @Schema(
      description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
      required = true)
  @NotNull
  @JsonProperty("displayName")
  String displayName;

  @Schema(description = "A customer supplied nick name for the account")
  @JsonProperty("nickname")
  String nickname;

  @Schema(
      description = "Open or closed status for the account. If not present then OPEN is assumed")
  @JsonProperty("openStatus")
  @Builder.Default
  BankingAccountStatus openStatus = BankingAccountStatus.OPEN;

  @Schema(
      description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
  @JsonProperty("isOwned")
  @Builder.Default
  Boolean isOwned = true;

  @Schema(
      description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
      required = true)
  @NotNull
  @JsonProperty("maskedNumber")
  String maskedNumber;

  @Schema(description = "The category to which a product or account belongs.", required = true)
  @NotNull
  @JsonProperty("productCategory")
  BankingProductCategory productCategory;

  @Schema(
      description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
      required = true)
  @NotNull
  @JsonProperty("productName")
  String productName;
  
  @Schema(
      description = "The unmasked BSB for the account. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonProperty("bsb")
  String bsb;

  @Schema(
      description = "The unmasked account number for the account. Should not be supplied if the account number is a PAN requiring PCI compliance. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces")
  @JsonProperty("accountNumber")
  String accountNumber;

  @Schema(
      description = "Optional field to indicate if this account is part of a bundle that is providing additional benefit for to the customer")
  @JsonProperty("bundleName")
  String bundleName;

  @Schema(description = "The type of structure to present account specific fields.")
  @JsonProperty("specificAccountUType")
  PayloadTypeBankingAccount specificAccountUType;

  @Schema(description = "Term Deposit Account Information")
  @JsonProperty("termDeposit")
  @Valid
  BankingTermDepositAccountV1 termDeposit;

  @Schema(description = "Credit Card Account Information")
  @JsonProperty("creditCard")
  @Valid
  BankingCreditCardAccountV1 creditCard;

  @Schema(description = "Loan Account Information")
  @JsonProperty("loan")
  @Valid
  BankingLoanAccountV1 loan;

  @Schema(
      description = "current rate to calculate interest earned being applied to deposit balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("depositRate")
  @Valid
  BigDecimal depositRate;

  @Schema(
      description = "The current rate to calculate interest payable being applied to lending balances as it stands at the time of the API call")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("lendingRate")
  @Valid
  BigDecimal lendingRate;

  @Schema(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonProperty("depositRates")
  @Valid
  List<BankingProductDepositRateV1> depositRates;

  @Schema(
      description = "Fully described deposit rates for this account based on the equivalent structure in Product Reference")
  @JsonProperty("lendingRates")
  @Valid
  List<BankingProductLendingRateV1> lendingRates;

  @Schema(
      description = "Array of features of the account based on the equivalent structure in Product Reference with the following additional field")
  @JsonProperty("features")
  @Valid
  List<BankingProductFeatureWithActivatedV1> features;

  @Schema(
      description = "Fees and charges applicable to the account based on the equivalent structure in Product Reference")
  @JsonProperty("fees")
  @Valid
  List<BankingProductFeeV1> fees;

  @Schema(description = "The addresses for the account to be used for correspondence")
  @JsonProperty("addresses")
  @Valid
  List<CommonPhysicalAddressV1> addresses;

  @AssertTrue(message = "Account Number must not be an unmasked PAN")
  private boolean isAccountNumberUnmaskedPan() {
    return FormatChecker.isPanNumber(accountNumber()) ? false : true;
  }

  @AssertTrue(message = "Account Type must supply matching Account Type Specific Information")
  private boolean isAccountTypeCorrect() {
    // Return true if not defined, @NotNull will pick this up later
    if (specificAccountUType() == null) {
      return true;
    }

    if (specificAccountUType().equals(PayloadTypeBankingAccount.TERM_DEPOSIT)) {
      return termDeposit() != null && creditCard() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.CREDIT_CARD)) {
      return creditCard() != null && termDeposit() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.LOAN)) {
      return loan() != null && creditCard() == null && termDeposit() == null ? true : false;
    }
    return false;
  }
}
