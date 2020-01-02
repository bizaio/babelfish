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
