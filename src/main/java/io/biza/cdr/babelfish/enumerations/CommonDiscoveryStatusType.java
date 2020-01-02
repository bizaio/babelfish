package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Common Discovery: Service Status Type")
public enum CommonDiscoveryStatusType implements LabelValueEnumInterface {
  // @formatter:off
  OK("OK", "Implementation is fully functional"),
  PARTIAL_FAILURE("PARTIAL_FAILURE", "One or more endpoints are unexpectedly unavailable"),
  UNAVAILABLE("UNAVAILABLE", "The full implementation is unexpectedly unavailable"),
  SCHEDULED_OUTAGE("SCHEDULED_OUTAGE", "An advertised outage is in effect");
  // @formatter:on


  private String value;
  private String label;

  CommonDiscoveryStatusType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CommonDiscoveryStatusType fromValue(String text) {
    for (CommonDiscoveryStatusType b : CommonDiscoveryStatusType.values()) {
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
