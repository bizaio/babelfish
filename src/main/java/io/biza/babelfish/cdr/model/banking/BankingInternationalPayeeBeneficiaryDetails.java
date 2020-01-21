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
package io.biza.babelfish.cdr.model.banking;

import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.CountryStringToLocaleConverter;
import io.biza.babelfish.cdr.converters.LocaleToCountryStringConverter;
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
@Schema(description = "International Payee Beneficiary Details")
public abstract class BankingInternationalPayeeBeneficiaryDetails<T> {
  @Schema(description = "Name of the beneficiary")
  @JsonProperty("name")
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @Schema(
      description = "Country where the beneficiary resides. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true, type = "string")
  @NonNull
  @NotNull
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("country")
  Locale country;

  public Locale country() {
    return getCountry();
  }

  @SuppressWarnings("unchecked")
  public T country(Locale country) {
    setCountry(country);
    return (T) this;
  }

  @Schema(description = "Response message for the payment")
  @JsonProperty("message")
  String message;

  public String message() {
    return getMessage();
  }

  @SuppressWarnings("unchecked")
  public T message(String message) {
    setMessage(message);
    return (T) this;
  }
}
