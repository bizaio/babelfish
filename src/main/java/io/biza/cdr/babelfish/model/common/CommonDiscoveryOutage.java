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

import java.time.LocalDateTime;
import java.time.Period;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Outage Detail")
public abstract class CommonDiscoveryOutage {

  @BabelFishModelProperty(description = "Date and time that the outage is scheduled to begin",
      required = true)
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("outageTime")
  @NotNull
  @NonNull
  public LocalDateTime outageTime;

  @BabelFishModelProperty(
      description = "Planned duration of the outage. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("duration")
  @NotNull
  @NonNull
  public Period duration;

  @BabelFishModelProperty(
      description = "Flag that indicates, if present and set to true, that the outage is only partial meaning that only a subset of normally available end points will be affected by the outage")
  @JsonProperty("isPartial")
  @NotNull
  @NonNull
  public Boolean isPartial;

  @BabelFishModelProperty(
      description = "Provides an explanation of the current outage that can be displayed to an end customer",
      required = true)
  @JsonProperty("explanation")
  @NotNull
  @NonNull
  public String explanation;

}
