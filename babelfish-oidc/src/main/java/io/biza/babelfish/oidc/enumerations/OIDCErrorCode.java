package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OIDCErrorCode {
  // @formatter:off
  // OAuth 2.0 4.1.2.1
  INVALID_REQUEST("invalid_request"),
  UNAUTHORISED_CLIENT("unauthorized_client"),
  UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
  UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
  INVALID_SCOPE("invalid_scope"),
  SERVER_ERROR("server_error"),
  TEMPORARILY_UNAVAILABLE("temporarily_unavailable"), 
  INVALID_CLIENT("invalid_client"), 
  INVALID_GRANT("invalid_grant"),
  // OpenID Connect Core 3.1.2.6
  INTERACTION_REQUIRED("interaction_required"),
  LOGIN_REQUIRED("login_required"),
  ACCOUNT_SELECTION_REQUIRED("account_selection_required"),
  CONSENT_REQUIRED("consent_required"),
  INVALID_REQUEST_URI("invalid_request_uri"),
  REQUEST_NOT_SUPPORTED("request_not_supported"),
  REQUEST_URI_NOT_SUPPORTED("request_uri_not_supported"),
  REGISTRATION_NOT_SUPPORTED("registration_not_supported");
  // @formatter:on

  private String text;

  OIDCErrorCode(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OIDCErrorCode fromValue(String value) {
    for (OIDCErrorCode b : OIDCErrorCode.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
