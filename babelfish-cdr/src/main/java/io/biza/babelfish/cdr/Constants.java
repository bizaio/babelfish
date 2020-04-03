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
package io.biza.babelfish.cdr;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import io.biza.babelfish.cdr.models.VersionerConstants;
import io.biza.babelfish.cdr.models.payloads.ErrorV1;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurer;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurerInterface;
import io.biza.babelfish.cdr.support.BabelfishVersionedModel;
import io.swagger.v3.oas.annotations.media.Schema;

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
      DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  /**
   * CDR DateString
   */
  public static final DateTimeFormatter CDR_DATESTRING_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

  /**
   * Orika implementer
   */
  public static final String ORIKA_PACKAGE_BASE = OrikaFactoryConfigurer.class.getPackageName();
  public static final String ORIKA_IMPLEMENTING_CLASS =
      OrikaFactoryConfigurerInterface.class.getName();

  public static final String BASE_MODELS_PACKAGE = VersionerConstants.class.getPackageName();
  public static final String BASE_MODELS_ANNOTATION = Schema.class.getName();
  public static final String VERSIONED_MODEL = BabelfishVersionedModel.class.getName();

  /**
   * Prepared Error Responses
   */
  public static final ErrorV1 ERROR_ATTRIBUTE_NOT_SUPPORTED =
      ErrorV1.builder().code("1001").title("Attribute Not Supported")
          .detail("Provided content contains attribute data which is not supported").build();
  public static final ErrorV1 ERROR_INVALID_ENUM_VALUE =
      ErrorV1.builder().code("1002").title("Invalid Enumeration Value")
          .detail("Provided content contains enumeration data which is not supported").build();
  public static final ErrorV1 ERROR_PAYLOAD_CONVERSION_FAILURE = ErrorV1.builder().code("2001")
      .title("Payload Conversion Failure")
      .detail(
          "An error was encountered while attempting to perform conversion between source and destination payload")
      .build();
  public static final ErrorV1 ERROR_UNSUPPORTED_VERSION = ErrorV1.builder().code("3001")
      .title("Unsupported Version")
      .detail("The request specified a version which is not supported by this endpoint").build();


}
