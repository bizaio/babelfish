package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Common: Email Address Purpose")
public enum CommonEmailAddressPurpose implements LabelValueEnumInterface {
  // @formatter:off
  HOME("HOME", "Personal Email Address"),
  WORK("WORK", "Work Email Address"),
  OTHER("OTHER", "Other Email Address"),
  UNSPECIFIED("UNSPECIFIED", "Unspecified Email Address Purpose");
  // @formatter:on

  private String value;
  private String label;

  CommonEmailAddressPurpose(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonEmailAddressPurpose fromValue(String text) {
    for (CommonEmailAddressPurpose b : CommonEmailAddressPurpose.values()) {
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
