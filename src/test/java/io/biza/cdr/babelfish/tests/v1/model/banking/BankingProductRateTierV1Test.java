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
package io.biza.cdr.babelfish.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.CommonUnitOfMeasureType;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductRateTier;

@DisplayName("BankingProductRateTier V1 Tests")
public class BankingProductRateTierV1Test {
  private Validator validator;

  // TODO: Overlapping rate tier checks

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingProductRateTier")
  void bankingProductRateTier() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_RATE_TIER).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_RATE_TIER).toString());
  }

  @Test
  @DisplayName("BankingProductRateTier for Mandatory Fields")
  void bankingProductRateTierMandatoryFields()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductRateTier data = new BankingProductRateTier();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // add name, missing two others still
    data.name("Rate Tier Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // add unit of measure, one missing still
    data.unitOfMeasure(CommonUnitOfMeasureType.DOLLAR);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // add minimumValue, should validate now
    data.minimumValue(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductRateTier for Discrete Values")
  void bankingProductRateTierDiscreteValues()
      throws IllegalAccessException, InvocationTargetException {
    // Dollar value should pass
    BankingProductRateTier data = new BankingProductRateTier().name("Test Rate Tier")
        .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Month with no bounding should pass
    data.unitOfMeasure(CommonUnitOfMeasureType.MONTH);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // As should day
    data.unitOfMeasure(CommonUnitOfMeasureType.DAY);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Including if maximum value is set
    data.unitOfMeasure(CommonUnitOfMeasureType.MONTH);
    data.maximumValue(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // For Day too
    data.unitOfMeasure(CommonUnitOfMeasureType.DAY);
    data.maximumValue(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
