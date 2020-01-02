package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Type of flat or unit for the address")
public enum AddressPAFFlatUnitType implements LabelValueEnumInterface {
  // @formatter:off  
  KIOSK("KSK", "Kiosk"), 
  SUITE("SE", "Suite"), 
  FACTORY("FY", "Factory"), 
  MAISONETTE("MSNT", "Maisonette"), 
  TOWNHOUSE("TNHS", "Townhouse"),
  WAREHOUSE("WE", "Warehouse"), 
  DUPLEX("DUP", "Duplex"), 
  UNIT("U", "Unit"), 
  FLAT("F", "Flat"), 
  VILLA("VLLA", "Villa"),
  WARD("WARD", "Ward");
  // @formatter:on  

  private String value;
  private String label;

  AddressPAFFlatUnitType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFFlatUnitType fromValue(String text) {
    for (AddressPAFFlatUnitType b : AddressPAFFlatUnitType.values()) {
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
