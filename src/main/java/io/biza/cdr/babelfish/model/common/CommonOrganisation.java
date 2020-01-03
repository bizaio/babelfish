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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.AccessLevel;

@BabelFishModel(description = "Organisation Definition in Brief")
public interface CommonOrganisation {
  @BabelFishModelProperty(
      description = "The date and time that this record was last updated by the customer. If no update has occurred then this date should reflect the initial creation date for the data")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)  
  @JsonGetter("lastUpdateTime")
  public LocalDateTime getLastUpdateTime();
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("lastUpdateTime")
  public void setLastUpdateTime(LocalDateTime lastUpdateTime);

  default public CommonOrganisation lastUpdateTime(LocalDateTime lastUpdateTime) {
    setLastUpdateTime(lastUpdateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "The first name of the individual providing access on behalf of the organisation. For people with single names this field need not be present.  The single name should be in the lastName field")  
  @JsonGetter("agentFirstName")
  public String getAgentFirstName();
  @JsonSetter("agentFirstName")
  public void setAgentFirstName(String agentFirstName);

  default public CommonOrganisation agentFirstName(String agentFirstName) {
    setAgentFirstName(agentFirstName);
    return this;
  }

  @BabelFishModelProperty(
      description = "The last name of the individual providing access on behalf of the organisation. For people with single names the single name should be in this field",
      required = true)
  @JsonGetter("agentLastName")
  public String getAgentLastName();
  @JsonSetter("agentLastName")
  public void setAgentLastName(String agentLastName);

  default public CommonOrganisation agentLastName(String agentLastName) {
    setAgentLastName(agentLastName);
    return this;
  }

  @BabelFishModelProperty(
      description = "The role of the individual identified as the agent who is providing authorisation.  Expected to be used for display.  Default to “Unspecified” if the role is not known",
      required = true)
  @JsonGetter("agentRole")  
  public String getAgentRole();

  @JsonSetter("agentRole")
  public void setAgentRole(String agentRole);

  default public CommonOrganisation agentRole(String agentRole) {
    setAgentRole(agentRole);
    return this;
  }

  @BabelFishModelProperty(description = "Name of the organisation", required = true)
  @JsonGetter("businessName")  
  public String getBusinessName();
  @JsonSetter("businessName")  
  public void setBusinessName(String businessName);

  default public CommonOrganisation businessName(String businessName) {
    setBusinessName(businessName);
    return this;
  }

  @BabelFishModelProperty(description = "Legal name, if different to the business name")
  @JsonGetter("legalName")  
  public String getLegalName();
  @JsonSetter("legalName")  
  public void setLegalName(String legalName);

  default public CommonOrganisation legalName(String legalName) {
    setLegalName(legalName);
    return this;
  }

  @BabelFishModelProperty(
      description = "Short name used for communication, if  different to the business name")
  @JsonGetter("shortName")  
  public String getShortName();
  @JsonSetter("shortName")  
  public void setShortName(String shortName);

  default public CommonOrganisation shortName(String shortName) {
    setShortName(shortName);
    return this;
  }

  @BabelFishModelProperty(description = "Australian Business Number for the organisation")
  @JsonGetter("abn")  
  public String getAbn();
  @JsonSetter("abn")  
  public void setAbn(String abn);

  default public CommonOrganisation abn(String abn) {
    setAbn(abn);
    return this;
  }

  @BabelFishModelProperty(
      description = "Australian Company Number for the organisation. Required only if an ACN is applicable for the organisation type")  
  @JsonGetter("acn")
  public String getAcn();
  @JsonSetter("acn")
  public void setAcn(String acn);

  default public CommonOrganisation acn(String acn) {
    setAcn(acn);
    return this;
  }

  @BabelFishModelProperty(
      description = "True if registered with the ACNC.  False if not. Absent or null if not confirmed.")  
  @JsonGetter("isACNCRegistered")
  public Boolean isACNCRegistered();
  @JsonSetter("isACNCRegistered")
  public void setIsACNCRegistered(Boolean isACNCRegistered);

  default public CommonOrganisation isACNCRegistered(Boolean isACNCRegistered) {
    setIsACNCRegistered(isACNCRegistered);
    return this;
  }

  @BabelFishModelProperty(
      description = "[ANZSIC (2006)](http://www.abs.gov.au/anzsic) code for the organisation.")
  @JsonGetter("industryCode")
  public String getIndustryCode();
  @JsonSetter("industryCode")
  public String setIndustryCode(String industryCode);

  default public CommonOrganisation industryCode(String industryCode) {
    setIndustryCode(industryCode);
    return this;
  }

  @BabelFishModelProperty(description = "Legal organisation type", required = true)
  @JsonGetter("organisationType")  
  public CommonOrganisationType getOrganisationType();
  @JsonSetter("organisationType")  
  public void setOrganisationType(CommonOrganisationType organisationType);

  default public CommonOrganisation organisationType(CommonOrganisationType organisationType) {
    setOrganisationType(organisationType);
    return this;
  }

  @BabelFishModelProperty(
      description = "Enumeration with values from [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country codes.  Assumed to be AUS if absent")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)  
  @JsonGetter("registeredCountry")  
  public Locale getRegisteredCountry();
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonSetter("registeredCountry")  
  public void setRegisteredCountry(Locale registeredCountry);

  default public CommonOrganisation registeredCountry(Locale registeredCountry) {
    setRegisteredCountry(registeredCountry);
    return this;
  }

  @BabelFishModelProperty(description = "The date the organisation described was established",
      dataType = "java.lang.String")  
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("establishmentDate")
  public LocalDate getEstablishmentDate();
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonSetter("establishmentDate")
  public void setEstablishmentDate(LocalDate establishmentDate);

  default public CommonOrganisation establishmentDate(LocalDate establishmentDate) {
    setEstablishmentDate(establishmentDate);
    return this;
  }
}
