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
package io.biza.cdr.babelfish.v1.model.common;

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

@Valid
public class CommonSimpleAddress extends io.biza.cdr.babelfish.model.common.CommonSimpleAddress {
    
    public CommonSimpleAddress(@NonNull String addressLine1, @NonNull String city,
      @NonNull String state) {
    super(addressLine1, city, state);
  }

    @AssertTrue(
            message = "Postcode and State must be correct when Country is defined as Australia (en-AU)")
    private boolean isAustralianFieldChecks() {
        if(country.equals(Locale.forLanguageTag("en-AU"))) {
            return postcode != null && state != null ? true : false;
        }
        return true;
    }

}
