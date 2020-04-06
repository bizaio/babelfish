package io.biza.babelfish.oidc.enumerations;

import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OIDC Response Types", enumAsRef = true)
public enum OIDCResponseType {
  // @formatter:off
  CODE_ID_TOKEN("code id_token"),
  CODE_TOKEN("code token"),
  CODE_ID_TOKEN_TOKEN("code id_token token");
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
      List<String> responseTypes = Arrays.asList(String.valueOf(b.value).split(" "));
      List<String> inputTypes = Arrays.asList(String.valueOf(text).split(" "));
      if (inputTypes.containsAll(responseTypes) && responseTypes.containsAll(inputTypes)) {
        return b;
      }
    }
    return null;
  }

}
