package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RegistrationErrorCode {
  // @formatter:off
  INVALID_REDIRECT_URI("invalid_redirect_uri"),
  INVALID_CLIENT_METADATA("invalid_client_metadata"),
  INVALID_SOFTWARE_STATEMENT("invalid_software_statement"),
  UNAPPROVED_SOFTWARE_STATEMENT("unapproved_software_statement");
  // @formatter:on

  private String text;

  RegistrationErrorCode(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static RegistrationErrorCode fromValue(String value) {
    for (RegistrationErrorCode b : RegistrationErrorCode.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}
