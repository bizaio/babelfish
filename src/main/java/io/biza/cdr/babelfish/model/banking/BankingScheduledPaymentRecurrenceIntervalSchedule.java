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

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingPaymentNonBusinessDayTreatment;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(
    description = "Indicates that the schedule of payments is defined by a series of intervals. Mandatory if recurrenceUType is set to intervalSchedule")
public abstract class BankingScheduledPaymentRecurrenceIntervalSchedule<T extends BankingScheduledPaymentRecurrenceIntervalSchedule<T>> {
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
      description = "Indicates the number of payments remaining in the schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value, If neither field is present the payments will continue indefinitely")
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
      description = "Enumerated field giving the treatment where a scheduled payment date is not a business day.  If absent assumed to be ON")
  BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment =
      BankingPaymentNonBusinessDayTreatment.ON;

  public BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment() {
    return getNonBusinessDayTreatment();
  }

  @SuppressWarnings("unchecked")
  public T nonBusinessDayTreatment(BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment) {
    setNonBusinessDayTreatment(nonBusinessDayTreatment);
    return (T) this;
  }


  @BabelFishModelProperty(
      description = "An array of interval objects defining the payment schedule.  Each entry in the array is additive, in that it adds payments to the overall payment schedule.  If multiple intervals result in a payment on the same day then only one payment will be made. Must have at least one entry",
      required = true)
  @NonNull
  @NotNull
  List<BankingScheduledPaymentInterval<?>> intervals;
}
