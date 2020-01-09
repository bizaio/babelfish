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
package io.biza.cdr.babelfish.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.enumerations.CommonDiscoveryStatusType;
import io.biza.cdr.babelfish.v1.model.common.CommonDiscoveryStatus;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("CommonDiscoveryStatus V1 Tests")
public class CommonDiscoveryStatusV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonDiscoveryStatus")
  void commonDiscoveryStatus() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_STATUS).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_STATUS).toString());
  }

  @Test
  @DisplayName("CommonDiscoveryStatus Mandatory Fields OK Status")
  void commonDiscoveryStatusMandatoryFieldsOk() {
    CommonDiscoveryStatus data = new CommonDiscoveryStatus();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(CommonDiscoveryStatusType.OK);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.updateTime(LocalDateTime.now());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE
    data.detectionTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.detectionTime(null);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Should not be present if the status property has a value of OK.
    data.expectedResolutionTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.expectedResolutionTime(null);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonDiscoveryStatus Mandatory Fields PARTIAL_FAILURE Status")
  void commonDiscoveryStatusMandatoryFieldsPartialFailure() {
    CommonDiscoveryStatus data = new CommonDiscoveryStatus();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(CommonDiscoveryStatusType.PARTIAL_FAILURE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.explanation("Explanation for outage");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.updateTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.detectionTime(LocalDateTime.now());
    // Should be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Expected Resolution Time is allowed and still be valid
    data.expectedResolutionTime(LocalDateTime.now());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonDiscoveryStatus Mandatory Fields UNAVAILABLE Status")
  void commonDiscoveryStatusMandatoryFieldsUnavailable() {
    CommonDiscoveryStatus data = new CommonDiscoveryStatus();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(CommonDiscoveryStatusType.UNAVAILABLE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.explanation("Explanation for outage");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.updateTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    // Should be a valid payload now
    data.detectionTime(LocalDateTime.now());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    // Expected Resolution Time is allowed and still valid
    data.expectedResolutionTime(LocalDateTime.now());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonDiscoveryStatus Mandatory Fields SCHEDULED_OUTAGE Status")
  void commonDiscoveryStatusMandatoryFieldsScheduledOutage() {
    CommonDiscoveryStatus data = new CommonDiscoveryStatus();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(CommonDiscoveryStatusType.SCHEDULED_OUTAGE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.updateTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.explanation("Explanation for outage");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.expectedResolutionTime(LocalDateTime.now());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Should only be present if the status property is PARTIAL_FAILURE or UNAVAILABLE
    data.detectionTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.detectionTime(null);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
