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

import java.time.LocalDateTime;
import java.time.Period;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
public class CommonDiscoveryOutage extends io.biza.cdr.babelfish.model.common.CommonDiscoveryOutage {

  public CommonDiscoveryOutage(@NonNull LocalDateTime outageTime, @NonNull Period duration,
      @NonNull String explanation) {
    super(outageTime, duration, explanation);
  }

}
