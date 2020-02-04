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
import java.util.Locale;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.Constants;
import io.biza.babelfish.cdr.models.payloads.common.CommonSimpleAddressV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("CommonSimpleAddress V1 Tests")
public class CommonSimpleAddressV1Test {
  private Validator validator;

  // TODO: Validate australian states

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonSimpleAddress")
  void responseCommonSimpleAddress() {
    CommonSimpleAddressV1 data = ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("CommonSimpleAddress Mandatory Fields (Australia)")
  void responseCommonSimpleAddressMandatoryFieldsAustralia() {
    CommonSimpleAddressV1 data = new CommonSimpleAddressV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.addressLine1("10 McGill Street");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.postcode("2550");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.city("Cobargo");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.state("NSW");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonSimpleAddress Mandatory Fields (International)")
  void responseCommonSimpleAddressMandatoryFieldsInternational() {
    CommonSimpleAddressV1 data = new CommonSimpleAddressV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.addressLine1("10 McGill Street");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.city("Paris");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // https://github.com/ConsumerDataStandardsAustralia/standards-maintenance/issues/88
    data.state("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.country(new Locale(Constants.DEFAULT_LOCALE, "FR"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
