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
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "Merchant Category Codes", enumAsRef = true)
@Slf4j
public class MerchantCategoryCodeType implements LabelValueEnumInterface {
  private String value;
  private String label;

  MerchantCategoryCodeType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static MerchantCategoryCodeType fromValue(String text)
      throws CsvValidationException, IOException {
    CSVReader csvReader = new CSVReader(new InputStreamReader(MerchantCategoryCodeType.class.getResourceAsStream("/merchant-codes.csv")));
    String[] line;
    MerchantCategoryCodeType result = null;
    while ((line = csvReader.readNext()) != null) {
      if (line[0].equals(text)) {
        result = new MerchantCategoryCodeType(line[0], line[1]);
        break;
      }
    }
    csvReader.close();

    if (result != null) {
      return result;
    }

    throw new IllegalArgumentException(
        "Unable to find Merchant Category Code with specified input of " + text);
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
  
  @Override
  public boolean equals(Object o) {
    if(o == this) { return true; }
    
    if(!(o instanceof MerchantCategoryCodeType)) {
      return false;
    }
    MerchantCategoryCodeType c = (MerchantCategoryCodeType) o;
    return toString().equals(c.toString());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
  
  
}
