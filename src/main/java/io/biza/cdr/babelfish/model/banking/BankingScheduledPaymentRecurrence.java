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

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Describes the detail of the scheduled payment")
public interface BankingScheduledPaymentRecurrence {

  @BabelFishModelProperty(
      description = "The date of the next payment under the recurrence schedule",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("nextPaymentDate")
  public LocalDate getNextPaymentDate();

  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonSetter("nextPaymentDate")
  public void setNextPaymentDate(LocalDate nextPaymntDate);

  public default BankingScheduledPaymentRecurrence nextPaymentDate(LocalDate nextPaymentDate) {
    setNextPaymentDate(nextPaymentDate);
    return this;
  }

  @BabelFishModelProperty(description = "The type of recurrence used to define the schedule",
      required = true)
  @JsonGetter("recurrenceUType")
  public PayloadTypeBankingScheduledPaymentRecurrence getType();

  @JsonSetter("recurrenceUType")
  public void setType(@NotNull PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType);

  public default BankingScheduledPaymentRecurrence type(
      @NotNull PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType) {
    setType(recurrenceUType);
    return this;
  }


  @JsonGetter("onceOff")
  public BankingScheduledPaymentRecurrenceOnceOff getOnceOff();

  @JsonSetter("onceOff")
  public void setOnceOff(BankingScheduledPaymentRecurrenceOnceOff onceOff);

  public default BankingScheduledPaymentRecurrence onceOff(
      @NotNull BankingScheduledPaymentRecurrenceOnceOff onceOff) {
    setOnceOff(onceOff);
    return this;
  }

  @JsonGetter("intervalSchedule")
  public BankingScheduledPaymentRecurrenceIntervalSchedule getIntervalSchedule();

  @JsonSetter("intervalSchedule")
  public void setIntervalSchedule(
      BankingScheduledPaymentRecurrenceIntervalSchedule intervalSchedule);

  public default BankingScheduledPaymentRecurrence intervalSchedule(
      @NotNull BankingScheduledPaymentRecurrenceIntervalSchedule intervalSchedule) {
    setIntervalSchedule(intervalSchedule);
    return this;
  }

  @JsonGetter("lastWeekDay")
  public BankingScheduledPaymentRecurrenceLastWeekday getLastWeekDay();

  @JsonSetter("lastWeekDay")
  public void setLastWeekDay(BankingScheduledPaymentRecurrenceLastWeekday lastWeekDay);

  public default BankingScheduledPaymentRecurrence lastWeekDay(
      @NotNull BankingScheduledPaymentRecurrenceLastWeekday lastWeekDay) {
    setLastWeekDay(lastWeekDay);
    return this;
  }

  @JsonGetter("eventBased")
  public BankingScheduledPaymentRecurrenceEventBased getEventBased();

  @JsonSetter("eventBased")
  public void setEventBased(BankingScheduledPaymentRecurrenceEventBased eventBased);

  public default BankingScheduledPaymentRecurrence eventBased(
      @NotNull BankingScheduledPaymentRecurrenceEventBased eventBased) {
    setEventBased(eventBased);
    return this;
  }


}
