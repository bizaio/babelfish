package io.biza.babelfish.cdr.enumerations.register;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Status", enumAsRef = true)
public enum JWKStatus implements LabelValueEnumInterface {
  // @formatter:off
  ACTIVE("ACTIVE", "Active");
  // @formatter:on

  private String value;

  private String label;

  JWKStatus(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static JWKStatus fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (JWKStatus b : JWKStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of JWKStatus from " + text,
        JWKStatus.class.getSimpleName(), JWKStatus.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}