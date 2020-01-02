package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Common: Organisation Type")
public enum CommonOrganisationType implements LabelValueEnumInterface {
  // @formatter:off
  SOLE_TRADER("SOLE_TRADER", "Sole Trader"),
  COMPANY("COMPANY", "Company"),
  PARTNERSHIP("PARTNERSHIP", "Partnership"),
  TRUST("TRUST", "Trust"),
  GOVERNMENT_ENTITY("GOVERNMENT_ENTITY", "Government Entity"), 
  OTHER("OTHER", "Other");
  // @formatter:on  

  private String value;
  private String label;

  CommonOrganisationType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonOrganisationType fromValue(String text) {
    for (CommonOrganisationType b : CommonOrganisationType.values()) {
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
