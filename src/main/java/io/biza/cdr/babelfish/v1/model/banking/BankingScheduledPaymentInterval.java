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

import java.time.Period;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
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
@BabelFishModel(description =  "Scheduled Payment Interval Description")
public class BankingScheduledPaymentInterval {

    @BabelFishModelProperty(
        description =  "An interval for the payment. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) with components less than a day in length ignored. This duration defines the period between payments starting with nextPaymentDate",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period interval;

    @BabelFishModelProperty(
        description =  "Uses an interval to define the ordinal day within the interval defined by the interval field on which the payment occurs. If the resulting duration is 0 days in length or larger than the number of days in the interval then the payment will occur on the last day of the interval. A duration of 1 day indicates the first day of the interval. If absent the assumed value is P1D. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) with components less than a day in length ignored. The first day of a week is considered to be Monday.",
        dataType = "java.lang.String"
    )
    @Builder.Default
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    // TODO: This is a day of week but uses a duration?
    Period dayInInterval = Period.ofDays(1);
}
