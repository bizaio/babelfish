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
import io.biza.babelfish.enumerations.cdr.BankingProductLendingRateType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductLendingRateV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductLendingRateV1;

@DisplayName("BankingProductLendingRateV1 V1 Tests")
public class BankingProductLendingRateV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductLendingRateV1")
  void bankingProductLendingRate() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_LENDING_RATE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_LENDING_RATE).toString());
  }



  @Test
  @DisplayName("BankingProductLendingRateV1 for Fixed")
  void bankingProductLendingRateFixed() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.FIXED).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductLendingRateV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductLendingRateV1.Builder.from(data).additionalValue("P1D").build())
            .toString());
  }


  @Test
  @DisplayName("BankingProductLendingRateV1 for Variable")
  void bankingProductLendingRateTransaction() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.VARIABLE).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Introductory")
  void bankingProductLendingRateIntroductory() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.INTRODUCTORY).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductLendingRateV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductLendingRateV1.Builder.from(data).additionalValue("P1D").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Discount")
  void bankingProductLendingRateDiscount() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.DISCOUNT).rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Penalty")
  void bankingProductLendingRatePenalty() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.PENALTY).rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Floating")
  void bankingProductLendingRateFloating() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.FLOATING).rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Market Linked")
  void bankingProductLendingRateMarketLinked() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.MARKET_LINKED).rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Cash Advance")
  void bankingProductLendingRateCashAdvance() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.CASH_ADVANCE).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Purchase")
  void bankingProductLendingRatePurchase() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.PURCHASE).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductLendingRateV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Bundle Discount Fixed")
  void bankingProductLendingRateBundleDiscountFixed() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.BUNDLE_DISCOUNT_FIXED)
        .rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRateV1 for Bundle Discount Variable")
  void bankingProductLendingRateBundleDiscountVariable() {
    BankingProductLendingRateV1 data = new BankingProductLendingRateV1.Builder()
        .type(BankingProductLendingRateType.BUNDLE_DISCOUNT_VARIABLE)
        .rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductLendingRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

}
