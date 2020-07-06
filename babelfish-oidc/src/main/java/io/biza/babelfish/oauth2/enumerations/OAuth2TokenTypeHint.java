package io.biza.babelfish.oauth2.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2TokenTypeHint {
  // @formatter:off
  ACCESS_TOKEN("access_token"),
  REFRESH_TOKEN("refresh_token"),
  PCT("pct");
  // @formatter:on
  
  private String text;

  OAuth2TokenTypeHint(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2TokenTypeHint fromValue(String value) {
    for (OAuth2TokenTypeHint b : OAuth2TokenTypeHint.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
