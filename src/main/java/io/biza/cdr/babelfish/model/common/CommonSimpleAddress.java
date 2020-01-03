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
import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocaleToCountryStringConverter;
import io.biza.cdr.babelfish.converters.CountryStringToLocaleConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
@BabelFishModel(description = "Simple Address Detail")
public interface CommonSimpleAddress {

  @BabelFishModelProperty(
      description = "Name of the individual or business formatted for inclusion in an address used for physical mail")
  @JsonGetter("mailingName")
  public String getMailingName();

  @JsonSetter("mailingName")
  public void setMailingName(String mailingName);

  public default CommonSimpleAddress mailingName(String mailingName) {
    setMailingName(mailingName);
    return this;
  }

  @BabelFishModelProperty(description = "First line of the standard address object",
      required = true)
  @JsonGetter("addressLine1")
  public String getAddressLine1();

  @JsonSetter("addressLine1")
  public void setAddressLine1(@NotNull String addressLine1);

  public default CommonSimpleAddress addressLine1(@NotNull String addressLine1) {
    setAddressLine1(addressLine1);
    return this;
  }

  @BabelFishModelProperty(description = "Second line of the standard address object")
  @JsonGetter("addressLine2")
  public String getAddressLine2();

  @JsonSetter("addressLine2")
  public void setAddressLine2(@NotNull String addressLine2);

  public default CommonSimpleAddress addressLine2(@NotNull String addressLine2) {
    setAddressLine2(addressLine2);
    return this;
  }

  @BabelFishModelProperty(description = "Third line of the standard address object")
  @JsonGetter("addressLine3")
  public String getAddressLine3();

  @JsonSetter("addressLine3")
  public void setAddressLine3(@NotNull String addressLine3);

  public default CommonSimpleAddress addressLine3(@NotNull String addressLine3) {
    setAddressLine3(addressLine3);
    return this;
  }

  @BabelFishModelProperty(description = "Mandatory for Australian addresses")
  @JsonGetter("postcode")
  public String getPostcode();

  @JsonSetter("postcode")
  public void setPostcode(@NotNull String postcode);

  public default CommonSimpleAddress postcode(@NotNull String postcode) {
    setPostcode(postcode);
    return this;
  }

  @BabelFishModelProperty(description = "Name of the city or locality", required = true)
  @JsonGetter("city")
  public String getCity();

  @JsonSetter("city")
  public void setCity(@NotNull String city);

  public default CommonSimpleAddress city(@NotNull String city) {
    setCity(city);
    return this;
  }

  @BabelFishModelProperty(
      description = "Free text if the country is not Australia. If country is Australia then must be one of the values defined by the [State Type Abbreviation](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf) in the PAF file format. NSW, QLD, VIC, NT, WA, SA, TAS, ACT, AAT",
      required = true)
  @JsonGetter("state")
  public String getState();

  @JsonSetter("state")
  public void setState(@NotNull String state);

  public default CommonSimpleAddress state(@NotNull String state) {
    setState(state);
    return this;
  }

  @BabelFishModelProperty(
      description = "A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code. Australia (AUS) is assumed if country is not present.")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonGetter("country")
  public Locale getCountry();

  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonSetter("country")
  public void setCountry(Locale country);

  public default CommonSimpleAddress country(Locale country) {
    setCountry(country);
    return this;
  }
}
