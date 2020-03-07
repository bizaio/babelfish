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
package io.biza.babelfish.oidc.converters;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between an Epoch and Java
 * OffsetDateTime
 * 
 * @since 0.9.6
 */
public class EpochToOffsetDateTimeConverter extends StdConverter<Long, OffsetDateTime> {
  @Override
  public OffsetDateTime convert(Long value) {
    if (value == null || value == 0L)
      return null;
    return Instant.ofEpochSecond(value).atOffset(ZoneOffset.UTC);
  }
}
