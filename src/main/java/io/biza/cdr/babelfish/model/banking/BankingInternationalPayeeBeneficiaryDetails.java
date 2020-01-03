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
package io.biza.cdr.babelfish.model.banking;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.LocaleToCountryStringConverter;
import io.biza.cdr.babelfish.converters.CountryStringToLocaleConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "International Payee Beneficiary Details")
public interface BankingInternationalPayeeBeneficiaryDetails {

  @BabelFishModelProperty(description = "Name of the beneficiary")
  @JsonGetter("name")
  public String getName();

  @JsonSetter("name")
  public void setName(String name);

  public default BankingInternationalPayeeBeneficiaryDetails name(String name) {
    setName(name);
    return this;
  }

  @BabelFishModelProperty(
      description = "Country where the beneficiary resides. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true, dataType = "java.lang.String")
  @JsonGetter("country")
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  public String getCountry();

  @JsonSetter("country")
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  public void setCountry(@NotNull String country);

  public default BankingInternationalPayeeBeneficiaryDetails country(@NotNull String country) {
    setCountry(country);
    return this;
  }

  @BabelFishModelProperty(description = "Response message for the payment")
  @JsonGetter("message")
  public String getMessage();

  @JsonSetter("message")
  public void setMessage(String message);

  public default BankingInternationalPayeeBeneficiaryDetails message(String message) {
    setMessage(message);
    return this;
  }
}
