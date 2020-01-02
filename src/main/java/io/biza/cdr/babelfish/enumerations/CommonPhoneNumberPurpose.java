package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Common: Phone Number Purpose")
public enum CommonPhoneNumberPurpose implements LabelValueEnumInterface {
  // @formatter:off
  MOBILE("MOBILE", "Mobile Number"),
  HOME("HOME", "Home Phone Number"),
  WORK("WORK", "Work Phone Number"),
  OTHER("OTHER", "Other Phone Number"),
  INTERNATIONAL("INTERNATIONAL", "International Phone Number"),
  UNSPECIFIED("UNSPECIFIED", "Unspecified Phone Format");
  // @formatter:on

  private String value;
  private String label;

  CommonPhoneNumberPurpose(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonPhoneNumberPurpose fromValue(String text) {
    for (CommonPhoneNumberPurpose b : CommonPhoneNumberPurpose.values()) {
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
