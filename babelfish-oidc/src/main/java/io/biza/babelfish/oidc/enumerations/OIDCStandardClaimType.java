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

@Schema(description = "OIDC Standard Claim Types", enumAsRef = true)
public enum OIDCStandardClaimType {
  // @formatter:off
  SUB("sub"),
  NAME("name"),
  GIVEN_NAME("given_name"),
  FAMILY_NAME("family_name"),
  MIDDLE_NAME("middle_name"),
  NICKNAME("nickname"),
  PREFERRED_USERNAME("preferred_username"),
  PROFILE("profile"),
  PICTURE("picture"),
  WEBSITE("website"),
  EMAIL("email"),
  EMAIL_VERIFIED("email_verified"),
  GENDER("gender"),
  BIRTHDATE("birthdate"),
  ZONEINFO("zoneinfo"),
  LOCALE("locale"),
  PHONE_NUMBER("phone_number"),
  PHONE_NUMBER_VERIFIED("phone_number_verified"),
  ADDRESS("address"),
  UPDATED_AT("updated_at");
  // @formatter:on
  
  private String text;

  OIDCStandardClaimType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OIDCStandardClaimType fromValue(String value) {
    for (OIDCStandardClaimType b : OIDCStandardClaimType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
}