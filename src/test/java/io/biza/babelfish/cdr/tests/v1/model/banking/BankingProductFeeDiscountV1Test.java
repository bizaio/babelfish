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
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.BankingProductDiscountType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDiscountV1;

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
    BankingProductDiscountV1 data =
        new BankingProductDiscountV1.Builder().description("Discount Description")
            .amount(new BigDecimal("10.00")).type(BankingProductDiscountType.BALANCE).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Deposits")
  void bankingProductFeeDiscountDeposits() {
    BankingProductDiscountV1 data =
        new BankingProductDiscountV1.Builder().description("Discount Description")
            .amount(new BigDecimal("10.00")).type(BankingProductDiscountType.DEPOSITS).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Payments")
  void bankingProductFeeDiscountPayments() {
    BankingProductDiscountV1 data =
        new BankingProductDiscountV1.Builder().description("Discount Description")
            .amount(new BigDecimal("10.00")).type(BankingProductDiscountType.PAYMENTS).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Fee Cap")
  void bankingProductFeeDiscountFeeCap() {
    BankingProductDiscountV1 data =
        new BankingProductDiscountV1.Builder().description("Discount Description")
            .amount(new BigDecimal("10.00")).type(BankingProductDiscountType.FEE_CAP).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(
            BankingProductDiscountV1.Builder.from(data).additionalValue("Not a Duration").build())
            .isEmpty(),
        validator.validate(
            BankingProductDiscountV1.Builder.from(data).additionalValue("Not a Duration").build())
            .toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductDiscountV1.Builder.from(data).additionalValue("P1D").build())
            .toString());

    // TODO: Add tests for associated rate entry fields

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Eligibility Only")
  void bankingProductFeeDiscountEligibilityOnly() {
    BankingProductDiscountV1 data = new BankingProductDiscountV1.Builder()
        .description("Discount Description").amount(new BigDecimal("10.00"))
        .type(BankingProductDiscountType.ELIGIBILITY_ONLY).build();

    // No eligibility criteria is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // With Eligibility is valid
    assertTrue(
        validator.validate(BankingProductDiscountV1.Builder.from(data)
            .addAllEligibility(
                List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY))
            .build()).isEmpty(),
        validator.validate(BankingProductDiscountV1.Builder.from(data)
            .addAllEligibility(
                List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY))
            .build()).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductDiscountV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());

  }

}
