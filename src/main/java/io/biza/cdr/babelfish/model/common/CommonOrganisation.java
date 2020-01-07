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
import javax.validation.constraints.NotNull;
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

@BabelFishModel(description = "Organisation Definition in Brief")
public abstract class CommonOrganisation<T> {
  @BabelFishModelProperty(
      description = "The date and time that this record was last updated by the customer. If no update has occurred then this date should reflect the initial creation date for the data")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastUpdateTime")
  public LocalDateTime lastUpdateTime;

  public LocalDateTime lastUpdateTime() {
    return getLastUpdateTime();
  }

  @SuppressWarnings("unchecked")
  public T lastUpdateTime(LocalDateTime lastUpdateTime) {
    setLastUpdateTime(lastUpdateTime);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The first name of the individual providing access on behalf of the organisation. For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("agentFirstName")
  public String agentFirstName;

  public String agentFirstName() {
    return getAgentFirstName();
  }

  @SuppressWarnings("unchecked")
  public T agentFirstName(String agentFirstName) {
    setAgentFirstName(agentFirstName);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The last name of the individual providing access on behalf of the organisation. For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("agentLastName")
  @NonNull
  @NotNull
  @Valid
  public String agentLastName;

  public String agentLastName() {
    return getAgentLastName();
  }

  @SuppressWarnings("unchecked")
  public T agentLastName(String agentLastName) {
    setAgentLastName(agentLastName);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The role of the individual identified as the agent who is providing authorisation.  Expected to be used for display.  Default to “Unspecified” if the role is not known",
      required = true)
  @JsonProperty("agentRole")
  @NotNull
  @NonNull
  @Valid
  public String agentRole = "Unspecified";

  public String agentRole() {
    return getAgentRole();
  }

  @SuppressWarnings("unchecked")
  public T agentRole(String agentRole) {
    setAgentRole(agentRole);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Name of the organisation", required = true)
  @JsonProperty("businessName")
  @NonNull
  @NotNull
  @Valid
  public String businessName;

  public String businessName() {
    return getBusinessName();
  }

  @SuppressWarnings("unchecked")
  public T businessName(String businessName) {
    setBusinessName(businessName);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Legal name, if different to the business name")
  @JsonProperty("legalName")
  public String legalName;

  public String legalName() {
    return getLegalName();
  }

  @SuppressWarnings("unchecked")
  public T legalName(String legalName) {
    setLegalName(legalName);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Short name used for communication, if  different to the business name")
  @JsonProperty("shortName")
  public String shortName;

  public String shortName() {
    return getShortName();
  }

  @SuppressWarnings("unchecked")
  public T shortName(String shortName) {
    setShortName(shortName);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Australian Business Number for the organisation")
  @JsonProperty("abn")
  public String abn;

  public String abn() {
    return getAbn();
  }

  @SuppressWarnings("unchecked")
  public T abn(String abn) {
    setAbn(abn);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Australian Company Number for the organisation. Required only if an ACN is applicable for the organisation type")
  @JsonProperty("acn")
  public String acn;

  public String acn() {
    return getAcn();
  }

  @SuppressWarnings("unchecked")
  public T acn(String acn) {
    setAcn(acn);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "True if registered with the ACNC.  False if not. Absent or null if not confirmed.")
  @JsonProperty("isACNCRegistered")
  public Boolean isACNCRegistered;

  public Boolean isACNCRegistered() {
    return getIsACNCRegistered();
  }

  @SuppressWarnings("unchecked")
  public T isACNCRegistered(Boolean isACNCRegistered) {
    setIsACNCRegistered(isACNCRegistered);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "[ANZSIC (2006)](http://www.abs.gov.au/anzsic) code for the organisation.")
  @JsonProperty("industryCode")
  public String industryCode;

  public String industryCode() {
    return getIndustryCode();
  }

  @SuppressWarnings("unchecked")
  public T industryCode(String industryCode) {
    setIndustryCode(industryCode);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Legal organisation type", required = true)
  @JsonProperty("organisationType")
  @NonNull
  @NotNull
  @Valid
  public CommonOrganisationType organisationType;

  public CommonOrganisationType organisationType() {
    return getOrganisationType();
  }

  @SuppressWarnings("unchecked")
  public T organisationType(CommonOrganisationType organisationType) {
    setOrganisationType(organisationType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Enumeration with values from [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country codes.  Assumed to be AUS if absent")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("registeredCountry")
  public Locale registeredCountry;

  public Locale registeredCountry() {
    return getRegisteredCountry();
  }

  @SuppressWarnings("unchecked")
  public T registeredCountry(Locale registeredCountry) {
    setRegisteredCountry(registeredCountry);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The date the organisation described was established",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("establishmentDate")
  public LocalDate establishmentDate;

  public LocalDate establishmentDate() {
    return getEstablishmentDate();
  }

  @SuppressWarnings("unchecked")
  public T establishmentDate(LocalDate establishmentDate) {
    setEstablishmentDate(establishmentDate);
    return (T) this;
  }
}
