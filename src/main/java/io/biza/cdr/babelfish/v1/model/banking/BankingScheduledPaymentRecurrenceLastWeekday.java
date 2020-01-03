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

import java.time.LocalDate;
import java.time.Period;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
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
@BabelFishModel(description =  "Indicates that the schedule of payments is defined according to the last occurrence of a specific weekday in an interval. Mandatory if recurrenceUType is set to lastWeekDay")
public class BankingScheduledPaymentRecurrenceLastWeekday {

    @BabelFishModelProperty(
        description =  "The limit date after which no more payments should be made using this schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value. If neither field is present the payments will continue indefinitely",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate finalPaymentDate;

    @BabelFishModelProperty(
        description =  "Indicates the number of payments remaining in the schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value. If neither field is present the payments will continue indefinitely"
    )
    @Min(1)
    Integer paymentsRemaining;

    @BabelFishModelProperty(
        description =  "The interval for the payment. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) with components less than a day in length ignored. This duration defines the period between payments starting with nextPaymentDate",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period interval;

    @BabelFishModelProperty(
        description =  "The weekDay specified. The payment will occur on the last occurrence of this weekday in the interval. Value is constrained to 1 to 7 with 1 indicating Monday.",
        required = true
    )
    @NonNull
    @NotNull
    @Min(1)
    Integer lastWeekDay;
}
