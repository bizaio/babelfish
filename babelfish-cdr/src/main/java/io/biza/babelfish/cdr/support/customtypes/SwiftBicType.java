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
package io.biza.babelfish.cdr.support.customtypes;

import java.io.IOException;
import java.io.InputStreamReader;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "Swift BIC Type")
@Slf4j
public class SwiftBicType implements LabelValueEnumInterface {
  private String value;
  private String label;

  SwiftBicType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SwiftBicType fromValue(String text) {
    try {
      CSVReader csvReader = new CSVReader(new InputStreamReader(SwiftBicType.class.getResourceAsStream("/swift-directory.csv")));
      String[] line;
      SwiftBicType result = null;
      while ((line = csvReader.readNext()) != null) {
        if (line[1].equals(text)) {
          result = new SwiftBicType(line[1], line[3]);
          break;
        }
      }
      csvReader.close();

      if (result != null) {
        return result;
      }
    } catch (CsvValidationException | IOException e) {
      LOG.error("Received unexpected exception while converting BSB");
    }

    throw new IllegalArgumentException(
        "Unable to find Swift BIC Code with specified input of " + text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return value + " - " + label;
  }

  @Override
  public String name() {
    return value;
  }
}
