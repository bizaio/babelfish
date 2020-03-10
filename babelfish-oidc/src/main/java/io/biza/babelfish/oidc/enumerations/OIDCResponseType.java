package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OIDC Response Types", enumAsRef = true)
public enum OIDCResponseType {
  // @formatter:off
  CODE_ID_TOKEN("code id_token"),
  TOKEN("token");
  // @formatter:on

  private String value;

  OIDCResponseType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OIDCResponseType fromValue(String text) {
    for (OIDCResponseType b : OIDCResponseType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
  
}