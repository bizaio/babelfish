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
public class CommonDiscoveryStatusData extends
    io.biza.cdr.babelfish.response.container.ResponseCommonDiscoveryStatusData<CommonDiscoveryStatusData> {
  @AssertTrue(
      message = "Detection Time should only be present if status is PARTIAL_FAILURE or UNAVAILABLE")
  private boolean isDetectionTimePresent() {
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.PARTIAL_FAILURE,
        CommonDiscoveryStatusType.UNAVAILABLE}).contains(status)
            ? FormatChecker.isDefined(detectionTime)
            : true;
  }

  @AssertTrue(message = "Resolution Time should only be present if status is not OK")
  private boolean isResolutionTimePresent() {
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status) ? !FormatChecker.isDefined(detectionTime) : true;
  }
}
