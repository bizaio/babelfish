package io.biza.cdr.babelfish.enumerations;

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
