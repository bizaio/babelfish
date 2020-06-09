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

import java.util.List;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson implementation to convert a separated list of strings to a List<String>
 */
public class SpaceListToListStringConverter extends StdConverter<String, List<String>> {
  @Override
  public List<String> convert(String value) {
    if (value == null || value == "") return List.of();
    return List.of(value.split(" "));
  }
}
