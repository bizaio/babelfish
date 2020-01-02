package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Street Suffix for the Address")
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
