package io.biza.babelfish.cdr.enumerations.oidc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Levels of Assurance used within CDR", enumAsRef = true)
public enum CDRLoA implements LabelValueEnumInterface {
  // @formatter:off
  CL1("urn:cds.au:cdr:2", "urn:cds.au:cdr:2"),
  CL2("urn:cds.au:cdr:3", "urn:cds.au:cdr:3");
  // @formatter:on

  private String value;

  private String label;

  CDRLoA(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CDRLoA fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CDRLoA b : CDRLoA.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of RegisterScope from " + text,
        CDRLoA.class.getSimpleName(), CDRLoA.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}