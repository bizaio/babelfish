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
package io.biza.babelfish.cdr.models.payloads.common;

import java.time.OffsetDateTime;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.enumerations.CommonDiscoveryStatusType;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "A single Discovery Status entry", name = "CommonDiscoveryStatus")
public class CommonDiscoveryStatusV1 {
  @Schema(
      description = "Enumeration with values. OK (implementation is fully functional). PARTIAL_FAILURE (one or more end points are unexpectedly unavailable). UNAVAILABLE (the full implementation is unexpectedly unavailable). SCHEDULED_OUTAGE (an advertised outage is in effect)",
      required = true)
  @JsonProperty("status")
  @NotNull
  @Valid
  CommonDiscoveryStatusType status;

  @Schema(description = "The date and time that this status was last updated by the Data Holder.",
      required = true, format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("updatedTime")
  @NotNull
  @Valid
  OffsetDateTime updateTime;

  @Schema(
      description = "Provides an explanation of the current outage that can be displayed to an end customer. Mandatory if the status property is any value other than OK")
  @JsonProperty("explanation")
  String explanation;

  @Schema(
      description = "The date and time that the current outage was detected. Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("detectionTime")
  OffsetDateTime detectionTime;

  @Schema(
      description = "The date and time that full service is expected to resume (if known). Should not be present if the status property has a value of OK.",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("expectedResolutionTime")
  OffsetDateTime expectedResolutionTime;

  @AssertTrue(message = "Explanation is MANDATORY if Status is not OK")
  private boolean isExplanationPresent() {
    if (status() == null) {
      return true;
    }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status()) ? FormatChecker.isNotEmpty(explanation()) : true;
  }

  @AssertTrue(
      message = "Detection Time should be PRESENT when status is PARTIAL_FAILURE or UNAVAILABLE")
  private boolean isDetectionTimePresent() {
    if (status() == null) {
      return true;
    }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.PARTIAL_FAILURE,
        CommonDiscoveryStatusType.UNAVAILABLE}).contains(status())
            ? FormatChecker.isDefined(detectionTime())
            : true;
  }

  @AssertTrue(
      message = "Detection Time should be ABSENT when status isn't PARTIAL_FAILURE or UNAVAILABLE")
  private boolean isDetectionTimeAbsent() {
    if (status() == null) {
      return true;
    }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {
        CommonDiscoveryStatusType.PARTIAL_FAILURE, CommonDiscoveryStatusType.UNAVAILABLE})
        .contains(status()) ? !FormatChecker.isDefined(detectionTime()) : true;
  }

  @AssertTrue(message = "Resolution Time should be ABSENT when status is OK")
  private boolean isExpectedResolutionTimeAbsent() {
    if (status() == null) {
      return true;
    }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status()) ? !FormatChecker.isDefined(expectedResolutionTime()) : true;
  }

}
