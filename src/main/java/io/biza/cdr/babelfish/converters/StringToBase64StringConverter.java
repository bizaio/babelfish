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
package io.biza.cdr.babelfish.converters;

import java.util.Base64;
import com.fasterxml.jackson.databind.util.StdConverter;


/**
 * A Jackson StdConverter implementation for converting between Java String and a Base64 String
 * 
 * @since 0.9.6
 */
public class StringToBase64StringConverter extends StdConverter<String, String> {

  @Override
  public String convert(String value) {
    if(value == null) return null;
    return Base64.getEncoder().encodeToString(value.getBytes());
  }

}
