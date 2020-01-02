package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Customer Type")
public enum PayloadTypeCustomer implements LabelValueEnumInterface {
  // @formatter:off    
    PERSON("person", "Person Customer Type"),
    ORGANISATION("organisation", "Organisation Customer Type");
    // @formatter:on

  private String value;
  private String label;

  PayloadTypeCustomer(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeCustomer fromValue(String text) {
    for (PayloadTypeCustomer b : PayloadTypeCustomer.values()) {
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
