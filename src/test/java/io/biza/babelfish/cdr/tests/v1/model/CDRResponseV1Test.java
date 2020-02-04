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
import io.biza.babelfish.cdr.models.payloads.CDRResponseV1;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;

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
    CDRResponseV1 myResponse =
        new CDRResponseV1().links(new LinksV1().self(URI.create("http://localhost/"))).meta(new MetaV1());
    assertTrue(validator.validate(myResponse).isEmpty());
  }

  @Test
  @DisplayName("Create CDR Response V1 with Missing Links and Meta")
  void createCDRResponseWithMissingLinksAndMetaV1() {
    CDRResponseV1 myResponse = new CDRResponseV1();
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  @DisplayName("Create CDR Response V1 with Missing Links")
  void createCDRResponseWithMissingLinksV1() {
    CDRResponseV1 myResponse = new CDRResponseV1().meta(new MetaV1());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  @DisplayName("Create CDR Response V1 with Links and broken self link")
  void createCDRResponseWithMissingLinksSelf() {
    CDRResponseV1 myResponse = new CDRResponseV1().links(new LinksV1()).meta(new MetaV1());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

}
