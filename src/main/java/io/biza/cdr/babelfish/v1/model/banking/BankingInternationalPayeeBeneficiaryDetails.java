/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.banking;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "International Payee Beneficiary Details")
public class BankingInternationalPayeeBeneficiaryDetails {

    @BabelFishModelProperty(
        description =  "Name of the beneficiary"
    )
    String name;

    @BabelFishModelProperty(
        description =  "Country where the beneficiary resides. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
	@JsonSerialize(converter = LocaleToCountryStringConverter.class)
	@JsonDeserialize(converter = CountryStringToLocaleConverter.class)
    Locale country;

    @BabelFishModelProperty(
        description =  "Response message for the payment"
    )
    String message;
}
