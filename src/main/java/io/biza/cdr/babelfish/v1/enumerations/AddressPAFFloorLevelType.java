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
package io.biza.cdr.babelfish.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Floor or level type for the address")
public enum AddressPAFFloorLevelType implements LabelValueEnumInterface {
  // @formatter:off
  BASEMENT("B", "Basement"),
  LOWER_GROUND("LG", "Lower Ground Floor"),
  MEZZANINE("M", "Mezzanine"),
  FLOOR("FL", "Floor"),
  UPPER_GROUND("UG", "Upper Ground Floor"),
  GROUND("G", "Ground Floor"),
  LEVEL("L", "Level");
  // @formatter:on
  private String value;

  private String label;

  AddressPAFFloorLevelType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFFloorLevelType fromValue(String text) {
    for (AddressPAFFloorLevelType b : AddressPAFFloorLevelType.values()) {
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
