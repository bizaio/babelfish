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
package io.biza.babelfish.cdr.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.v1.model.CDRResponse;
import io.biza.babelfish.cdr.v1.model.common.Links;
import io.biza.babelfish.cdr.v1.model.common.Meta;

@DisplayName("CDR Response V1 Tests")
public class CDRResponseV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CDR Response V1")
  void createValidCDRResponse() {
    CDRResponse myResponse =
        new CDRResponse().links(new Links().self(URI.create("http://localhost/"))).meta(new Meta());
    assertTrue(validator.validate(myResponse).isEmpty());
  }

  @Test
  @DisplayName("Create CDR Response V1 with Missing Links and Meta")
  void createCDRResponseWithMissingLinksAndMeta() {
    CDRResponse myResponse = new CDRResponse();
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  @DisplayName("Create CDR Response V1 with Missing Links")
  void createCDRResponseWithMissingLinks() {
    CDRResponse myResponse = new CDRResponse().meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  @DisplayName("Create CDR Response V1 with Links and broken self link")
  void createCDRResponseWithMissingLinksSelf() {
    CDRResponse myResponse = new CDRResponse().links(new Links()).meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

}
