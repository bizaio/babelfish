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
package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("LinksPaginated V1 Tests")
public class LinksPaginatedV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid LinksPaginated")
  void responseLinksPaginated() {
    LinksPaginated data = ModelConstants.DEFAULT_LINKS_PAGINATED;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("LinksPaginated Mandatory Fields (Middle Result)")
  void linksPaginatedMandatoryFieldsMiddleResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.self(ModelConstants.DEFAULT_SELF_URI);
    data.first(ModelConstants.DEFAULT_FIRST_URI);
    data.prev(ModelConstants.DEFAULT_PREV_URI);
    data.next(ModelConstants.DEFAULT_NEXT_URI);
    data.last(ModelConstants.DEFAULT_LAST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("LinksPaginated Mandatory Fields (First Result)")
  void linksPaginatedMandatoryFieldsFirstResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.self(ModelConstants.DEFAULT_SELF_URI);
    data.next(ModelConstants.DEFAULT_NEXT_URI);
    data.last(ModelConstants.DEFAULT_LAST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("LinksPaginated Mandatory Fields (Last Result)")
  void linksPaginatedMandatoryFieldsLastResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.self(ModelConstants.DEFAULT_SELF_URI);
    data.first(ModelConstants.DEFAULT_FIRST_URI);
    data.prev(ModelConstants.DEFAULT_PREV_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("LinksPaginated Mandatory Fields (First and Last)")
  void linksPaginatedMandatoryFieldsFirstAndLastPage() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.self(ModelConstants.DEFAULT_FIRST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
