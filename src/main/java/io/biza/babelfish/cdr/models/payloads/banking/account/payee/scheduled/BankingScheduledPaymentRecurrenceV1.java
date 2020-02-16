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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Describes the detail of the scheduled payment",
    name = "BankingScheduledPaymentRecurrenceV1")
public class BankingScheduledPaymentRecurrenceV1 extends io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceV1 {
  @Schema(description = "The date of the next payment under the recurrence schedule",
      type = "string", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("nextPaymentDate")
  LocalDate nextPaymentDate;

  @Schema(description = "The type of recurrence used to define the schedule", required = true)
  @NotNull
  @JsonProperty("recurrenceUType")
  PayloadTypeBankingScheduledPaymentRecurrence type;

  @JsonProperty("onceOff")
  @Schema(description = "Scheduled Payment Once Off Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceOnceOffV1 onceOff;

  @JsonProperty("intervalSchedule")
  @Schema(description = "Scheduled Payment Interval Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceIntervalScheduleV1 intervalSchedule;

  @JsonProperty("lastWeekDay")
  @Schema(description = "Scheduled Payment Last Weekday Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceLastWeekdayV1 lastWeekDay;

  @JsonProperty("eventBased")
  @Schema(description = "Scheduled Payment Event Based Recurrence Details")
  @Valid
  BankingScheduledPaymentRecurrenceEventBasedV1 eventBased;

}
