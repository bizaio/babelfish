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

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson implementation to convert an OffsetDateTime to the numbers of seconds it represents in the future
 */
public class OffsetDateTimeToFutureSecondsConverter extends StdConverter<OffsetDateTime, Long> {
  @Override
  public Long convert(OffsetDateTime value) {
    if (value == null)
      return Long.valueOf(0);
    
    long expirySeconds = ChronoUnit.SECONDS.between(OffsetDateTime.now(), value);
    
    return expirySeconds > 0 ? expirySeconds : 0;
    
  }
}
