package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nimbusds.jose.jwk.KeyUse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Public Key Use", enumAsRef = true)
public enum JWKPublicKeyUse {
  // @formatter:off
  SIGN("sig"),
  ENCRYPT("enc");
  // @formatter:on

  private String text;

  JWKPublicKeyUse(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWKPublicKeyUse fromValue(String value) {
    for (JWKPublicKeyUse b : JWKPublicKeyUse.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
  
  public KeyUse toNimbus() {
	  if(this.equals(SIGN)) {
		  return KeyUse.SIGNATURE;
	  } else if(this.equals(ENCRYPT)) {
		  return KeyUse.ENCRYPTION;
	  } else {
		  return null;
	  }
  }
  
  public JWKPublicKeyUse fromNimbus(KeyUse use) {
	  if(use.equals(KeyUse.ENCRYPTION)) {
		  return ENCRYPT;
	  } else if(use.equals(KeyUse.SIGNATURE)) {
		  return SIGN;
	  } else {
		  return null;
	  }
  }
}
