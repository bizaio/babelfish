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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import lombok.AccessLevel;

@Valid
public class CommonOrganisation extends io.biza.cdr.babelfish.model.common.CommonOrganisation {

  public CommonOrganisation(@NonNull String agentLastName, @NonNull String agentRole,
      @NonNull String businessName, @NonNull CommonOrganisationType organisationType) {
    super(agentLastName, agentRole, businessName, organisationType);
  }

}
