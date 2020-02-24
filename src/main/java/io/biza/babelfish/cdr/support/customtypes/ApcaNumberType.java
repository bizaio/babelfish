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
import java.io.InputStream;
import java.io.InputStreamReader;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "APCA Numbers (aka BSB Numbers)")
@Slf4j
@Getter
@Setter
@Builder
public class ApcaNumberType implements LabelValueEnumInterface {
  private String bsb;
  private String bankCode;
  private String branchName;
  private String address;
  private String suburb;
  private String state;
  private String postcode;
  private String paymentSystems;

  ApcaNumberType(String bsb, String bankCode, String branchName, String address, String suburb,
      String state, String postcode, String paymentSystems) {
    this.bsb = bsb;
    this.bankCode = bankCode;
    this.branchName = branchName;
    this.address = address;
    this.suburb = suburb;
    this.state = state;
    this.postcode = postcode;
    this.paymentSystems = paymentSystems;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(bsb);
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    /*
     * Check if o is an instance of Complex or not "null instanceof [type]" also returns false
     */
    if (!(o instanceof ApcaNumberType)) {
      return false;
    }

    ApcaNumberType a = (ApcaNumberType) o;
    return a.bsb().equals(this.bsb());
  }

  @JsonCreator
  public static ApcaNumberType fromValue(String text) {
    LOG.debug("Converting APCA value from text of: {}", text);
    try {
      InputStream bsbDirectory = ApcaNumberType.class.getResourceAsStream("/bsb-directory.csv");

      CSVReader csvReader = new CSVReader(new InputStreamReader(bsbDirectory));
      String[] line;
      ApcaNumberType result = null;


      while ((line = csvReader.readNext()) != null) {
        if (line[0].equals(text)) {
          result = ApcaNumberType.builder().bsb(line[0]).bankCode(line[1]).branchName(line[2])
              .address(line[3]).suburb(line[4]).state(line[5]).postcode(line[6])
              .paymentSystems(line[7]).build();
          break;
        }
      }

      csvReader.close();

      if (result != null) {
        LOG.debug("APCA Converter produced result of: {}", result.toString());
        return result;
      }

    } catch (CsvValidationException | IOException e) {
      LOG.error("Received unexpected exception while converting BSB");
    }

    throw new IllegalArgumentException(
        "Unable to find BSB in Directory with specified input of " + text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return bsb() + " - " + branchName();
  }

  @Override
  public String name() {
    return bsb();
  }
}
