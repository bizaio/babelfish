/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Australian State for the Address")
public enum AddressPAFStateType implements LabelValueEnumInterface {
  // @formatter:off
  AAT("AAT", "Australian Antarctic Territory"),
  ACT("ACT", "Australian Capital Territory"),
  NSW("NSW", "New South Wales"),
  NT("NT", "Northern Territory"),
  QLD("QLD", "Queenslands"),
  SA("SA", "South Australia"),
  TAS("TAS", "Tasmania"),
  VIC("VIC", "Victoria"),
  WA("WA", "Western Australia");
  // @formatter:on

  private String value;
  private String label;

  AddressPAFStateType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFStateType fromValue(String text) {
    for (AddressPAFStateType b : AddressPAFStateType.values()) {
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
