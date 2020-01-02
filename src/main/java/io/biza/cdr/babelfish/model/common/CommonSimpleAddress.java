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
package io.biza.cdr.babelfish.model.common;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Simple Address Detail")
public class CommonSimpleAddress {

    @BabelFishModelProperty(
        description =  "Name of the individual or business formatted for inclusion in an address used for physical mail"
    )
    String mailingName;

    @BabelFishModelProperty(
        description =  "First line of the standard address object",
        required = true
    )
    @NonNull
    @NotNull
    String addressLine1;

    @BabelFishModelProperty(
        description =  "Second line of the standard address object"
    )
    String addressLine2;

    @BabelFishModelProperty(
        description =  "Third line of the standard address object"
    )
    String addressLine3;

    @BabelFishModelProperty(
        description =  "Mandatory for Australian addresses"
    )
    String postcode;

    @BabelFishModelProperty(
        description =  "Name of the city or locality",
        required = true
    )
    @NonNull
    @NotNull
    String city;

    @BabelFishModelProperty(
        description =  "Free text if the country is not Australia. If country is Australia then must be one of the values defined by the [State Type Abbreviation](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf) in the PAF file format. NSW, QLD, VIC, NT, WA, SA, TAS, ACT, AAT",
        required = true
    )
    @NonNull
    @NotNull
    String state;

    @BabelFishModelProperty(
        description =  "A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code. Australia (AUS) is assumed if country is not present."
    )
	@JsonSerialize(converter = LocaleToCountryStringConverter.class)
	@JsonDeserialize(converter = CountryStringToLocaleConverter.class)
    @Builder.Default
    Locale country = Locale.forLanguageTag("en-AU");
    
    @AssertTrue(
            message = "Postcode and State must be correct when Country is defined as Australia (en-AU)")
    private boolean isAustralianFieldChecks() {
        if(country.equals(Locale.forLanguageTag("en-AU"))) {
            return postcode != null && state != null ? true : false;
        }
        
        return true;
    }

}
