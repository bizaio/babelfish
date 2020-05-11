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

@Schema(description = "Common: Unit of Measure", enumAsRef = true)
public enum CommonUnitOfMeasureType implements LabelValueEnumInterface {
  // @formatter:off
  DOLLAR("DOLLAR", "Dollar Value"),
  PERCENT("PERCENT", "Percentage Value"), 
  MONTH("MONTH", "Month"), 
  DAY("DAY", "Day of Month");
  // @formatter:on

  private String value;

  private String label;

  CommonUnitOfMeasureType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonUnitOfMeasureType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CommonUnitOfMeasureType b : CommonUnitOfMeasureType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of CommonUnitOfMeasureType from " + text,
        CommonUnitOfMeasureType.class.getSimpleName(), CommonUnitOfMeasureType.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
