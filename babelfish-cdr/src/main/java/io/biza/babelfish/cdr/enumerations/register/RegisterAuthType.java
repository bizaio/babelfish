package io.biza.babelfish.cdr.enumerations.register;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Register Auth Configuration Type", enumAsRef = true)
public enum RegisterAuthType implements LabelValueEnumInterface {
  // @formatter:off
  SIGNED_JWT("SIGNED-JWT", "Signed JWT Authentication to Revocation Endpoint");
  // @formatter:on

  private String value;

  private String label;

  RegisterAuthType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static RegisterAuthType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (RegisterAuthType b : RegisterAuthType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of RegisterAuthType from " + text,
        RegisterAuthType.class.getSimpleName(), RegisterAuthType.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}