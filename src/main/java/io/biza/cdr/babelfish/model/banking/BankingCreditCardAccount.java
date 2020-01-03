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
import java.time.LocalDate;
import java.util.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description =  "Credit Card Account Details")
public interface BankingCreditCardAccount {

    @BabelFishModelProperty(
        description =  "The minimum payment amount due for the next card payment",
        required = true,
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonGetter("minPaymentAmount")
    public BigDecimal getMinPaymentAmount();
    
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    @JsonSetter("minPaymentAmount")
    public void setMinPaymentAmount(@NotNull BigDecimal minPaymentAmount);
    
    public default BankingCreditCardAccount minPaymentAmount(@NotNull BigDecimal minPaymentAmount) {
      setMinPaymentAmount(minPaymentAmount);
      return this;
    }

    @BabelFishModelProperty(
        description =  "The amount due for the next card payment",
        required = true,
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonGetter("paymentDueAmount")
    public BigDecimal getPaymentDueAmount();
        
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    @JsonSetter("paymentDueAmount")
    public void setPaymentDueAmount(@NotNull BigDecimal paymentDueAmount);
    
    public default BankingCreditCardAccount paymentDueAmount(@NotNull BigDecimal paymentDueAmount) {
      setPaymentDueAmount(paymentDueAmount);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Payment Currency for Credit Card",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = CurrencyToStringConverter.class)
    @JsonGetter("paymentCurrency")
    public Currency getPaymentCurrency();
    
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    @JsonSetter("paymentCurrency")
    public void setPaymentCurrency(Currency paymentCurrency);
    
    public default BankingCreditCardAccount paymentCurrency(@NotNull Currency paymentCurrency) {
      setPaymentCurrency(paymentCurrency);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Date that the next payment for the card is due",
        required = true,
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonGetter("paymentDueDate")
    public LocalDate getPaymentDueDate();
    
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    @JsonSetter("paymentDueDate")
    public void setPaymentDueDate(LocalDate paymentDueDate);
    
    public default BankingCreditCardAccount paymentDueDate(LocalDate paymentDueDate) {
      setPaymentDueDate(paymentDueDate);
      return this;
    }
    
}
