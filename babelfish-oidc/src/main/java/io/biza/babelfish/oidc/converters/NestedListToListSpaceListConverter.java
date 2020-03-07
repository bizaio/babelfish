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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * A Jackson implementation to convert from a list of space separated strings into a List of List of Strings
 */
@Slf4j
public class NestedListToListSpaceListConverter extends StdConverter<List<List<Enum<?>>>, List<String>> {
  @Override
  public List<String> convert(List<List<Enum<?>>> value) {
    if (value == null || value.size() < 1) return List.of();
    List<String> stringList = new ArrayList<String>();
    for(List<Enum<?>> one : value) {
      stringList.add(one.stream().map(e -> {
        return e.toString();
      }).collect(Collectors.joining(" ")));
    }
    
    return stringList;
  }
}
