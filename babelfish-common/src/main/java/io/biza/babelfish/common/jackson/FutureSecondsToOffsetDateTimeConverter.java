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
package io.biza.babelfish.common.jackson;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson implementation to convert a number of seconds to a future dated OffsetDateTime
 */
public class FutureSecondsToOffsetDateTimeConverter extends StdConverter<Long, OffsetDateTime> {
  @Override
  public OffsetDateTime convert(Long value) {
    if (value == null || value == 0L)
      return null;
    
    return OffsetDateTime.now().plusSeconds(value);
  }
}
