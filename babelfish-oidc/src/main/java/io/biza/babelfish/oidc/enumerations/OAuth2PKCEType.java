package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2PKCEType {
  // @formatter:off
  PLAIN("plain"),
  S256("S256");
  // @formatter:on
  
  private String text;

  OAuth2PKCEType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2PKCEType fromValue(String value) {
    for (OAuth2PKCEType b : OAuth2PKCEType.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
