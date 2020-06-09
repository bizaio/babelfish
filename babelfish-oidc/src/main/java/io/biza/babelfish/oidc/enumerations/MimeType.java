package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "MIME Types", enumAsRef = true)
public enum MimeType {
  // @formatter:off
  APPLICATION_JSON("application/json");
  // @formatter:on
  
  private String text;

  MimeType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static MimeType fromValue(String value) {
    for (MimeType b : MimeType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }

}
