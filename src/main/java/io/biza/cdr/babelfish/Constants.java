/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

/**
 * Various Constants used within Babelfish
 * 
 * @since 0.9.6
 */
public class Constants {
  /**
   * Generic Constants
   */
  public static final String DEFAULT_LOCALE = "en";

  /**
   * CDR AmountString
   */
  public static final String CDR_AMOUNTSTRING_PATTERN =
      "##############0.00###;-##############0.00###";

  /**
   * CDR RateString
   */
  public static final String CDR_RATESTRING_PATTERN = "0.00##############;0.00##############";

  public static final int CDR_RATESTRING_SCALE = 16;

  public static final RoundingMode CDR_RATESTRING_ROUNDING_MODE = RoundingMode.HALF_EVEN;

  /**
   * CDR DateTimeString
   */
  public static final DateTimeFormatter CDR_DATETIMESTRING_FORMATTER =
      DateTimeFormatter.ISO_INSTANT;

  /**
   * CDR DateString
   */
  public static final DateTimeFormatter CDR_DATESTRING_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
}
