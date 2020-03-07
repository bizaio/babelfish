package io.biza.babelfish.cdr.enumerations.oidc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Supported Response Types in CDR", enumAsRef = true)
public enum CDRResponseType implements LabelValueEnumInterface {
  // @formatter:off
  CODE_ID_TOKEN("code id_token", "code id_token");
  // @formatter:on

  private String value;

  private String label;

  CDRResponseType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CDRResponseType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CDRResponseType b : CDRResponseType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of RegisterScope from " + text,
        CDRResponseType.class.getSimpleName(), CDRResponseType.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}