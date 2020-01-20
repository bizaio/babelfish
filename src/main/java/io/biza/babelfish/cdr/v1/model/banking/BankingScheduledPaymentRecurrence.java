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
package io.biza.babelfish.cdr.v1.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingScheduledPaymentRecurrence extends
    io.biza.babelfish.cdr.model.banking.BankingScheduledPaymentRecurrence<BankingScheduledPaymentRecurrence> {
  @AssertTrue(
      message = "One and Only One Recurrence Type Object must be populated to align with recurrencyUType")
  private boolean isRecurrenceTypeCorrect() {
    if (type() == null) {
      return true;
    }

    if (type().equals(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)) {
      return onceOff() != null && intervalSchedule() == null && lastWeekDay() == null
          && eventBased() == null ? true : false;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentRecurrence.INTERVAL_SCHEDULE)) {
      return intervalSchedule() != null && onceOff() == null && lastWeekDay() == null
          && eventBased() == null ? true : false;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentRecurrence.LAST_WEEKDAY)) {
      return lastWeekDay() != null && intervalSchedule() == null && intervalSchedule() == null
          && eventBased() == null ? true : false;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentRecurrence.EVENT_BASED)) {
      return eventBased() != null && onceOff() == null && intervalSchedule() == null
          && lastWeekDay() == null ? true : false;
    }
    return false;
  }
}
