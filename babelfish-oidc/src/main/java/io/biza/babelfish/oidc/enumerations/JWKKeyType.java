package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Key Types", enumAsRef = true)
public enum JWKKeyType {
  // @formatter:off
  RSA("RSA"),
  EC("EC");
  // @formatter:on

  private String text;

  JWKKeyType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWKKeyType fromValue(String value) {
    for (JWKKeyType b : JWKKeyType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }

}
