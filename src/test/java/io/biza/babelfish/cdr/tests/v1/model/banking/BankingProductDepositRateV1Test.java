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
import io.biza.babelfish.enumerations.cdr.BankingProductDepositRateType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDepositRateV1;

@DisplayName("BankingProductDepositRate V1 Tests")
public class BankingProductDepositRateV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductDepositRate")
  void bankingProductDepositRate() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE).toString());
  }



  @Test
  @DisplayName("BankingProductDepositRate for Fixed")
  void bankingProductDepositRateFixed() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.FIXED).rate(new BigDecimal("0.05")).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductDepositRateV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductDepositRateV1.Builder.from(data).additionalValue("P1D").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Bonus")
  void bankingProductDepositRateBonus() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.BONUS).rate(new BigDecimal("0.05")).build();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Bundle Bonus")
  void bankingProductDepositRateBundleBonus() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.BUNDLE_BONUS).rate(new BigDecimal("0.05")).build();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Variable")
  void bankingProductDepositRateTransaction() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.VARIABLE).rate(new BigDecimal("0.05")).build();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(
                BankingProductDepositRateV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductDepositRateV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Introductory")
  void bankingProductDepositRateIntroductory() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.INTRODUCTORY).rate(new BigDecimal("0.05")).build();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).toString());

    // Duration String formatted value is valid
    assertFalse(
        validator
            .validate(BankingProductDepositRateV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductDepositRateV1.Builder.from(data).additionalValue("P1D").build())
            .toString());

  }


  @Test
  @DisplayName("BankingProductDepositRate for Floating")
  void bankingProductDepositRateFloating() {
    BankingProductDepositRateV1 data = new BankingProductDepositRateV1.Builder()
        .type(BankingProductDepositRateType.FLOATING).rate(new BigDecimal("0.05")).build();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Market Linked")
  void bankingProductDepositRateMarketLinked() {
    BankingProductDepositRateV1 data =
        new BankingProductDepositRateV1.Builder().type(BankingProductDepositRateType.MARKET_LINKED)
            .rate(new BigDecimal("0.05")).buildPartial();

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).isEmpty(),
        validator.validate(BankingProductDepositRateV1.Builder.from(data)
            .additionalValue("Description Text").build()).toString());
  }

}
