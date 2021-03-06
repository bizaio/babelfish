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
package io.biza.babelfish.cdr.tests.v1.model.response;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryOutagesListV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("ResponseCommonDiscoveryOutagesList V1 Tests")
public class ResponseCommonDiscoveryOutagesListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonDiscoveryOutagesList")
  void discoveryOutagesList() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST)
            .toString());
  }

  @Test
  @DisplayName("ResponseCommonDiscoveryOutagesList Mandatory Fields")
  void discoveryOutagesListMandatoryFields() {
    ResponseCommonDiscoveryOutagesListV1 data = new ResponseCommonDiscoveryOutagesListV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.links(ModelConstants.DEFAULT_LINKS.build());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.meta(ModelConstants.DEFAULT_META.build());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.data(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA.build());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
