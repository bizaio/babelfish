package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2GrantType {
  // @formatter:off
  CLIENT_CREDENTIALS("client_credentials"),
  AUTHORIZATION_CODE("authorization_code");
  // @formatter:on
  
  private String text;

  OAuth2GrantType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2GrantType fromValue(String value) {
    for (OAuth2GrantType b : OAuth2GrantType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
}
