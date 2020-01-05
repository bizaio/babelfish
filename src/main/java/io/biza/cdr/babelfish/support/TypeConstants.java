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
package io.biza.cdr.babelfish.support;

public final class TypeConstants {
  public static final String BASE_PAYLOAD_VERSION = "1";

  public static final String PAN_NUMBER_PATTERN = "(\\d{4} *){3}(\\d{4})";

  public static final String ISO17442_PATTERN = "^[0-9A-Z]{18}[0-9]{2}$";

  public static final String MASKED_ACCOUNT_PATTERN = "(xxx\\\\-xxx xxxxx|(xxxx ){3})?\\\\d{4}";

  public static final String AUSTRALIA_PHONE_CODE = "+61";

  public static final String AUSTRALIA_ALPHA2 = "AU";
}
