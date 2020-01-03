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
import java.util.Currency;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description =  "A Representation of a Banking Account Balance")
public interface BankingBalance {

    @BabelFishModelProperty(
        description =  "A unique ID of the account adhering to the standards for ID permanence",
        required = true
    )
    @JsonGetter("accountId")
    public String getAccountId();
    @JsonSetter("accountId")
    public void setAccountId(@NotNull String accountId);
    public default BankingBalance accountId(@NotNull String accountId) {
      setAccountId(accountId);
      return this;
    }

    @BabelFishModelProperty(
        description =  "The balance of the account at this time. Should align to the balance available via other channels such as Internet Banking. Assumed to be negative if the customer has money owing",
        required = true,
        dataType = "java.lang.String"
    )
    @JsonGetter("currentBalance")
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    public BigDecimal getCurrentBalance();
    
    @JsonSetter("currentBalance")
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    public void setCurrentBalance(@NotNull BigDecimal currentBalance);
    
    public default BankingBalance currentBalance(@NotNull BigDecimal currentBalance) {
      setCurrentBalance(currentBalance);
      return this;
    }
    
    @BabelFishModelProperty(
        description =  "Balance representing the amount of funds available for transfer. Assumed to be zero or positive",
        required = true,
        dataType = "java.lang.String"
    )
    @JsonGetter("availableBalance")
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    public BigDecimal getAvailableBalance();
    
    @JsonSetter("availableBalance")
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    public BigDecimal setAvailableBalance(@NotNull BigDecimal availableBalance);
    
    public default BankingBalance availableBalance(@NotNull BigDecimal availableBalance) {
      setAvailableBalance(availableBalance);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Object representing the maximum amount of credit that is available for this account. Assumed to be zero if absent",
        dataType = "java.lang.String"
    )
    @JsonGetter("creditLimit")
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    public BigDecimal getCreditLimit();
    
    @JsonSetter("creditLimit")
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    public void setCreditLimit(BigDecimal creditLimit);
    
    public default BankingBalance creditLimit(@NotNull BigDecimal creditLimit) {
      setCreditLimit(creditLimit);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Object representing the available limit amortised according to payment schedule. Assumed to be zero if absent",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonGetter("amortisedLimit")
    public BigDecimal getAmortisedLimit();
    
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    @JsonSetter("amortisedLimit")
    public void setAmortisedLimit(BigDecimal amortisedLimit);
    
    public default BankingBalance amortisedLimit(BigDecimal amortisedLimit) {
      setAmortisedLimit(amortisedLimit);
      return this;
    }

    @BabelFishModelProperty(
        description =  "The currency for the balance amounts. If absent assumed to be AUD",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonGetter("currency")
    public Currency getCurrency();
    
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    @JsonSetter("currency")
    public void setCurrency(Currency currency);
    
    public default BankingBalance currency(Currency currency) {
      setCurrency(currency);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Optional array of balances for the account in other currencies. Included to support accounts that support multi-currency purses such as Travel Cards"
    )
    @JsonGetter("purses")
    public List<BankingBalancePurse> getPurses();
    @JsonSetter("purses")
    public void setPurses(List<BankingBalancePurse> purses);
    
    public default BankingBalance purses(List<BankingBalancePurse> purses) {
      setPurses(purses);
      return this;
    }
}
