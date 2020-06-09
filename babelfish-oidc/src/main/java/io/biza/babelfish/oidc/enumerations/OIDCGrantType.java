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

import io.biza.babelfish.oidc.exceptions.oauth2.UnsupportedGrantTypeException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OIDC Grant Types", enumAsRef = true)
public enum OIDCGrantType {
  // @formatter:off
  AUTHORIZATION_CODE("authorization_code"),
  CLIENT_CREDENTIALS("client_credentials"),
  JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
  REFRESH_TOKEN("refresh_token"),
  PASSWORD("password");
  // @formatter:on
  
  String text;

  OIDCGrantType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OIDCGrantType fromValue(String value) throws UnsupportedGrantTypeException {
    for (OIDCGrantType b : OIDCGrantType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    throw UnsupportedGrantTypeException.builder().build();
  }
}