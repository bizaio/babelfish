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
import io.biza.babelfish.cdr.v1.enumerations.BankingProductFeeType;
import io.biza.babelfish.cdr.v1.model.banking.BankingProductFee;

@DisplayName("BankingProductFee V1 Tests")
public class BankingProductFeeV1Test {
  private Validator validator;

  // TODO: Enforce different value types
  // TODO: Enforce name and feeType mandatory field

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFee")
  void bankingProductFee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Periodic")
  void bankingProductFeeUnlimitedTransactions() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.PERIODIC).amount(new BigDecimal("10.00"));

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    data.additionalValue("Not a Duration");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Duration String formatted value is valid
    data.additionalValue("P1D");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingProductFee for Transaction")
  void bankingProductFeeTransaction() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.TRANSACTION).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Withdrawal")
  void bankingProductFeeWithdrawal() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.WITHDRAWAL).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Deposit")
  void bankingProductFeeDeposit() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.DEPOSIT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Payment")
  void bankingProductFeePayment() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.PAYMENT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Purchase")
  void bankingProductFeePurchase() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.PURCHASE).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Event")
  void bankingProductFeeEvent() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.EVENT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Upfront")
  void bankingProductFeeUpfront() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.UPFRONT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFee for Exit")
  void bankingProductFeeExit() {
    BankingProductFee data = new BankingProductFee().name("Fee Name")
        .feeType(BankingProductFeeType.EXIT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
