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
import io.biza.babelfish.cdr.enumerations.BankingProductLendingRateType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductLendingRate;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingProductLendingRate V1 Tests")
public class BankingProductLendingRateV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductLendingRate")
  void bankingProductLendingRate() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_LENDING_RATE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_LENDING_RATE).toString());
  }



  @Test
  @DisplayName("BankingProductLendingRate for Fixed")
  void bankingProductLendingRateFixed() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.FIXED).rate(new BigDecimal("0.05"));

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
  @DisplayName("BankingProductLendingRate for Variable")
  void bankingProductLendingRateTransaction() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.VARIABLE).rate(new BigDecimal("0.05"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Introductory")
  void bankingProductLendingRateIntroductory() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.INTRODUCTORY).rate(new BigDecimal("0.05"));

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
  @DisplayName("BankingProductLendingRate for Discount")
  void bankingProductLendingRateDiscount() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.DISCOUNT).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Penalty")
  void bankingProductLendingRatePenalty() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.PENALTY).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Floating")
  void bankingProductLendingRateFloating() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.FLOATING).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Market Linked")
  void bankingProductLendingRateMarketLinked() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.MARKET_LINKED).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Cash Advance")
  void bankingProductLendingRateCashAdvance() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.CASH_ADVANCE).rate(new BigDecimal("0.05"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Purchase")
  void bankingProductLendingRatePurchase() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.PURCHASE).rate(new BigDecimal("0.05"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Bundle Discount Fixed")
  void bankingProductLendingRateBundleDiscountFixed() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.BUNDLE_DISCOUNT_FIXED)
        .rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductLendingRate for Bundle Discount Variable")
  void bankingProductLendingRateBundleDiscountVariable() {
    BankingProductLendingRate data = new BankingProductLendingRate()
        .lendingRateType(BankingProductLendingRateType.BUNDLE_DISCOUNT_VARIABLE)
        .rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
