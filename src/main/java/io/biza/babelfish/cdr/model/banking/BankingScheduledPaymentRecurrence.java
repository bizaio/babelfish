/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.LocalDateToStringConverter;
import io.biza.babelfish.converter.cdr.StringToLocalDateConverter;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingScheduledPaymentRecurrence;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "Describes the detail of the scheduled payment")
public abstract class BankingScheduledPaymentRecurrence<T> {
  @Schema(description = "The date of the next payment under the recurrence schedule",
      type = "string", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("nextPaymentDate")
  LocalDate nextPaymentDate;

  public LocalDate nextPaymentDate() {
    return getNextPaymentDate();
  }

  @SuppressWarnings("unchecked")
  public T nextPaymentDate(LocalDate nextPaymentDate) {
    setNextPaymentDate(nextPaymentDate);
    return (T) this;
  }

  @Schema(description = "The type of recurrence used to define the schedule", required = true)
  @NotNull
  @JsonProperty("type")
  PayloadTypeBankingScheduledPaymentRecurrence type;

  public PayloadTypeBankingScheduledPaymentRecurrence type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType) {
    setType(recurrenceUType);
    return (T) this;
  }

  @JsonProperty("onceOff")
  @Schema(description = "Scheduled Payment Once Off Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceOnceOff<?> onceOff;

  public BankingScheduledPaymentRecurrenceOnceOff<?> onceOff() {
    return getOnceOff();
  }

  @SuppressWarnings("unchecked")
  public T onceOff(BankingScheduledPaymentRecurrenceOnceOff<?> onceOff) {
    setOnceOff(onceOff);
    return (T) this;
  }

  @JsonProperty("intervalSchedule")
  @Schema(description = "Scheduled Payment Interval Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule;

  public BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule() {
    return getIntervalSchedule();
  }

  @SuppressWarnings("unchecked")
  public T intervalSchedule(BankingScheduledPaymentRecurrenceIntervalSchedule<?> intervalSchedule) {
    setIntervalSchedule(intervalSchedule);
    return (T) this;
  }

  @JsonProperty("lastWeekDay")
  @Schema(description = "Scheduled Payment Last Weekday Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay;

  public BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay() {
    return getLastWeekDay();
  }

  @SuppressWarnings("unchecked")
  public T lastWeekDay(BankingScheduledPaymentRecurrenceLastWeekday<?> lastWeekDay) {
    setLastWeekDay(lastWeekDay);
    return (T) this;
  }

  @JsonProperty("eventBased")
  @Schema(description = "Scheduled Payment Event Based Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceEventBased<?> eventBased;

  public BankingScheduledPaymentRecurrenceEventBased<?> eventBased() {
    return getEventBased();
  }

  @SuppressWarnings("unchecked")
  public T eventBased(BankingScheduledPaymentRecurrenceEventBased<?> eventBased) {
    setEventBased(eventBased);
    return (T) this;
  }

}
