/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFlatUnitType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFloorLevelType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFPostalDeliveryType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStateType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetSuffix;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(
    description = "Australian address formatted according to the file format defined by the [PAF file format](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf)")
public abstract class CommonPAFAddress {

  @BabelFishModelProperty(
      description = "Unique identifier for an address as defined by Australia Post.  Also known as Delivery Point Identifier")
  @JsonProperty("dpid")
  public String dpid;

  @BabelFishModelProperty(
      description = "Thoroughfare number for a property (first number in a property ranged address)")
  @JsonProperty("thoroughfareNumber1")
  public Integer thoroughfareNumber1;

  public void thoroughfareNumber1(String thoroughfareNumber1String) {
    if (thoroughfareNumber1String != null) {
      thoroughfareNumber1 = thoroughfareNumber1String.equals("00000") ? null
          : Integer.parseInt(thoroughfareNumber1String);
    }
  }

  @BabelFishModelProperty(
      description = "Suffix for the thoroughfare number. Only relevant is thoroughfareNumber1 is populated")
  @JsonProperty("thoroughfareNumber1Suffix")
  public String thoroughfareNumber1Suffix;

  @BabelFishModelProperty(
      description = "Second thoroughfare number (only used if the property has a ranged address eg 23-25)")
  @JsonProperty("thoroughfareNumber2")
  public Integer thoroughfareNumber2;

  public void thoroughfareNumber2(String thoroughfareNumber2String) {
    if (thoroughfareNumber2String != null) {
      thoroughfareNumber2 = thoroughfareNumber2String.equals("00000") ? null
          : Integer.parseInt(thoroughfareNumber2String);
    }
  }

  @BabelFishModelProperty(
      description = "Suffix for the second thoroughfare number. Only relevant is thoroughfareNumber2 is populated")
  @JsonProperty("thoroughfareNumber2Suffix")
  public String thoroughfareNumber2Suffix;

  @BabelFishModelProperty(description = "Type of flat or unit for the address",
      dataType = "java.lang.String")
  @JsonProperty("flatUnitType")
  public AddressPAFFlatUnitType flatUnitType;

  @BabelFishModelProperty(description = "Unit number (including suffix, if applicable)")
  @JsonProperty("flatUnitNumber")
  public String flatUnitNumber;

  @BabelFishModelProperty(description = "Type of floor or level for the address",
      dataType = "java.lang.String")
  @JsonProperty("floorLevelType")
  public AddressPAFFloorLevelType floorLevelType;

  @BabelFishModelProperty(description = "Floor or level number (including alpha characters)")
  @JsonProperty("floorLevelNumber")
  public String floorLevelNumber;

  @BabelFishModelProperty(description = "Allotment number for the address")
  @JsonProperty("lotNumber")
  public String lotNumber;

  @BabelFishModelProperty(description = "Building/Property name 1")
  @JsonProperty("buildingName1")
  public String buildingName1;

  @BabelFishModelProperty(description = "Building/Property name 2")
  @JsonProperty("buildingName2")
  public String buildingName2;

  @BabelFishModelProperty(description = "The name of the street")
  @JsonProperty("streetName")
  public String streetName;

  @BabelFishModelProperty(
      description = "The street type. Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonProperty("streetType")
  public AddressPAFStreetType streetType;

  @BabelFishModelProperty(
      description = "The street type suffix. Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonProperty("streetSuffix")
  public AddressPAFStreetSuffix streetSuffix;

  @BabelFishModelProperty(
      description = "Postal delivery type. (eg. PO BOX). Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonProperty("postalDeliveryType")
  public AddressPAFPostalDeliveryType postalDeliveryType;

  @BabelFishModelProperty(
      description = "Postal delivery number if the address is a postal delivery type")
  @JsonProperty("postalDeliveryNumber")
  public Integer postalDeliveryNumber;

  public void postalDeliveryNumber(String postalDeliveryNumberString) {
    if (postalDeliveryNumberString != null) {
      postalDeliveryNumber = postalDeliveryNumberString.equals("00000") ? null
          : Integer.parseInt(postalDeliveryNumberString);
    }
  }

  @BabelFishModelProperty(
      description = "Postal delivery number prefix related to the postal delivery number")
  @JsonProperty("postalDeliveryNumberPrefix")
  public String postalDeliveryNumberPrefix;

  @BabelFishModelProperty(
      description = "Postal delivery number suffix related to the postal delivery number")
  @JsonProperty("postalDeliveryNumberSuffix")
  public String postalDeliveryNumberSuffix;

  @BabelFishModelProperty(description = "Full name of locality", required = true)
  @JsonProperty("localityName")
  public String localityName;

  @BabelFishModelProperty(description = "Postcode for the locality", required = true)
  @JsonProperty("postcode")
  public String postcode;

  @BabelFishModelProperty(
      description = "State in which the address belongs. Valid enumeration defined by Australia Post PAF code file",
      required = true, dataType = "java.lang.String")
  @JsonProperty("state")
  public AddressPAFStateType state;
}
