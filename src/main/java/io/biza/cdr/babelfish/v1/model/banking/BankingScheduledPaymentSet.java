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
package io.biza.cdr.babelfish.v1.model.banking;

import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "The set of payment amounts and destination accounts for this payment accommodating multi-part payments. A single entry indicates a simple payment with one destination account. Must have at least one entry")
public class BankingScheduledPaymentSet {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingScheduledPaymentTo to;

    @BabelFishModelProperty(
        description =  "Flag indicating whether the amount of the payment is calculated based on the context of the event. For instance a payment to reduce the balance of a credit card to zero. If absent then false is assumed"
    )
    @Builder.Default
    Boolean isAmountCalculated = false;

    @BabelFishModelProperty(
        description =  "Flag indicating whether the amount of the payment is calculated based on the context of the event. For instance a payment to reduce the balance of a credit card to zero. If absent then false is assumed",
        dataType = "java.lang.String"
    
            )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    private BigDecimal amount;

    @BabelFishModelProperty(
        description =  "The currency for the payment.",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = CurrencyToStringConverter.class)
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    @Builder.Default
    Currency currency  = Currency.getInstance("AUD");
}
