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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.BankingProductConstraintType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductConstraintV1;

@DisplayName("BankingProductConstraint V1 Tests")
public class BankingProductConstraintV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingProductConstraint")
  void createValidBankingProductBundle() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_CONSTRAINT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_CONSTRAINT).toString());
  }

  @Test
  @DisplayName("BankingProductConstraint for Minimum Balance")
  void bankingProductConstraintMinBalance() {
    BankingProductConstraintV1 data = new BankingProductConstraintV1.Builder()
        .type(BankingProductConstraintType.MIN_BALANCE).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Maximum Balance")
  void bankingProductConstraintMaxBalance() {
    BankingProductConstraintV1 data = new BankingProductConstraintV1.Builder()
        .type(BankingProductConstraintType.MAX_BALANCE).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Opening Balance")
  void bankingProductConstraintOpeningBalance() {
    BankingProductConstraintV1 data = new BankingProductConstraintV1.Builder()
        .type(BankingProductConstraintType.OPENING_BALANCE).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }


  @Test
  @DisplayName("BankingProductConstraint for Max Limit")
  void bankingProductConstraintMaxLimit() {
    BankingProductConstraintV1 data = new BankingProductConstraintV1.Builder()
        .type(BankingProductConstraintType.MAX_LIMIT).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Min Limit")
  void bankingProductConstraintMinLimit() {
    BankingProductConstraintV1 data = new BankingProductConstraintV1.Builder()
        .type(BankingProductConstraintType.MIN_LIMIT).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator.validate(
            BankingProductConstraintV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductConstraintV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductConstraintV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }
}
