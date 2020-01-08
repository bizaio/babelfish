/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.StringToDurationConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.DurationToStringConverter;
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

@BabelFishModel(description = "Outage Detail")
public abstract class CommonDiscoveryOutage<T> {
  @BabelFishModelProperty(description = "Date and time that the outage is scheduled to begin",
      required = true)
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("outageTime")
  @NotNull
  @NonNull
  public LocalDateTime outageTime;

  public LocalDateTime outageTime() {
    return getOutageTime();
  }

  @SuppressWarnings("unchecked")
  public T outageTime(LocalDateTime outageTime) {
    setOutageTime(outageTime);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Planned duration of the outage. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @JsonSerialize(converter = DurationToStringConverter.class)
  @JsonDeserialize(converter = StringToDurationConverter.class)
  @JsonProperty("duration")
  @NotNull
  @NonNull
  public Duration duration;

  public Duration duration() {
    return getDuration();
  }

  @SuppressWarnings("unchecked")
  public T duration(Duration duration) {
    setDuration(duration);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Flag that indicates, if present and set to true, that the outage is only partial meaning that only a subset of normally available end points will be affected by the outage")
  @JsonProperty("isPartial")
  public Boolean isPartial;

  public Boolean isPartial() {
    return getIsPartial();
  }

  @SuppressWarnings("unchecked")
  public T isPartial(Boolean isPartial) {
    setIsPartial(isPartial);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Provides an explanation of the current outage that can be displayed to an end customer",
      required = true)
  @JsonProperty("explanation")
  @NotNull
  @NonNull
  public String explanation;

  public String explanation() {
    return getExplanation();
  }

  @SuppressWarnings("unchecked")
  public T explanation(String explanation) {
    setExplanation(explanation);
    return (T) this;
  }
}
