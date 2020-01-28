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

import java.util.Base64;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Base64 String and Java String
 * 
 * @since 0.9.6
 */
public class Base64StringToStringConverter extends StdConverter<String, String> {
  @Override
  public String convert(String value) {
    if (value == null)
      return null;
    return Base64.getDecoder().decode(value).toString();
  }
}
