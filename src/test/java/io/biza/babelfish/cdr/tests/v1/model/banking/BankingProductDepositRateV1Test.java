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
import io.biza.babelfish.cdr.enumerations.BankingProductDepositRateType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDepositRate;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

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
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.FIXED).rate(new BigDecimal("0.05"));

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
  @DisplayName("BankingProductDepositRate for Bonus")
  void bankingProductDepositRateBonus() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.BONUS).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Bundle Bonus")
  void bankingProductDepositRateBundleBonus() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.BUNDLE_BONUS).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Variable")
  void bankingProductDepositRateTransaction() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.VARIABLE).rate(new BigDecimal("0.05"));

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Introductory")
  void bankingProductDepositRateIntroductory() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.INTRODUCTORY).rate(new BigDecimal("0.05"));

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
  @DisplayName("BankingProductDepositRate for Floating")
  void bankingProductDepositRateFloating() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.FLOATING).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductDepositRate for Market Linked")
  void bankingProductDepositRateMarketLinked() {
    BankingProductDepositRate data = new BankingProductDepositRate()
        .depositRateType(BankingProductDepositRateType.MARKET_LINKED).rate(new BigDecimal("0.05"));

    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
