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

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFlatUnitType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFloorLevelType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFPostalDeliveryType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStateType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetSuffix;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(
    description = "Australian address formatted according to the file format defined by the [PAF file format](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf)")
public interface CommonPAFAddress {

  @BabelFishModelProperty(
      description = "Unique identifier for an address as defined by Australia Post.  Also known as Delivery Point Identifier")
  @JsonGetter("dpid")
  public String getDpid();

  @JsonSetter("dpid")
  public void setDpid(String dpid);

  public default CommonPAFAddress dpid(String dpid) {
    setDpid(dpid);
    return this;
  }

  @BabelFishModelProperty(
      description = "Thoroughfare number for a property (first number in a property ranged address)")
  @JsonGetter("thoroughfareNumber1")
  public Integer getThoroughfareNumber1();

  @JsonSetter("thoroughfareNumber1")
  public void setThoroughfareNumber1(Integer thoroughfareNumber1);

  public default void setThoroughfareNumber1(String thoroughfareNumber1) {
    if (thoroughfareNumber1 != null) {
      setThoroughfareNumber1(
          thoroughfareNumber1.equals("00000") ? null : Integer.parseInt(thoroughfareNumber1));
    }
  }

  public default CommonPAFAddress thoroughfareNumber1(Integer thoroughfareNumber1) {
    setThoroughfareNumber1(thoroughfareNumber1);
    return this;
  }

  @BabelFishModelProperty(
      description = "Suffix for the thoroughfare number. Only relevant is thoroughfareNumber1 is populated")
  @JsonGetter("thoroughfareNumber1Suffix")
  public String getThoroughfareNumber1Suffix();

  @JsonSetter("thoroughfareNumber1Suffix")
  public void setThoroughfareNumber1Suffix(String thoroughfareNumber1Suffix);

  public default CommonPAFAddress thoroughfareNumber1Suffix(String thoroughfareNumber1Suffix) {
    setThoroughfareNumber1Suffix(thoroughfareNumber1Suffix);
    return this;
  }

  @BabelFishModelProperty(
      description = "Second thoroughfare number (only used if the property has a ranged address eg 23-25)")
  @JsonGetter("thoroughfareNumber2")
  public Integer getThoroughfareNumber2();

  @JsonSetter("thoroughfareNumber2")
  public void setThoroughfareNumber2(Integer thoroughfareNumber1);

  public default void setThoroughfareNumber2(String thoroughfareNumber2) {
    if (thoroughfareNumber2 != null) {
      setThoroughfareNumber2(
          thoroughfareNumber2.equals("00000") ? null : Integer.parseInt(thoroughfareNumber2));
    }
  }

  public default CommonPAFAddress thoroughfareNumber2(Integer thoroughfareNumber2) {
    setThoroughfareNumber1(thoroughfareNumber2);
    return this;
  }

  @BabelFishModelProperty(
      description = "Suffix for the second thoroughfare number. Only relevant is thoroughfareNumber2 is populated")
  @JsonGetter("thoroughfareNumber2Suffix")
  public String getThoroughfareNumber2Suffix();

  @JsonSetter("thoroughfareNumber2Suffix")
  public void setThoroughfareNumber2Suffix(String thoroughfareNumber2Suffix);

  public default CommonPAFAddress thoroughfareNumber2Suffix(String thoroughfareNumber2Suffix) {
    setThoroughfareNumber1Suffix(thoroughfareNumber2Suffix);
    return this;
  }

  @BabelFishModelProperty(description = "Type of flat or unit for the address",
      dataType = "java.lang.String")
  @JsonGetter("flatUnitType")
  public AddressPAFFlatUnitType getFlatUnitType();

  @JsonSetter("flatUnitType")
  public void setFlatUnitType(AddressPAFFlatUnitType flatUnitType);

  public default CommonPAFAddress flatUnitType(AddressPAFFlatUnitType flatUnitType) {
    setFlatUnitType(flatUnitType);
    return this;
  }

  @BabelFishModelProperty(description = "Unit number (including suffix, if applicable)")
  @JsonGetter("flatUnitNumber")
  public String getFlatUnitNumber();

  @JsonSetter("flatUnitNumber")
  public void setFlatUnitNumber(String flatUnitNumber);

  public default CommonPAFAddress flatUnitNumber(String flatUnitNumber) {
    setFlatUnitNumber(flatUnitNumber);
    return this;
  }

  @BabelFishModelProperty(description = "Type of floor or level for the address",
      dataType = "java.lang.String")
  @JsonGetter("floorLevelType")
  public AddressPAFFloorLevelType getFloorLevelType();

  @JsonSetter("floorLevelType")
  public void setFloorLevelType(AddressPAFFloorLevelType floorLevelType);

  public default CommonPAFAddress floorLevelType(AddressPAFFloorLevelType floorLevelType) {
    setFloorLevelType(floorLevelType);
    return this;
  }

  @BabelFishModelProperty(description = "Floor or level number (including alpha characters)")
  @JsonGetter("floorLevelNumber")
  public String getFloorLevelNumber();

  @JsonSetter("floorLevelNumber")
  public void setFloorLevelNumber(String floorLevelNumber);

  public default CommonPAFAddress floorLevelNumber(String floorLevelNumber) {
    setFloorLevelNumber(floorLevelNumber);
    return this;
  }

  @BabelFishModelProperty(description = "Allotment number for the address")
  @JsonGetter("lotNumber")
  public String getLotNumber();

  @JsonSetter("lotNumber")
  public void setLotNumber(String lotNumber);

  public default CommonPAFAddress lotNumber(String lotNumber) {
    setLotNumber(lotNumber);
    return this;
  }

  @BabelFishModelProperty(description = "Building/Property name 1")
  @JsonGetter("buildingName1")
  public String getBuildingName1();

  @JsonSetter("buildingName1")
  public void setBuildingName1(String buildingName1);

  public default CommonPAFAddress buildingName1(String buildingName1) {
    setBuildingName1(buildingName1);
    return this;
  }

  @BabelFishModelProperty(description = "Building/Property name 2")  
  @JsonGetter("buildingName2")
  public String getBuildingName2();

  @JsonSetter("buildingName2")
  public void setBuildingName2(String buildingName2);

  public default CommonPAFAddress buildingName2(String buildingName2) {
    setBuildingName2(buildingName2);
    return this;
  }

  @BabelFishModelProperty(description = "The name of the street")
  @JsonGetter("streetName")
  public String getStreetName();

  @JsonSetter("streetName")
  public void setStreetName(String streetName);

  public default CommonPAFAddress streetName(String streetName) {
    setStreetName(streetName);
    return this;
  }

  @BabelFishModelProperty(
      description = "The street type. Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonGetter("streetType")
  public AddressPAFStreetType getStreetType();

  @JsonSetter("streetType")
  public void setStreetType(AddressPAFStreetType streetType);

  public default CommonPAFAddress streetType(AddressPAFStreetType streetType) {
    setStreetType(streetType);
    return this;
  }

  @BabelFishModelProperty(
      description = "The street type suffix. Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonGetter("streetSuffix")
  public AddressPAFStreetSuffix getStreetSuffix();

  @JsonSetter("streetSuffix")
  public void setStreetSuffix(AddressPAFStreetSuffix streetSuffix);

  public default CommonPAFAddress streetSuffix(AddressPAFStreetSuffix streetSuffix) {
    setStreetSuffix(streetSuffix);
    return this;
  }

  @BabelFishModelProperty(
      description = "Postal delivery type. (eg. PO BOX). Valid enumeration defined by Australia Post PAF code file",
      dataType = "java.lang.String")
  @JsonGetter("postalDeliveryType")
  public AddressPAFPostalDeliveryType getPostalDeliveryType();

  @JsonSetter("postalDeliveryType")
  public void setPostalDeliveryType(AddressPAFPostalDeliveryType postalDeliveryType);

  public default CommonPAFAddress postalDeliveryType(
      AddressPAFPostalDeliveryType postalDeliveryType) {
    setPostalDeliveryType(postalDeliveryType);
    return this;
  }

  @BabelFishModelProperty(
      description = "Postal delivery number if the address is a postal delivery type")
  @JsonGetter("postalDeliveryNumber")
  public Integer getPostalDeliveryNumber();

  @JsonSetter("postalDeliveryNumber")
  public void setPostalDeliveryNumber(Integer postalDeliveryNumber);

  public default void setPostalDeliveryNumber(String postalDeliveryNumber) {
    if (postalDeliveryNumber != null) {
      setPostalDeliveryNumber(
          postalDeliveryNumber.equals("00000") ? null : Integer.parseInt(postalDeliveryNumber));
    }
  }

  public default CommonPAFAddress postalDeliveryNumber(Integer postalDeliveryNumber) {
    setPostalDeliveryNumber(postalDeliveryNumber);
    return this;
  }


  @BabelFishModelProperty(
      description = "Postal delivery number prefix related to the postal delivery number")
  @JsonGetter("postalDeliveryNumberPrefix")
  public String getPostalDeliveryNumberPrefix();

  @JsonSetter("postalDeliveryNumberPrefix")
  public void setPostalDeliveryNumberPrefix(String postalDeliveryNumberPrefix);

  public default CommonPAFAddress postalDeliveryNumberPrefix(String postalDeliveryNumberPrefix) {
    setPostalDeliveryNumberPrefix(postalDeliveryNumberPrefix);
    return this;
  }

  @BabelFishModelProperty(
      description = "Postal delivery number suffix related to the postal delivery number")
  @JsonGetter("postalDeliveryNumberSuffix")
  public String getPostalDeliveryNumberSuffix();

  @JsonSetter("postalDeliveryNumberSuffix")
  public void setPostalDeliveryNumberSuffix(String postalDeliveryNumberSuffix);

  public default CommonPAFAddress postalDeliveryNumberSuffix(String postalDeliveryNumberSuffix) {
    setPostalDeliveryNumberSuffix(postalDeliveryNumberSuffix);
    return this;
  }

  @BabelFishModelProperty(description = "Full name of locality", required = true)
  @JsonGetter("localityName")
  public String getLocalityName();

  @JsonSetter("localityName")
  public void setLocalityName(@NotNull String localityName);

  public default CommonPAFAddress localityName(@NotNull String localityName) {
    setLocalityName(localityName);
    return this;
  }

  @BabelFishModelProperty(description = "Postcode for the locality", required = true)
  @JsonGetter("postcode")
  public String getPostcode();

  @JsonSetter("postcode")
  public void setPostcode(@NotNull String postcode);

  public default CommonPAFAddress postcode(@NotNull String postcode) {
    setPostcode(postcode);
    return this;
  }

  @BabelFishModelProperty(
      description = "State in which the address belongs. Valid enumeration defined by Australia Post PAF code file",
      required = true, dataType = "java.lang.String")
  @JsonGetter("state")
  public AddressPAFStateType getState();

  @JsonSetter("state")
  public void setState(@NotNull AddressPAFStateType state);

  public default CommonPAFAddress state(@NotNull AddressPAFStateType state) {
    setState(state);
    return this;
  }
}
