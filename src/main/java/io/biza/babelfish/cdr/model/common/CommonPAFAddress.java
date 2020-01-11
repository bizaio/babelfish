/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFFlatUnitType;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFFloorLevelType;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFPostalDeliveryType;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFStateType;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFStreetSuffix;
import io.biza.babelfish.cdr.v1.enumerations.AddressPAFStreetType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(
    description = "Australian address formatted according to the file format defined by the [PAF file format](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf)")
public abstract class CommonPAFAddress<T> {
  @Schema(
      description = "Unique identifier for an address as defined by Australia Post.  Also known as Delivery Point Identifier")
  @JsonProperty("dpid")
  public String dpid;

  public String dpid() {
    return getDpid();
  }

  @SuppressWarnings("unchecked")
  public T dpid(String dpid) {
    setDpid(dpid);
    return (T) this;
  }

  @Schema(
      description = "Thoroughfare number for a property (first number in a property ranged address)")
  @JsonProperty("thoroughfareNumber1")
  public Integer thoroughfareNumber1;

  public Integer thoroughfareNumber1() {
    return getThoroughfareNumber1();
  }

  @SuppressWarnings("unchecked")
  public T thoroughfareNumber1(Integer thoroughfareNumber1) {
    setThoroughfareNumber1(thoroughfareNumber1);
    return (T) this;
  }

  public void thoroughfareNumber1(String thoroughfareNumber1String) {
    if (thoroughfareNumber1String != null) {
      thoroughfareNumber1 = thoroughfareNumber1String.equals("00000") ? null
          : Integer.parseInt(thoroughfareNumber1String);
    }
  }

  @Schema(
      description = "Suffix for the thoroughfare number. Only relevant is thoroughfareNumber1 is populated")
  @JsonProperty("thoroughfareNumber1Suffix")
  public String thoroughfareNumber1Suffix;

  public String thoroughfareNumber1Suffix() {
    return getThoroughfareNumber1Suffix();
  }

  @SuppressWarnings("unchecked")
  public T thoroughfareNumber1Suffix(String thoroughfareNumber1Suffix) {
    setThoroughfareNumber1Suffix(thoroughfareNumber1Suffix);
    return (T) this;
  }

  @Schema(
      description = "Second thoroughfare number (only used if the property has a ranged address eg 23-25)")
  @JsonProperty("thoroughfareNumber2")
  public Integer thoroughfareNumber2;

  public Integer thoroughfareNumber2() {
    return getThoroughfareNumber2();
  }

  @SuppressWarnings("unchecked")
  public T thoroughfareNumber2(Integer thoroughfareNumber2) {
    setThoroughfareNumber2(thoroughfareNumber2);
    return (T) this;
  }

  public void thoroughfareNumber2(String thoroughfareNumber2String) {
    if (thoroughfareNumber2String != null) {
      thoroughfareNumber2 = thoroughfareNumber2String.equals("00000") ? null
          : Integer.parseInt(thoroughfareNumber2String);
    }
  }

  @Schema(
      description = "Suffix for the second thoroughfare number. Only relevant is thoroughfareNumber2 is populated")
  @JsonProperty("thoroughfareNumber2Suffix")
  public String thoroughfareNumber2Suffix;

  public String thoroughfareNumber2Suffix() {
    return getThoroughfareNumber2Suffix();
  }

  @SuppressWarnings("unchecked")
  public T thoroughfareNumber2Suffix(String thoroughfareNumber2Suffix) {
    setThoroughfareNumber2Suffix(thoroughfareNumber2Suffix);
    return (T) this;
  }

  @Schema(description = "Type of flat or unit for the address",
      type = "string")
  @JsonProperty("flatUnitType")
  public AddressPAFFlatUnitType flatUnitType;

  public AddressPAFFlatUnitType flatUnitType() {
    return getFlatUnitType();
  }

  @SuppressWarnings("unchecked")
  public T flatUnitType(AddressPAFFlatUnitType flatUnitType) {
    setFlatUnitType(flatUnitType);
    return (T) this;
  }

  @Schema(description = "Unit number (including suffix, if applicable)")
  @JsonProperty("flatUnitNumber")
  public String flatUnitNumber;

  public String flatUnitNumber() {
    return getFlatUnitNumber();
  }

  @SuppressWarnings("unchecked")
  public T flatUnitNumber(String flatUnitNumber) {
    setFlatUnitNumber(flatUnitNumber);
    return (T) this;
  }

  @Schema(description = "Type of floor or level for the address",
      type = "string")
  @JsonProperty("floorLevelType")
  public AddressPAFFloorLevelType floorLevelType;

  public AddressPAFFloorLevelType floorLevelType() {
    return getFloorLevelType();
  }

  @SuppressWarnings("unchecked")
  public T floorLevelType(AddressPAFFloorLevelType floorLevelType) {
    setFloorLevelType(floorLevelType);
    return (T) this;
  }

  @Schema(description = "Floor or level number (including alpha characters)")
  @JsonProperty("floorLevelNumber")
  public String floorLevelNumber;

  public String floorLevelNumber() {
    return getFloorLevelNumber();
  }

  @SuppressWarnings("unchecked")
  public T floorLevelNumber(String floorLevelNumber) {
    setFloorLevelNumber(floorLevelNumber);
    return (T) this;
  }

  @Schema(description = "Allotment number for the address")
  @JsonProperty("lotNumber")
  public String lotNumber;

  public String lotNumber() {
    return getLotNumber();
  }

  @SuppressWarnings("unchecked")
  public T lotNumber(String lotNumber) {
    setLotNumber(lotNumber);
    return (T) this;
  }

  @Schema(description = "Building/Property name 1")
  @JsonProperty("buildingName1")
  public String buildingName1;

  public String buildingName1() {
    return getBuildingName1();
  }

  @SuppressWarnings("unchecked")
  public T buildingName1(String buildingName1) {
    setBuildingName1(buildingName1);
    return (T) this;
  }

  @Schema(description = "Building/Property name 2")
  @JsonProperty("buildingName2")
  public String buildingName2;

  public String buildingName2() {
    return getBuildingName2();
  }

  @SuppressWarnings("unchecked")
  public T buildingName2(String buildingName2) {
    setBuildingName2(buildingName2);
    return (T) this;
  }

  @Schema(description = "The name of the street")
  @JsonProperty("streetName")
  public String streetName;

  public String streetName() {
    return getStreetName();
  }

  @SuppressWarnings("unchecked")
  public T streetName(String streetName) {
    setStreetName(streetName);
    return (T) this;
  }

  @Schema(
      description = "The street type. Valid enumeration defined by Australia Post PAF code file",
      type = "string")
  @JsonProperty("streetType")
  public AddressPAFStreetType streetType;

  public AddressPAFStreetType streetType() {
    return getStreetType();
  }

  @SuppressWarnings("unchecked")
  public T streetType(AddressPAFStreetType streetType) {
    setStreetType(streetType);
    return (T) this;
  }

  @Schema(
      description = "The street type suffix. Valid enumeration defined by Australia Post PAF code file",
      type = "string")
  @JsonProperty("streetSuffix")
  public AddressPAFStreetSuffix streetSuffix;

  public AddressPAFStreetSuffix streetSuffix() {
    return getStreetSuffix();
  }

  @SuppressWarnings("unchecked")
  public T streetSuffix(AddressPAFStreetSuffix streetSuffix) {
    setStreetSuffix(streetSuffix);
    return (T) this;
  }

  @Schema(
      description = "Postal delivery type. (eg. PO BOX). Valid enumeration defined by Australia Post PAF code file",
      type = "string")
  @JsonProperty("postalDeliveryType")
  public AddressPAFPostalDeliveryType postalDeliveryType;

  public AddressPAFPostalDeliveryType postalDeliveryType() {
    return getPostalDeliveryType();
  }

  @SuppressWarnings("unchecked")
  public T postalDeliveryType(AddressPAFPostalDeliveryType postalDeliveryType) {
    setPostalDeliveryType(postalDeliveryType);
    return (T) this;
  }

  @Schema(
      description = "Postal delivery number if the address is a postal delivery type")
  @JsonProperty("postalDeliveryNumber")
  public Integer postalDeliveryNumber;

  public Integer postalDeliveryNumber() {
    return getPostalDeliveryNumber();
  }

  @SuppressWarnings("unchecked")
  public T postalDeliveryNumber(Integer postalDeliveryNumber) {
    setPostalDeliveryNumber(postalDeliveryNumber);
    return (T) this;
  }

  public void postalDeliveryNumber(String postalDeliveryNumberString) {
    if (postalDeliveryNumberString != null) {
      postalDeliveryNumber = postalDeliveryNumberString.equals("00000") ? null
          : Integer.parseInt(postalDeliveryNumberString);
    }
  }

  @Schema(
      description = "Postal delivery number prefix related to the postal delivery number")
  @JsonProperty("postalDeliveryNumberPrefix")
  public String postalDeliveryNumberPrefix;

  public String postalDeliveryNumberPrefix() {
    return getPostalDeliveryNumberPrefix();
  }

  @SuppressWarnings("unchecked")
  public T postalDeliveryNumberPrefix(String postalDeliveryNumberPrefix) {
    setPostalDeliveryNumberPrefix(postalDeliveryNumberPrefix);
    return (T) this;
  }

  @Schema(
      description = "Postal delivery number suffix related to the postal delivery number")
  @JsonProperty("postalDeliveryNumberSuffix")
  public String postalDeliveryNumberSuffix;

  public String postalDeliveryNumberSuffix() {
    return getPostalDeliveryNumberSuffix();
  }

  @SuppressWarnings("unchecked")
  public T postalDeliveryNumberSuffix(String postalDeliveryNumberSuffix) {
    setPostalDeliveryNumberSuffix(postalDeliveryNumberSuffix);
    return (T) this;
  }

  @Schema(description = "Full name of locality", required = true)
  @JsonProperty("localityName")
  @NotNull
  @NonNull
  public String localityName;

  public String localityName() {
    return getLocalityName();
  }

  @SuppressWarnings("unchecked")
  public T localityName(String localityName) {
    setLocalityName(localityName);
    return (T) this;
  }

  @Schema(description = "Postcode for the locality", required = true)
  @JsonProperty("postcode")
  @NotNull
  @NonNull
  public String postcode;

  public String postcode() {
    return getPostcode();
  }

  @SuppressWarnings("unchecked")
  public T postcode(String postcode) {
    setPostcode(postcode);
    return (T) this;
  }

  @Schema(
      description = "State in which the address belongs. Valid enumeration defined by Australia Post PAF code file",
      required = true, type = "string")
  @JsonProperty("state")
  @NotNull
  @NonNull
  public AddressPAFStateType state;

  public AddressPAFStateType state() {
    return getState();
  }

  @SuppressWarnings("unchecked")
  public T state(AddressPAFStateType state) {
    setState(state);
    return (T) this;
  }
}
