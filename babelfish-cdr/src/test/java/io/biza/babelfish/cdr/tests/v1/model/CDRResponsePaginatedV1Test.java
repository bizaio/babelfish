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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.LinksPaginatedV1;
import io.biza.babelfish.cdr.models.payloads.MetaPaginatedV1;
import io.biza.babelfish.cdr.models.responses.CDRResponsePaginatedV1;

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
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1()
        .links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI).build())
        .meta(MetaPaginatedV1.builder().totalPages(10).totalRecords(100).build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedAndMetaPaginatedV1() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1();
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingMetaPaginatedV1() {
    CDRResponsePaginatedV1 myResponse =
        new CDRResponsePaginatedV1().links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI).build());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedV1() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1().meta(MetaPaginatedV1.builder().build());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedSelf() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1().links(LinksPaginatedV1.builder()
        .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
        .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI).build())
        .meta(MetaPaginatedV1.builder().build());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithPreviousPageButFirstPageMissing() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1();
    myResponse
        .links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .next(ModelConstants.DEFAULT_NEXT_URI).last(ModelConstants.DEFAULT_LAST_URI)
            .prev(ModelConstants.DEFAULT_PREV_URI).build())
        .meta(MetaPaginatedV1.builder().totalPages(10).totalRecords(100).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithNextPageButLastPageMissing() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1();
    myResponse
        .links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .prev(ModelConstants.DEFAULT_PREV_URI).build())
        .meta(MetaPaginatedV1.builder().totalPages(10).totalRecords(100).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePage() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1();
    myResponse
        .links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).last(ModelConstants.DEFAULT_LAST_URI).build())
        .meta(MetaPaginatedV1.builder().totalPages(1).totalRecords(10).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePageAndMoreThanOnePageTotal() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1();
    myResponse.links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI).build())
        .meta(MetaPaginatedV1.builder().totalPages(10).totalRecords(10).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithZeroRecords() {
    CDRResponsePaginatedV1 myResponse =
        new CDRResponsePaginatedV1().links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI).build())
            .meta(MetaPaginatedV1.builder().totalPages(1).totalRecords(0).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No Validation errors found when there should be as totalRecords is 0 but totalPages is 1");
  }

  @Test
  void invalidResponsePaginatedWithZeroPages() {
    CDRResponsePaginatedV1 myResponse =
        new CDRResponsePaginatedV1().links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI).build())
            .meta(MetaPaginatedV1.builder().totalPages(0).totalRecords(10).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No Validation errors found when there should be as totalPages is 0 but totalRecords is 10");
  }

  @Test
  void invalidResponsePaginatedWithMissingMetaContent() {
    CDRResponsePaginatedV1 myResponse = new CDRResponsePaginatedV1()
        .links(LinksPaginatedV1.builder().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI).build())
        .meta(MetaPaginatedV1.builder().build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        "No MetaPaginated content was supplied but validation succeeded");
  }

}
