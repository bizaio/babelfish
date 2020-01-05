/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Describes the detail of the scheduled payment")
public abstract class BankingScheduledPaymentRecurrence<T extends BankingScheduledPaymentRecurrence<T>> {
  @BabelFishModelProperty(
      description = "The date of the next payment under the recurrence schedule",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate nextPaymentDate;

  public LocalDate nextPaymentDate() {
    return getNextPaymentDate();
  }

  @SuppressWarnings("unchecked")
  public T nextPaymentDate(LocalDate nextPaymentDate) {
    setNextPaymentDate(nextPaymentDate);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The type of recurrence used to define the schedule",
      required = true)
  @NonNull
  @NotNull
  private PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType;

  public PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType() {
    return getRecurrenceUType();
  }

  @SuppressWarnings("unchecked")
  public T recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType) {
    setRecurrenceUType(recurrenceUType);
    return (T) this;
  }

  private BankingScheduledPaymentRecurrenceOnceOff<?> onceOff;

  public BankingScheduledPaymentRecurrenceOnceOff<?> onceOff() {
    return getOnceOff();
  }

  @SuppressWarnings("unchecked")
  public T onceOff(BankingScheduledPaymentRecurrenceOnceOff<?> onceOff) {
    setOnceOff(onceOff);
    return (T) this;
  }

  private BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule;

  public BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule() {
    return getIntervalSchedule();
  }

  @SuppressWarnings("unchecked")
  public T intervalSchedule(BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule) {
    setIntervalSchedule(intervalSchedule);
    return (T) this;
  }

  private BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay;

  public BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay() {
    return getLastWeekDay();
  }

  @SuppressWarnings("unchecked")
  public T lastWeekDay(BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay) {
    setLastWeekDay(lastWeekDay);
    return (T) this;
  }

  private BankingScheduledPaymentRecurrenceEventBased<?> eventBased;

  public BankingScheduledPaymentRecurrenceEventBased<?> eventBased() {
    return getEventBased();
  }

  @SuppressWarnings("unchecked")
  public T eventBased(BankingScheduledPaymentRecurrenceEventBased<?> eventBased) {
    setEventBased(eventBased);
    return (T) this;
  }

}
