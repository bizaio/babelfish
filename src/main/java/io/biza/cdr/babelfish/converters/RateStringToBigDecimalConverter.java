/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.converters;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between CDR RateString and Java BigDecimal
 * 
 * @since 0.9.6
 */
public class RateStringToBigDecimalConverter extends StdConverter<String, BigDecimal> {
  @Override
  public BigDecimal convert(String value) {
    if (value == null)
      return null;
    return new BigDecimal(value).setScale(Constants.CDR_RATESTRING_SCALE,
        Constants.CDR_RATESTRING_ROUNDING_MODE);
  }
}
