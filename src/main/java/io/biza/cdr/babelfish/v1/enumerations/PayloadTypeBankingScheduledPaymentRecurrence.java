/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@Schema(description = "Payload Type: Banking Scheduled Payment Recurrence")
public enum PayloadTypeBankingScheduledPaymentRecurrence implements LabelValueEnumInterface {
  // @formatter:off
  ONCE_OFF("onceOff", "Indicates that the payment is a once off payment on a specific future date."),
  INTERVAL_SCHEDULE("intervalSchedule", "Indicates that the schedule of payments is defined by a series of intervals."),
  LAST_WEEKDAY("lastWeekDay", "Indicates that the schedule of payments is defined according to the last occurrence of a specific weekday in an interval."),
  EVENT_BASED("eventBased", "Indicates that the schedule of payments is defined according to an external event that cannot be predetermined.");
  // @formatter:on
  private String value;

  private String label;

  PayloadTypeBankingScheduledPaymentRecurrence(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingScheduledPaymentRecurrence fromValue(String text) {
    for (PayloadTypeBankingScheduledPaymentRecurrence b : PayloadTypeBankingScheduledPaymentRecurrence
        .values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
