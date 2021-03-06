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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.enumerations.CommonPhoneNumberPurpose;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhoneNumberV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("CommonPhoneNumber V1 Tests")
public class CommonPhoneNumberV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonPhoneNumber")
  void commonPhoneNumber() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_PHONE_NUMBER).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_PHONE_NUMBER).toString());
  }

  @Test
  @DisplayName("CommonPhoneNumber Mandatory Fields (Home)")
  void commonPhoneNumberMandatoryFieldsHome() {
    CommonPhoneNumberV1 data = new CommonPhoneNumberV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.purpose(CommonPhoneNumberPurpose.HOME);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.number("0733076000");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.fullNumber("tel:+61-073-307-6000");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.areaCode("7");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonPhoneNumber Mandatory Fields (Mobile)")
  void commonPhoneNumberMandatoryFieldsMobile() {
    CommonPhoneNumberV1 data = new CommonPhoneNumberV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.purpose(CommonPhoneNumberPurpose.MOBILE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.number("0404839839");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.fullNumber("tel:+61-040-483-9839");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
