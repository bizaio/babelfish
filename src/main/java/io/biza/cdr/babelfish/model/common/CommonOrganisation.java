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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.LocaleToCountryStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.CountryStringToLocaleConverter;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.CommonOrganisationType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Organisation Definition in Brief")
public abstract class CommonOrganisation {
  @BabelFishModelProperty(
      description = "The date and time that this record was last updated by the customer. If no update has occurred then this date should reflect the initial creation date for the data")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastUpdateTime")
  public LocalDateTime lastUpdateTime;

  @BabelFishModelProperty(
      description = "The first name of the individual providing access on behalf of the organisation. For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("agentFirstName")
  public String agentFirstName;

  @BabelFishModelProperty(
      description = "The last name of the individual providing access on behalf of the organisation. For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("agentLastName")
  public String agentLastName;

  @BabelFishModelProperty(
      description = "The role of the individual identified as the agent who is providing authorisation.  Expected to be used for display.  Default to “Unspecified” if the role is not known",
      required = true)
  @JsonProperty("agentRole")
  public String agentRole;

  @BabelFishModelProperty(description = "Name of the organisation", required = true)
  @JsonProperty("businessName")
  public String businessName;

  @BabelFishModelProperty(description = "Legal name, if different to the business name")
  @JsonProperty("legalName")
  public String legalName;

  @BabelFishModelProperty(
      description = "Short name used for communication, if  different to the business name")
  @JsonProperty("shortName")
  public String shortName;

  @BabelFishModelProperty(description = "Australian Business Number for the organisation")
  @JsonProperty("abn")
  public String abn;

  @BabelFishModelProperty(
      description = "Australian Company Number for the organisation. Required only if an ACN is applicable for the organisation type")
  @JsonProperty("acn")
  public String acn;

  @BabelFishModelProperty(
      description = "True if registered with the ACNC.  False if not. Absent or null if not confirmed.")
  @JsonProperty("isACNCRegistered")
  public Boolean isACNCRegistered;

  @BabelFishModelProperty(
      description = "[ANZSIC (2006)](http://www.abs.gov.au/anzsic) code for the organisation.")
  @JsonProperty("industryCode")
  public String industryCode;

  @BabelFishModelProperty(description = "Legal organisation type", required = true)
  @JsonProperty("organisationType")
  public CommonOrganisationType organisationType;

  @BabelFishModelProperty(
      description = "Enumeration with values from [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country codes.  Assumed to be AUS if absent")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("registeredCountry")
  public Locale registeredCountry;

  @BabelFishModelProperty(description = "The date the organisation described was established",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("establishmentDate")
  public LocalDate establishmentDate;
}
