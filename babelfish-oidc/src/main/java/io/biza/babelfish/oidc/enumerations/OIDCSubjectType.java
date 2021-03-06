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

@Schema(description = "OIDC Response Modes", enumAsRef = true)
public enum OIDCSubjectType {
  // @formatter:off
  PAIRWISE("pairwise"),
  PUBLIC("public");
  // @formatter:on

  String text;

  OIDCSubjectType(String value) {
    this.text = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return text;
  }

  @JsonCreator
  public static OIDCSubjectType fromValue(String value) {
    for (OIDCSubjectType b : OIDCSubjectType.values()) {
      if (String.valueOf(b.text).equals(value)) {
        return b;
      }
    }

    return null;
  }
}