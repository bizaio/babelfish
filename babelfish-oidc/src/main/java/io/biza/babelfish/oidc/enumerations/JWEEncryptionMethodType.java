/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWE Encryption Methods", enumAsRef = true)
public enum JWEEncryptionMethodType {
  // @formatter:off
  A128CBC_HS256("A128CBC-HS256"),
  A192CBC_HS384("A128CBC-HS256"),
  A256CBC_HS512("A256CBC-HS512"),
  A128GCM("A128GCM"),
  A192GCM("A192GCM"),
  A256GCM("A256GCM");
  // @formatter:on
  
  private String text;

  JWEEncryptionMethodType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWEEncryptionMethodType fromValue(String value) {
    for (JWEEncryptionMethodType b : JWEEncryptionMethodType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
}