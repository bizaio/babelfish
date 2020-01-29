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
package io.biza.babelfish.cdr.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.time.OffsetDateTime;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryOutage;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("CommonDiscoveryOutage V1 Tests")
public class CommonDiscoveryOutageV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonDiscoveryOutage")
  void commonDiscoveryOutage() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE).toString());
  }

  @Test
  @DisplayName("CommonDiscoveryOutage Mandatory Fields")
  void commonDiscoveryOutageMandatoryFields() {
    CommonDiscoveryOutage data = new CommonDiscoveryOutage();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.outageTime(OffsetDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.duration(Duration.ofHours(1));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.explanation("Outage Explanation");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
