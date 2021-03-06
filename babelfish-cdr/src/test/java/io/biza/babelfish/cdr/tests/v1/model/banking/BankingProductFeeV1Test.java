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
import io.biza.babelfish.cdr.enumerations.BankingProductFeeType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
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
    BankingProductFeeV1 data = new BankingProductFeeV1().name("Fee Name")
        .feeType(BankingProductFeeType.EXIT).amount(new BigDecimal("10.00"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
