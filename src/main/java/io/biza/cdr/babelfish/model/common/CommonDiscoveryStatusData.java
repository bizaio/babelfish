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
package io.biza.cdr.babelfish.model.common;

import java.time.LocalDateTime;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.enumerations.CommonDiscoveryStatusType;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Common Discovery Status Information")
public class CommonDiscoveryStatusData {

    @BabelFishModelProperty(
        description =  "Enumeration with values. OK (implementation is fully functional). PARTIAL_FAILURE (one or more end points are unexpectedly unavailable). UNAVAILABLE (the full implementation is unexpectedly unavailable). SCHEDULED_OUTAGE (an advertised outage is in effect)",
        required = true
    )
    @NonNull
    @NotNull
    CommonDiscoveryStatusType status;

    @BabelFishModelProperty(
        description =  "Provides an explanation of the current outage that can be displayed to an end customer. Mandatory if the status property is any value other than OK"
    )
    String explanation;

    @BabelFishModelProperty(
        description =  "The date and time that the current outage was detected. Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE"
    )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private LocalDateTime detectionTime;
    
    @AssertTrue(
            message = "Detection Time should only be present if status is PARTIAL_FAILURE or UNAVAILABLE")
    private boolean isDetectionTimePresent() {
        return  Arrays.asList(new CommonDiscoveryStatusType[] { CommonDiscoveryStatusType.PARTIAL_FAILURE, CommonDiscoveryStatusType.UNAVAILABLE }).contains(status)
                        ? FormatChecker.isDefined(detectionTime)
                        : true;
    }

    @BabelFishModelProperty(
        description =  "The date and time that full service is expected to resume (if known). Should not be present if the status property has a value of OK."
    )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private LocalDateTime expectedResolutionTime;
    
    @AssertTrue(
            message = "Resolution Time should only be present if status is not OK")
    private boolean isResolutionTimePresent() {
        return Arrays.asList(new CommonDiscoveryStatusType[] { CommonDiscoveryStatusType.OK }).contains(status)
                        ? ! FormatChecker.isDefined(detectionTime)
                        : true;
    }

    @BabelFishModelProperty(
        description =  "The date and time that this status was last updated by the Data Holder.",
        required = true
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private LocalDateTime updateTime;
}
