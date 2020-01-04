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

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.CommonDiscoveryStatusType;
import lombok.NonNull;

@BabelFishModel(description = "Common Discovery Status Information")
public abstract class ResponseCommonDiscoveryStatusData {
  @BabelFishModelProperty(
      description = "Enumeration with values. OK (implementation is fully functional). PARTIAL_FAILURE (one or more end points are unexpectedly unavailable). UNAVAILABLE (the full implementation is unexpectedly unavailable). SCHEDULED_OUTAGE (an advertised outage is in effect)",
      required = true)
  @JsonProperty("status")
  @NotNull
  @NonNull
  public CommonDiscoveryStatusType status;

  @BabelFishModelProperty(
      description = "The date and time that this status was last updated by the Data Holder.",
      required = true)
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("updatedTime")
  public LocalDateTime updateTime;

  @BabelFishModelProperty(
      description = "Provides an explanation of the current outage that can be displayed to an end customer. Mandatory if the status property is any value other than OK")
  @JsonProperty("explanation")
  public String explanation;

  @BabelFishModelProperty(
      description = "The date and time that the current outage was detected. Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("detectionTime")
  public LocalDateTime detectionTime;

  @BabelFishModelProperty(
      description = "The date and time that full service is expected to resume (if known). Should not be present if the status property has a value of OK.")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("expectedResolutionTime")
  public LocalDateTime expectedResolutionTime;
}
