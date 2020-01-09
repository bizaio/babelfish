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

@Schema(description = "Street Suffix for the Address")
public enum AddressPAFStreetSuffix implements LabelValueEnumInterface {
  // @formatter:off
  NORTH_WEST("NW", "North West"),
  CENTRAL("CN", "Central"),
  SOUTH("S", "South"),
  EAST("E", "East"),
  SOUTH_EAST("SE", "South East"),
  EXTENSION("EX", "Extension"),
  SOUTH_WEST("SW", "South West"),
  LOWER("LR", "Lower"),
  NORTH("N", "North"),
  UPPER("UP", "Upper"),
  NORTH_EAST("NE", "North East"),
  WEST("W", "West");
  // @formatter:on
  private String value;

  private String label;

  AddressPAFStreetSuffix(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFStreetSuffix fromValue(String text) {
    for (AddressPAFStreetSuffix b : AddressPAFStreetSuffix.values()) {
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
