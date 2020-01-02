package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Purpose for the Address", modelName = "PhysicalAddressPurpose" )
public enum AddressPurpose implements LabelValueEnumInterface {
  // @formatter:off
  REGISTERED("REGISTERED", "Registered Mail Address"),
  MAIL("MAIL", "Mailing Address"),
  PHYSICAL("PHYSICAL", "Physical Address"),
  WORK("WORK", "Work Address"),
  OTHER("OTHER", "Other Physical Address");
  // @formatter:on
  
  private String value;
  private String label;

  AddressPurpose(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPurpose fromValue(String text) {
    for (AddressPurpose b : AddressPurpose.values()) {
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
