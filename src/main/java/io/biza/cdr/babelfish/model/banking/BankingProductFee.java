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
import java.net.URI;
import java.time.Period;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Banking Product Fee Definition")
public abstract class BankingProductFee {

    @BabelFishModelProperty(description = "Name of the fee", required = true)
    @NonNull
    @NotNull
    String name;

    @BabelFishModelProperty(description = "The type of fee", required = true)
    @NonNull
    @NotNull
    BankingProductFeeType feeType;

    @BabelFishModelProperty(
            description = "The amount charged for the fee. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
            dataType = "java.lang.String")
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    private BigDecimal amount;

    @BabelFishModelProperty(
            description = "A fee rate calculated based on a proportion of the balance. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
            dataType = "java.lang.String")
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)

    private BigDecimal balanceRate;

    @BabelFishModelProperty(
            description = "A fee rate calculated based on a proportion of a transaction. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
            dataType = "java.lang.String")
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    private BigDecimal transactionRate;

    @BabelFishModelProperty(
            description = "A fee rate calculated based on a proportion of the calculated interest accrued on the account. One of amount, balanceRate, transactionRate and accruedRate is mandatory",
            dataType = "java.lang.String")
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    private BigDecimal accruedRate;

    @BabelFishModelProperty(
            description = "The indicative frequency with which the fee is calculated on the account. Only applies if balanceRate or accruedRate is also present. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
            dataType = "java.lang.String")
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period accrualFrequency;

    @BabelFishModelProperty(description = "The currency the fee will be charged in")
    @JsonSerialize(converter = CurrencyToStringConverter.class)
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    Currency currency = Currency.getInstance("AUD");

    @BabelFishModelProperty(
        description =  "Generic field containing additional information relevant to the [feeType](#tocSproductfeetypedoc) specified. Whether mandatory or not is dependent on the value of [feeType](#tocSproductfeetypedoc)"
    )
    String additionalValue;

    @BabelFishModelProperty(description = "Display text providing more information on the fee")
    String additionalInfo;

    @BabelFishModelProperty(description = "Link to a web page with more information on this fee", dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;

    @BabelFishModelProperty(description = "An optional list of discounts to this fee that may be available")
    List<BankingProductFeeDiscount> discounts;

    @AssertTrue(message = "Additional Value must be a Duration String when Fee type is PERIODIC")
    private boolean isValueDuration() {
    	return Arrays.asList(new BankingProductFeeType[] { BankingProductFeeType.PERIODIC}).contains(feeType) ? FormatChecker.isDuration(additionalValue) : true;
    }

    @AssertTrue(message = "One of amount, balanceRate, transactionRate or accruedRate is mandatory")
    private boolean isAmountOrRateSet() {
        return amount != null || balanceRate != null || transactionRate != null || accruedRate != null;
    }

}
