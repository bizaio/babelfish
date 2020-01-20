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
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.v1.response.container.ResponseCommonCustomerDetailData;

@DisplayName("ResponseCommonCustomerDetailData V1 Tests")
public class ResponseCommonCustomerDetailDataV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonCustomerDetailData")
  void responseCommonCustomerDetailData() {
    ResponseCommonCustomerDetailData data =
        ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseCommonCustomerDetailData Mandatory Fields (Person)")
  void responseCommonCustomerDetailDataMandatoryFieldsPerson() {
    ResponseCommonCustomerDetailData data = new ResponseCommonCustomerDetailData();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeCustomer.PERSON);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.person(ModelConstants.DEFAULT_COMMON_PERSON_DETAIL);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Should only have one
    data.organisation(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("ResponseCommonCustomerDetailData Mandatory Fields (Organisation)")
  void responseCommonCustomerDetailDataMandatoryFieldsOrganisation() {
    ResponseCommonCustomerDetailData data = new ResponseCommonCustomerDetailData();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeCustomer.ORGANISATION);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.organisation(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Should only have one
    data.person(ModelConstants.DEFAULT_COMMON_PERSON_DETAIL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
