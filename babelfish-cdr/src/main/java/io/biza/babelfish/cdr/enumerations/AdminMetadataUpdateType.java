/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Metadata Update Request Type", name = "AdminMetadataUpdateType", enumAsRef = true)
public enum AdminMetadataUpdateType implements LabelValueEnumInterface {
  // @formatter:off
  REFRESH("REFRESH", "Request a Refresh");
  // @formatter:on
  private String value;

  private String label;

  AdminMetadataUpdateType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AdminMetadataUpdateType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (AdminMetadataUpdateType b : AdminMetadataUpdateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of Metadata Update Type from " + text,
        AdminMetadataUpdateType.class.getSimpleName(), AdminMetadataUpdateType.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
