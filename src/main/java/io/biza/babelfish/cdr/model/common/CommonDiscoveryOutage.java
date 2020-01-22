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
package io.biza.babelfish.cdr.model.common;

import java.time.Duration;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.DurationToStringConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.StringToDurationConverter;
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

@Schema(description = "Outage Detail")
public abstract class CommonDiscoveryOutage<T> {
  @Schema(description = "Date and time that the outage is scheduled to begin", required = true,
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("outageTime")
  @NotNull
  OffsetDateTime outageTime;

  public OffsetDateTime outageTime() {
    return getOutageTime();
  }

  @SuppressWarnings("unchecked")
  public T outageTime(OffsetDateTime outageTime) {
    setOutageTime(outageTime);
    return (T) this;
  }

  @Schema(
      description = "Planned duration of the outage. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      required = true)
  @JsonSerialize(converter = DurationToStringConverter.class)
  @JsonDeserialize(converter = StringToDurationConverter.class)
  @JsonProperty("duration")
  @NotNull
  Duration duration;

  public Duration duration() {
    return getDuration();
  }

  @SuppressWarnings("unchecked")
  public T duration(Duration duration) {
    setDuration(duration);
    return (T) this;
  }

  @Schema(
      description = "Flag that indicates, if present and set to true, that the outage is only partial meaning that only a subset of normally available end points will be affected by the outage")
  @JsonProperty("isPartial")
  Boolean isPartial;

  public Boolean isPartial() {
    return getIsPartial();
  }

  @SuppressWarnings("unchecked")
  public T isPartial(Boolean isPartial) {
    setIsPartial(isPartial);
    return (T) this;
  }

  @Schema(
      description = "Provides an explanation of the current outage that can be displayed to an end customer",
      required = true)
  @JsonProperty("explanation")
  @NotNull
  String explanation;

  public String explanation() {
    return getExplanation();
  }

  @SuppressWarnings("unchecked")
  public T explanation(String explanation) {
    setExplanation(explanation);
    return (T) this;
  }
}
