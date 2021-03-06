package io.biza.babelfish.oidc.enumerations.cdr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Claims used within the CDR", enumAsRef = true)
public enum CDRClaim implements LabelValueEnumInterface {
  // @formatter:off
  SUB("sub", "sub"),
  ACR("acr", "acr"),
  AUTH_TIME("auth_time", "auth_time"),
  NAME("name", "name"),
  GIVEN_NAME("given_name", "given_name"),
  FAMILY_NAME("family_name", "family_name"),
  UPDATED_AT("updated_at", "updated_at"),
  REFRESH_TOKEN_EXPIRES_AT("refresh_token_expires_at", "refresh_token_expires_at"),
  SHARING_DURATION("sharing_duration", "sharing_duration"),
  SHARING_EXPIRES_AT("sharing_expires_at", "sharing_expires_at");
  // @formatter:on

  private String value;

  private String label;

  CDRClaim(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CDRClaim fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CDRClaim b : CDRClaim.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of RegisterScope from " + text,
        CDRClaim.class.getSimpleName(), CDRClaim.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}