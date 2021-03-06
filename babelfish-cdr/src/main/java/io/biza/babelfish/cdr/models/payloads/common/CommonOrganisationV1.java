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
package io.biza.babelfish.cdr.models.payloads.common;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.cdr.enumerations.CommonOrganisationType;
import io.biza.babelfish.common.jackson.CountryStringToLocaleConverter;
import io.biza.babelfish.common.jackson.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.LocalDateToStringConverter;
import io.biza.babelfish.common.jackson.LocaleToCountryStringConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.common.jackson.StringToLocalDateConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Organisation Definition in Brief", name = "CommonOrganisationV1")
public class CommonOrganisationV1
    extends io.biza.babelfish.cdr.abstracts.payloads.common.CommonOrganisationV1 {
  @Schema(
      description = "The date and time that this record was last updated by the customer. If no update has occurred then this date should reflect the initial creation date for the data",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastUpdateTime")
  OffsetDateTime lastUpdateTime;

  @Schema(
      description = "The first name of the individual providing access on behalf of the organisation. For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("agentFirstName")
  String agentFirstName;

  @Schema(
      description = "The last name of the individual providing access on behalf of the organisation. For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("agentLastName")
  @NotEmpty(
      message = "Last Name should have a value, if the agent has only one name it should be placed in this field")
  @Valid
  String agentLastName;

  @Schema(
      description = "The role of the individual identified as the agent who is providing authorisation.  Expected to be used for display.  Default to “Unspecified” if the role is not known",
      required = true)
  @JsonProperty(value = "agentRole", defaultValue = "Unspecified")
  @NotEmpty(message = "Should be populated with a role or Unspecified if not available")
  @Valid
  @Builder.Default
  String agentRole = "Unspecified";

  @Schema(description = "Name of the organisation", required = true)
  @JsonProperty("businessName")
  @NotEmpty(message = "Business Name must be populated with the name of the organisation")
  @Valid
  String businessName;

  @Schema(description = "Legal name, if different to the business name")
  @JsonProperty("legalName")
  String legalName;

  @Schema(description = "Short name used for communication, if  different to the business name")
  @JsonProperty("shortName")
  String shortName;

  @Schema(description = "Australian Business Number for the organisation")
  @JsonProperty("abn")
  String abn;

  @Schema(
      description = "Australian Company Number for the organisation. Required only if an ACN is applicable for the organisation type")
  @JsonProperty("acn")
  String acn;

  @Schema(
      description = "True if registered with the ACNC.  False if not. Absent or null if not confirmed.")
  @JsonProperty("isACNCRegistered")
  Boolean isACNCRegistered;

  @Schema(description = "[ANZSIC (2006)](http://www.abs.gov.au/anzsic) code for the organisation.")
  @JsonProperty("industryCode")
  String industryCode;

  @Schema(description = "Legal organisation type", required = true)
  @JsonProperty("organisationType")
  @NotNull
  @Valid
  CommonOrganisationType organisationType;

  @Schema(
      description = "Enumeration with values from [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country codes.  Assumed to be AUS if absent")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("registeredCountry")
  Locale registeredCountry;

  @Schema(description = "The date the organisation described was established", type = "string")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("establishmentDate")
  LocalDate establishmentDate;

}
