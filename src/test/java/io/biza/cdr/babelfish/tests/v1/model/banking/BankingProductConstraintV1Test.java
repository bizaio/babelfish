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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductConstraint;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

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
    BankingProductConstraint data =
        new BankingProductConstraint().constraintType(BankingProductConstraintType.MIN_BALANCE);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Maximum Balance")
  void bankingProductConstraintMaxBalance() {
    BankingProductConstraint data =
        new BankingProductConstraint().constraintType(BankingProductConstraintType.MAX_BALANCE);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Opening Balance")
  void bankingProductConstraintOpeningBalance() {
    BankingProductConstraint data =
        new BankingProductConstraint().constraintType(BankingProductConstraintType.OPENING_BALANCE);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingProductConstraint for Max Limit")
  void bankingProductConstraintMaxLimit() {
    BankingProductConstraint data =
        new BankingProductConstraint().constraintType(BankingProductConstraintType.MAX_LIMIT);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductConstraint for Min Limit")
  void bankingProductConstraintMinLimit() {
    BankingProductConstraint data =
        new BankingProductConstraint().constraintType(BankingProductConstraintType.MIN_LIMIT);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
