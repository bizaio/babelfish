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
package io.biza.cdr.babelfish.response.container;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.CommonDiscoveryStatusType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description = "Common Discovery Status Information")
public interface ResponseCommonDiscoveryStatusData {

  @BabelFishModelProperty(
      description = "Enumeration with values. OK (implementation is fully functional). PARTIAL_FAILURE (one or more end points are unexpectedly unavailable). UNAVAILABLE (the full implementation is unexpectedly unavailable). SCHEDULED_OUTAGE (an advertised outage is in effect)",
      required = true)
  @JsonGetter("status")
  public CommonDiscoveryStatusType getStatus();

  @JsonSetter("status")
  public void setStatus(CommonDiscoveryStatusType status);

  default public ResponseCommonDiscoveryStatusData status(CommonDiscoveryStatusType status) {
    setStatus(status);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time that this status was last updated by the Data Holder.",
      required = true)
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("updatedTime")
  public LocalDateTime getUpdateTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("updatedTime")
  public void setUpdateTime(LocalDateTime updateTime);

  default public ResponseCommonDiscoveryStatusData updateTime(LocalDateTime updateTime) {
    setUpdateTime(updateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "Provides an explanation of the current outage that can be displayed to an end customer. Mandatory if the status property is any value other than OK")
  @JsonGetter("explanation")
  public String getExplanation();

  @JsonSetter("explanation")
  public void setExplanation(String explanation);

  default public ResponseCommonDiscoveryStatusData explanation(String explanation) {
    setExplanation(explanation);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time that the current outage was detected. Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("detectionTime")
  public LocalDateTime getDetectionTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("detectionTime")
  public void setDetectionTime(LocalDateTime detectionTime);

  default public ResponseCommonDiscoveryStatusData detectionTime(LocalDateTime detectionTime) {
    setDetectionTime(detectionTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time that full service is expected to resume (if known). Should not be present if the status property has a value of OK.")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("expectedResolutionTime")
  public LocalDateTime getExpectedResolutionTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("expectedResolutionTime")
  public void setExpectedResolutionTime(LocalDateTime expectedResolutionTime);

  default public ResponseCommonDiscoveryStatusData expectedResolutionTime(
      LocalDateTime expectedResolutionTime) {
    setExpectedResolutionTime(expectedResolutionTime);
    return this;
  }

}

