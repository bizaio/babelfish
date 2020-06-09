package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2ClientType {
  // @formatter:off
  PUBLIC("public"),
  CONFIDENTIAL("confidential");
  // @formatter:on

  private String text;

  OAuth2ClientType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2ClientType fromValue(String value) {
    for (OAuth2ClientType b : OAuth2ClientType.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
