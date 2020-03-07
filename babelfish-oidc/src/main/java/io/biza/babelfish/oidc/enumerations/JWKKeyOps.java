package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Key Operations", enumAsRef = true)
public enum JWKKeyOps {
  // @formatter:off
  SIGN("sign"),
  VERIFY("verify"),
  ENCRYPT("encrypt"),
  DECRYPT("decrypt"),
  WRAP_KEY("wrapKey"),
  UNWRAP_KEY("unwrapKey"),
  DERIVE_KEY("deriveKey"),
  DERIVE_BITS("deriveBits");
  // @formatter:on

  private String text;

  JWKKeyOps(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWKKeyOps fromValue(String value) {
    for (JWKKeyOps b : JWKKeyOps.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }

}
