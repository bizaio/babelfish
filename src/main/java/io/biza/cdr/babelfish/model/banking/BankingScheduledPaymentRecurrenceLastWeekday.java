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

import java.time.LocalDate;
import java.time.Period;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingPaymentNonBusinessDayTreatment;
import io.biza.cdr.babelfish.v1.enumerations.CommonWeekDay;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(
    description = "Indicates that the schedule of payments is defined according to the last occurrence of a specific weekday in an interval. Mandatory if recurrenceUType is set to lastWeekDay")
public abstract class BankingScheduledPaymentRecurrenceLastWeekday<T> {
  @BabelFishModelProperty(
      description = "The limit date after which no more payments should be made using this schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value. If neither field is present the payments will continue indefinitely",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate finalPaymentDate;

  public LocalDate finalPaymentDate() {
    return getFinalPaymentDate();
  }

  @SuppressWarnings("unchecked")
  public T finalPaymentDate(LocalDate finalPaymentDate) {
    setFinalPaymentDate(finalPaymentDate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Indicates the number of payments remaining in the schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value. If neither field is present the payments will continue indefinitely")
  @Min(1)
  Integer paymentsRemaining;

  public Integer paymentsRemaining() {
    return getPaymentsRemaining();
  }

  @SuppressWarnings("unchecked")
  public T paymentsRemaining(Integer paymentsRemaining) {
    setPaymentsRemaining(paymentsRemaining);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The interval for the payment. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) with components less than a day in length ignored. This duration defines the period between payments starting with nextPaymentDate",
      required = true, dataType = "java.lang.String")
  @NonNull
  @NotNull
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  Period interval;

  public Period interval() {
    return getInterval();
  }

  @SuppressWarnings("unchecked")
  public T interval(Period interval) {
    setInterval(interval);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The weekDay specified. The payment will occur on the last occurrence of this weekday in the interval.",
      required = true)
  @NonNull
  @NotNull
  CommonWeekDay lastWeekDay;

  public CommonWeekDay lastWeekDay() {
    return getLastWeekDay();
  }

  @SuppressWarnings("unchecked")
  public T lastWeekDay(CommonWeekDay lastWeekDay) {
    setLastWeekDay(lastWeekDay);
    return (T) this;
  }
  
  @BabelFishModelProperty(
      description = "Enumerated field giving the treatment where a scheduled payment date is not a business day. If absent assumed to be ON"
      )
  BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment = BankingPaymentNonBusinessDayTreatment.ON;

  public BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment() {
    return getNonBusinessDayTreatment();
  }

  @SuppressWarnings("unchecked")
  public T nonBusinessDayTreatment(BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment) {
    setNonBusinessDayTreatment(nonBusinessDayTreatment);
    return (T) this;
  }
  
}
