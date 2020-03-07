package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Algorithms, a concatenation of JWE and JWS Algorithms", enumAsRef = true)
public enum JWKAlgorithm {
  // @formatter:off
  RSA1_5("RSA1_5"),
  RSA_OAEP("RSA-OAEP"),
  ECDH_ES("ECDH-ES"),
  A128KW("A128KW"),
  A256KW("A256KW"),
  A128GCM("A128GCM"),
  A256GCM("A256GCM"),
  HS256("HS256"),
  HS384("HS384"),
  HS512("HS512"),
  RS256("RS256"),
  RS384("RS384"),
  RS512("RS512"),
  ES256("ES256"),
  ES384("ES384"),
  ES512("ES512"),
  PS256("PS256"),
  PS384("PS384"),
  PS512("PS512"),
  NONE("none");
  // @formatter:on

  private String text;

  JWKAlgorithm(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWKAlgorithm fromValue(String value) {
    for (JWKAlgorithm b : JWKAlgorithm.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }

}
