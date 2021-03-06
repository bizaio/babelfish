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
package io.biza.babelfish.cdr.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductBundleV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingProductBundle V1 Tests")
public class BankingProductBundleV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingProductBundle")
  void createValidBankingProductBundle() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE).toString());
  }

  @Test
  @DisplayName("Create valid BankingProductBundle with empty name")
  void createBankingProductBundleWithEmptyName()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductBundleV1 data = new BankingProductBundleV1();
    BeanUtils.copyProperties(data, ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE);
    data.name("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create valid BankingProductBundle with empty description")
  void createBankingProductBundleWithEmptyDescription()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductBundleV1 data = new BankingProductBundleV1();
    BeanUtils.copyProperties(data, ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE);
    data.description("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
