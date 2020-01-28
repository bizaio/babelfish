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
package io.biza.babelfish.converter.cdr;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between Java BigDecimal and CDR RateString
 * 
 * @since 0.9.6
 */
public class BigDecimalToRateStringConverter extends StdConverter<BigDecimal, String> {
  @Override
  public String convert(BigDecimal value) {
    if (value == null)
      return null;
    final DecimalFormat myFormatter = new DecimalFormat(Constants.CDR_RATESTRING_PATTERN);
    return myFormatter.format(value);
  }
}
