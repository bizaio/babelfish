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
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.BankingProductFeeType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeV1;

@DisplayName("BankingProductFeeV1 V1 Tests")
public class BankingProductFeeV1Test {
  private Validator validator;

  // TODO: Enforce different value types
  // TODO: Enforce name and type mandatory field

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFeeV1")
  void bankingProductFee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE).toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Periodic")
  void bankingProductFeeUnlimitedTransactions() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.PERIODIC).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(
            BankingProductFeeV1.Builder.from(data).additionalValue("Not a Duration").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeeV1.Builder.from(data).additionalValue("Not a Duration").build())
            .toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductFeeV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeeV1.Builder.from(data).additionalValue("P1D").build())
            .toString());
  }


  @Test
  @DisplayName("BankingProductFeeV1 for Transaction")
  void bankingProductFeeTransaction() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.TRANSACTION).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Withdrawal")
  void bankingProductFeeWithdrawal() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.WITHDRAWAL).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Deposit")
  void bankingProductFeeDeposit() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.DEPOSIT).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Payment")
  void bankingProductFeePayment() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.PAYMENT).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Purchase")
  void bankingProductFeePurchase() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.PURCHASE).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Event")
  void bankingProductFeeEvent() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.EVENT).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Upfront")
  void bankingProductFeeUpfront() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.UPFRONT).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeV1 for Exit")
  void bankingProductFeeExit() {
    BankingProductFeeV1 data = new BankingProductFeeV1.Builder().name("Fee Name")
        .type(BankingProductFeeType.EXIT).amount(new BigDecimal("10.00")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeeV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

}
