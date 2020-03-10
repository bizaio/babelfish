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

@Schema(description = "JWT Signing Algorithm Types", enumAsRef = true)
public enum JWSSigningAlgorithmType {
  // @formatter:off
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

  JWSSigningAlgorithmType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static JWSSigningAlgorithmType fromValue(String value) {
    for (JWSSigningAlgorithmType b : JWSSigningAlgorithmType.values()) {
      if (String.valueOf(b.text).equalsIgnoreCase(value)) {
        return b;
      }
    }

    return null;
  }
}