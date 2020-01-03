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
import java.time.LocalDateTime;
import java.time.Period;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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
import lombok.experimental.Accessors;

@BabelFishModel(description = "Outage Detail")
public interface CommonDiscoveryOutage {

  @BabelFishModelProperty(description = "Date and time that the outage is scheduled to begin",
      required = true)
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("outageTime")
  public LocalDateTime getOutageTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("outageTime")
  public void setOutageTime(LocalDateTime outageTime);

  default public CommonDiscoveryOutage outageTime(LocalDateTime outageTime) {
    setOutageTime(outageTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "Planned duration of the outage. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonGetter("duration")
  public Period getDuration();

  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonSetter("duration")
  public void setDuration(Period duration);

  default public CommonDiscoveryOutage duration(Period duration) {
    setDuration(duration);
    return this;
  }

  @BabelFishModelProperty(
      description = "Flag that indicates, if present and set to true, that the outage is only partial meaning that only a subset of normally available end points will be affected by the outage")
  @JsonGetter("isPartial")
  public Boolean isPartial();

  @JsonSetter("isPartial")
  public void setIsPartial(Boolean isPartial);

  default public CommonDiscoveryOutage isPartial(Boolean isPartial) {
    setIsPartial(isPartial);
    return this;
  }

  @BabelFishModelProperty(
      description = "Provides an explanation of the current outage that can be displayed to an end customer",
      required = true)
  @JsonGetter("explanation")
  public String getExplanation();

  @JsonSetter("explanation")
  public void setExplanation(String explanation);

  default public CommonDiscoveryOutage explanation(String explanation) {
    setExplanation(explanation);
    return this;
  }

}
