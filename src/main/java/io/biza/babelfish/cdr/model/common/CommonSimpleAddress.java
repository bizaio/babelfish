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
package io.biza.babelfish.cdr.model.common;

import java.util.Locale;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.Constants;
import io.biza.babelfish.converter.cdr.CountryStringToLocaleConverter;
import io.biza.babelfish.converter.cdr.LocaleToCountryStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Simple Address Detail")
public abstract class CommonSimpleAddress<T> {
  @Schema(
      description = "Name of the individual or business formatted for inclusion in an address used for physical mail")
  @JsonProperty("mailingName")
  String mailingName;

  public String mailingName() {
    return getMailingName();
  }

  @SuppressWarnings("unchecked")
  public T mailingName(String mailingName) {
    setMailingName(mailingName);
    return (T) this;
  }

  @Schema(description = "First line of the standard address object", required = true)
  @JsonProperty("addressLine1")
  String addressLine1;

  public String addressLine1() {
    return getAddressLine1();
  }

  @SuppressWarnings("unchecked")
  public T addressLine1(String addressLine1) {
    setAddressLine1(addressLine1);
    return (T) this;
  }

  @Schema(description = "Second line of the standard address object")
  @JsonProperty("addressLine2")
  String addressLine2;

  public String addressLine2() {
    return getAddressLine2();
  }

  @SuppressWarnings("unchecked")
  public T addressLine2(String addressLine2) {
    setAddressLine2(addressLine2);
    return (T) this;
  }

  @Schema(description = "Third line of the standard address object")
  @JsonProperty("addressLine3")
  String addressLine3;

  public String addressLine3() {
    return getAddressLine3();
  }

  @SuppressWarnings("unchecked")
  public T addressLine3(String addressLine3) {
    setAddressLine3(addressLine3);
    return (T) this;
  }

  @Schema(description = "Mandatory for Australian addresses")
  @JsonProperty("postcode")
  String postcode;

  public String postcode() {
    return getPostcode();
  }

  @SuppressWarnings("unchecked")
  public T postcode(String postcode) {
    setPostcode(postcode);
    return (T) this;
  }

  @Schema(description = "Name of the city or locality", required = true)
  @JsonProperty("city")
  String city;

  public String city() {
    return getCity();
  }

  @SuppressWarnings("unchecked")
  public T city(String city) {
    setCity(city);
    return (T) this;
  }

  @Schema(
      description = "Free text if the country is not Australia. If country is Australia then must be one of the values defined by the [State Type Abbreviation](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf) in the PAF file format. NSW, QLD, VIC, NT, WA, SA, TAS, ACT, AAT",
      required = true)
  @JsonProperty("state")
  String state;

  public String state() {
    return getState();
  }

  @SuppressWarnings("unchecked")
  public T state(String state) {
    setState(state);
    return (T) this;
  }

  @Schema(
      description = "A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code. Australia (AUS) is assumed if country is not present.")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("country")
  Locale country = new Locale(Constants.DEFAULT_LOCALE, "AU");

  public Locale country() {
    return getCountry();
  }

  @SuppressWarnings("unchecked")
  public T country(Locale country) {
    setCountry(country);
    return (T) this;
  }
}
