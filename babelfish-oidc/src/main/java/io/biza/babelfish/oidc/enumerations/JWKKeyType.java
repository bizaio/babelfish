package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nimbusds.jose.jwk.KeyType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWK Key Types", enumAsRef = true)
public enum JWKKeyType {
  // @formatter:off
  RSA("RSA"),
  EC("EC");
  // @formatter:on

  private String text;

  JWKKeyType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWKKeyType fromValue(String value) {
    for (JWKKeyType b : JWKKeyType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
  
  public KeyType toNimbus() {
	  if(this.equals(RSA)) {
		  return KeyType.RSA;
	  } else if(this.equals(EC)) {
		  return KeyType.EC;
	  } else {
		  return null;
	  }
  }
  
  public JWKKeyType fromNimbus(KeyType type) {
	  if(type.equals(KeyType.RSA)) {
		  return JWKKeyType.RSA;
	  } else if(type.equals(KeyType.EC)) {
		  return JWKKeyType.EC;
	  } else {
		  return null;
	  }
  }

}
