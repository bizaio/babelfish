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
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;

@DisplayName("CDR Paginated Response V1 Tests")
public class CDRResponsePaginatedV1Test {
  private Validator validator;


  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void createValidCDRResponsePaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedAndMetaPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingMetaPaginated() {
    CDRResponsePaginated myResponse =
        new CDRResponsePaginated().links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI));
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated().meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedSelf() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated().links(new LinksPaginated()
        .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
        .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithPreviousPageButFirstPageMissing() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .next(ModelConstants.DEFAULT_NEXT_URI).last(ModelConstants.DEFAULT_LAST_URI)
            .prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithNextPageButLastPageMissing() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePage() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).last(ModelConstants.DEFAULT_LAST_URI))
        .meta(new MetaPaginated().totalPages(1).totalRecords(10));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePageAndMoreThanOnePageTotal() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(10));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithZeroRecords() {
    CDRResponsePaginated myResponse =
        new CDRResponsePaginated().links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI))
            .meta(new MetaPaginated().totalPages(1).totalRecords(0));
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No Validation errors found when there should be as totalRecords is 0 but totalPages is 1");
  }

  @Test
  void invalidResponsePaginatedWithZeroPages() {
    CDRResponsePaginated myResponse =
        new CDRResponsePaginated().links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI))
            .meta(new MetaPaginated().totalPages(0).totalRecords(10));
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No Validation errors found when there should be as totalPages is 0 but totalRecords is 10");
  }

  @Test
  void invalidResponsePaginatedWithMissingMetaContent() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No MetaPaginated content was supplied but validation succeeded");
  }

}
