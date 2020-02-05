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
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingProductFeeDiscount V1 Tests")
public class BankingProductFeeDiscountV1Test {
  private Validator validator;

  // TODO: Test amount, balanceRate, transactionRate, feeRate, accruedRate variability

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  // TODO: Enforce description field

  @Test
  @DisplayName("Valid BankingProductFeeDiscount")
  void bankingProductFeeDiscount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Balance")
  void bankingProductFeeDiscountBalance() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1().description("Discount Description")
        .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE);

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
  @DisplayName("BankingProductFeeDiscount for Deposits")
  void bankingProductFeeDiscountDeposits() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1().description("Discount Description")
        .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.DEPOSITS);

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
  @DisplayName("BankingProductFeeDiscount for Payments")
  void bankingProductFeeDiscountPayments() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1().description("Discount Description")
        .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.PAYMENTS);

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
  @DisplayName("BankingProductFeeDiscount for Fee Cap")
  void bankingProductFeeDiscountFeeCap() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1().description("Discount Description")
        .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.FEE_CAP);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    data.additionalValue("Not a Duration");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Duration String formatted value is valid
    data.additionalValue("P1D");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // TODO: Add tests for associated rate entry fields

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Eligibility Only")
  void bankingProductFeeDiscountEligibilityOnly() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1().description("Discount Description")
        .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.ELIGIBILITY_ONLY);
    
    // No eligibility criteria is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.eligibility(List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY.build()));
    
    // With Eligibility is valid
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());


  }

}
