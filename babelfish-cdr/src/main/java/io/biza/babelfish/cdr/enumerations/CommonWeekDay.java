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
package io.biza.babelfish.cdr.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Day of Week", name = "CommonWeekDay", enumAsRef = true)
public enum CommonWeekDay implements LabelValueEnumInterface {
  // @formatter:off
  MON("MON", "Monday"),
  TUE("TUE", "Tuesday"),
  WED("WED", "Wednesday"),
  THU("THU", "Thursday"),
  FRI("FRI", "Friday"),
  SAT("SAT", "Saturday"),
  SUN("SUN", "Sunday");
  // @formatter:on

  private String value;

  private String label;

  CommonWeekDay(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonWeekDay fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CommonWeekDay b : CommonWeekDay.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of CommonWeekDay from " + text,
        CommonWeekDay.class.getSimpleName(), CommonWeekDay.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
