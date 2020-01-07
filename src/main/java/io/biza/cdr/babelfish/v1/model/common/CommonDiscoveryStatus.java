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
package io.biza.cdr.babelfish.v1.model.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.CommonDiscoveryStatusType;

@Valid
public class CommonDiscoveryStatus extends
    io.biza.cdr.babelfish.response.container.CommonDiscoveryStatus<CommonDiscoveryStatus> {
  
  @AssertTrue(
      message = "Explanation is MANDATORY if Status is not OK")
  private boolean isExplanationPresent() {
    if(status() == null) { return true; }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status) ? FormatChecker.isNotEmpty(explanation()) : true;
  }

  @AssertTrue(
      message = "Detection Time should be PRESENT when status is PARTIAL_FAILURE or UNAVAILABLE")
  private boolean isDetectionTimePresent() {
    if(status() == null) { return true; }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.PARTIAL_FAILURE,
        CommonDiscoveryStatusType.UNAVAILABLE}).contains(status)
            ? FormatChecker.isDefined(detectionTime())
            : true;
  }
  
  @AssertTrue(
      message = "Detection Time should be ABSENT when status isn't PARTIAL_FAILURE or UNAVAILABLE")
  private boolean isDetectionTimeAbsent() {
    if(status() == null) { return true; }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.PARTIAL_FAILURE,
        CommonDiscoveryStatusType.UNAVAILABLE}).contains(status)
            ? !FormatChecker.isDefined(detectionTime())
            : true;
  }

  @AssertTrue(message = "Resolution Time should be ABSENT when status is OK")
  private boolean isExpectedResolutionTimeAbsent() {
    if(status() == null) { return true; }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status) ? !FormatChecker.isDefined(expectedResolutionTime()) : true;
  }

}
