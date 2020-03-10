package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2ErrorCode {
  // @formatter:off
  INVALID_REQUEST("invalid_request"),
  UNAUTHORISED_CLIENT("unauthorized_client"),
  UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
  UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
  INVALID_SCOPE("invalid_scope"),
  SERVER_ERROR("server_error"),
  TEMPORARILY_UNAVAILABLE("temporarily_unavailable"), 
  INVALID_CLIENT("invalid_client"), 
  INVALID_GRANT("invalid_grant");
  // @formatter:on

  private String text;

  OAuth2ErrorCode(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OAuth2ErrorCode fromValue(String value) {
    for (OAuth2ErrorCode b : OAuth2ErrorCode.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
