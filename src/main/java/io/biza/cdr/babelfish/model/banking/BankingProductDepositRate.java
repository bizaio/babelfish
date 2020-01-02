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
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.enumerations.BankingProductDepositRateType;
import io.biza.cdr.babelfish.support.BabelFishModel;
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
@BabelFishModel(description = "A Description of a Deposit Rate")
public class BankingProductDepositRate {

    @BabelFishModelProperty(
            description = "The type of rate (base, bonus, etc). See the next section for an overview of valid values and their meaning",
            required = true)
    @NonNull
    @NotNull
    BankingProductDepositRateType depositRateType;

    @BabelFishModelProperty(description = "The rate to be applied", required = true, dataType = "java.lang.String")
    @NonNull
    @NotNull
    @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
    @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
    BigDecimal rate;

    @BabelFishModelProperty(
            description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
            dataType = "java.lang.String")
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period calculationFrequency;

    @BabelFishModelProperty(
            description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
            dataType = "java.lang.String")
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period applicationFrequency;

    @BabelFishModelProperty(description = "Rate tiers applicable for this rate")
    List<BankingProductRateTier> tiers;

    @BabelFishModelProperty(
            description = "Generic field containing additional information relevant to the [depositRateType](#tocSproductdepositratetypedoc) specified. Whether mandatory or not is dependent on the value of [depositRateType](#tocSproductdepositratetypedoc)")
    String additionalValue;

    @AssertTrue(message = "Additional Value must be a Duration String when Fee type is FIXED or INTRODUCTORY")
    private boolean isValueDuration() {
        return Arrays.asList(new BankingProductDepositRateType[] { BankingProductDepositRateType.FIXED, BankingProductDepositRateType.INTRODUCTORY}).contains(depositRateType)
                ? FormatChecker.isDuration(additionalValue)
                : true;
    }

    @BabelFishModelProperty(description = "Display text providing more information on the rate")
    String additionalInfo;

    @BabelFishModelProperty(description = "Link to a web page with more information on this rate", dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;
}
