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
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.CommonUnitOfMeasureType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductRateTierV1;

@DisplayName("BankingProductRateTierV1 V1 Tests")
public class BankingProductRateTierV1Test {
  private Validator validator;

  // TODO: Overlapping rate tier checks

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingProductRateTierV1")
  void bankingProductRateTier() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_RATE_TIER).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_RATE_TIER).toString());
  }

  @Test
  @DisplayName("BankingProductRateTierV1 for Mandatory Fields")
  void bankingProductRateTierMandatoryFields()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductRateTierV1 data = new BankingProductRateTierV1.Builder().buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // add name, missing two others still
    assertFalse(
        validator
            .validate(
                BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name").buildPartial())
            .isEmpty(),
        validator
            .validate(
                BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name").buildPartial())
            .toString());

    // add unit of measure, one missing still
    assertFalse(
        validator.validate(BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name")
            .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).buildPartial()).isEmpty(),
        validator.validate(BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name")
            .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).buildPartial()).toString());

    // add minimumValue, should validate now
    assertTrue(
        validator.validate(BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name")
            .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("10.00"))
            .buildPartial()).isEmpty(),
        validator.validate(BankingProductRateTierV1.Builder.from(data).name("Rate Tier Name")
            .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("10.00"))
            .buildPartial()).toString());
  }

  @Test
  @DisplayName("BankingProductRateTierV1 for Discrete Values")
  void bankingProductRateTierDiscreteValues()
      throws IllegalAccessException, InvocationTargetException {
    // Dollar value should pass
    BankingProductRateTierV1 data = new BankingProductRateTierV1.Builder().name("Test Rate Tier")
        .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("10.00"))
        .buildPartial();
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Month with no bounding should pass
    assertTrue(
        validator.validate(BankingProductRateTierV1.Builder.from(data)
            .unitOfMeasure(CommonUnitOfMeasureType.MONTH).buildPartial()).isEmpty(),
        validator.validate(BankingProductRateTierV1.Builder.from(data)
            .unitOfMeasure(CommonUnitOfMeasureType.MONTH).buildPartial()).toString());

    // As should day
    assertTrue(
        validator.validate(BankingProductRateTierV1.Builder.from(data)
            .unitOfMeasure(CommonUnitOfMeasureType.DAY).buildPartial()).isEmpty(),
        validator.validate(BankingProductRateTierV1.Builder.from(data)
            .unitOfMeasure(CommonUnitOfMeasureType.DAY).buildPartial()).toString());

    // Including if maximum value is set
    assertTrue(
        validator.validate(
            BankingProductRateTierV1.Builder.from(data).unitOfMeasure(CommonUnitOfMeasureType.MONTH)
                .maximumValue(new BigDecimal("10.00")).buildPartial())
            .isEmpty(),
        validator.validate(
            BankingProductRateTierV1.Builder.from(data).unitOfMeasure(CommonUnitOfMeasureType.MONTH)
                .maximumValue(new BigDecimal("10.00")).buildPartial())
            .toString());

    // For Day too
    assertTrue(
        validator.validate(
            BankingProductRateTierV1.Builder.from(data).unitOfMeasure(CommonUnitOfMeasureType.DAY)
                .maximumValue(new BigDecimal("10.00")).buildPartial())
            .isEmpty(),
        validator.validate(
            BankingProductRateTierV1.Builder.from(data).unitOfMeasure(CommonUnitOfMeasureType.DAY)
                .maximumValue(new BigDecimal("10.00")).buildPartial())
            .toString());

  }

}
