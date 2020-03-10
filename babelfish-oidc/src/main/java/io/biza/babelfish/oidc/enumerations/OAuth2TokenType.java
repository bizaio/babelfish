package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2TokenType {
  // @formatter:off
  BEARER("Bearer");
  // @formatter:on
  
  private String text;

  OAuth2TokenType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2TokenType fromValue(String value) {
    for (OAuth2TokenType b : OAuth2TokenType.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
