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

import java.util.List;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson implementation to convert a List of Strings to a space separated string set
 */
public class ListStringToSpaceListConverter extends StdConverter<List<String>, String> {
  @Override
  public String convert(List<String> value) {
    if (value == null || value.size() < 1) return "";
    return String.join(" ", value);
  }
}
