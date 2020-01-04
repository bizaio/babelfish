/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingAccount;
import io.biza.cdr.babelfish.v1.model.common.CommonPhysicalAddress;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description =  "Detailed Australian Banking Account Information", parent = BankingAccount.class)
public abstract class BankingAccountDetail extends BankingAccount {

    @BabelFishModelProperty(
        description =  "The unmasked BSB for the account. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces"
    )
    String bsb;

    @BabelFishModelProperty(
        description =  "The unmasked account number for the account. Should not be supplied if the account number is a PAN requiring PCI compliance. Is expected to be formatted as digits only with leading zeros included and no punctuation or spaces"
    )
    String accountNumber;
    
    @AssertTrue(message = "Account Number must not be an unmasked PAN")
    private boolean isAccountNumberUnmaskedPan() {
    	return FormatChecker.isPanNumber(accountNumber) ? false : true;
    }

    @BabelFishModelProperty(
        description =  "Optional field to indicate if this account is part of a bundle that is providing additional benefit for to the customer"
    )
    String bundleName;

    @BabelFishModelProperty(
        description =  "The type of structure to present account specific fields."
    )
    PayloadTypeBankingAccount specificAccountUType;

    @BabelFishModelProperty(
            description = "Term Deposit Account Information"
    )
    BankingTermDepositAccount termDeposit;

    @BabelFishModelProperty(
            description = "Credit Card Account Information"
    )
    BankingCreditCardAccount creditCard;

    @BabelFishModelProperty(
            description = "Loan Account Information"
    )
    BankingLoanAccount loan;

    @BabelFishModelProperty(
        description =  "current rate to calculate interest earned being applied to deposit balances as it stands at the time of the API call"
    )
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    BigDecimal depositRate;

    @BabelFishModelProperty(
        description =  "The current rate to calculate interest payable being applied to lending balances as it stands at the time of the API call"
    )
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    BigDecimal lendingRate;

    @BabelFishModelProperty(
        description =  "Fully described deposit rates for this account based on the equivalent structure in Product Reference"
    )
    List<BankingProductDepositRate> depositRates;

    @BabelFishModelProperty(
        description =  "Fully described deposit rates for this account based on the equivalent structure in Product Reference"
    )
    List<BankingProductLendingRate> lendingRates;

    @BabelFishModelProperty(
        description =  "Array of features of the account based on the equivalent structure in Product Reference with the following additional field"
    )
    List<BankingProductFeatureWithActivated> features;

    @BabelFishModelProperty(
        description =  "Fees and charges applicable to the account based on the equivalent structure in Product Reference"
    )
    List<BankingProductFee> fees;

    @BabelFishModelProperty(
        description =  "The addresses for the account to be used for correspondence"
    )
    List<CommonPhysicalAddress> addresses;
    
    @AssertTrue(message = "Account Type must supply matching Account Type Specific Information")
    private boolean isAccountTypeCorrect() {
        if(specificAccountUType.equals(PayloadTypeBankingAccount.TERM_DEPOSIT)) {
            return termDeposit != null && creditCard == null && loan == null ? true : false;
        } else  if(specificAccountUType.equals(PayloadTypeBankingAccount.CREDIT_CARD)) {
            return creditCard != null && termDeposit == null && loan == null ? true : false;
        } else  if(specificAccountUType.equals(PayloadTypeBankingAccount.LOAN)) {
            return loan != null && creditCard == null && termDeposit == null ? true : false;
        }
        return false;
    }
}
