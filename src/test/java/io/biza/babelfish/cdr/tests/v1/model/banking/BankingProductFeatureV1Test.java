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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.BankingProductFeatureType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeatureV1;

@DisplayName("BankingProductBundle V1 Tests")
public class BankingProductFeatureV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFeatureV1")
  void bankingProductFeature() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEATURE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEATURE).toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Card Access")
  void bankingProductFeatureCardAccess() {

    // Missing description
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.CARD_ACCESS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Description text for card access").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Description text for card access").build()).toString());


  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Additional Cards")
  void bankingProductFeatureAdditionalCards() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.ADDITIONAL_CARDS).buildPartial();

    // Null Value is unlimited cards
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value specifies maximum number of cards
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Unlimited Transactions")
  void bankingProductFeatureUnlimitedTransactions() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.UNLIMITED_TXNS).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Free Transactions")
  void bankingProductFeatureFreeTransactions() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.FREE_TXNS).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value specifies maximum number of transactions
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Free Transaction Allowance with Value")
  void bankingProductFeatureFreeTransactionsAllowance() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.FREE_TXNS_ALLOWANCE).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Loyalty Program")
  void bankingProductFeatureLoyaltyProgram() {

    // Missing description
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.LOYALTY_PROGRAM).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Loyalty Program Name").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Loyalty Program Name").build()).toString());


  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Offset")
  void bankingProductFeatureOffset() {
    BankingProductFeatureV1 data =
        new BankingProductFeatureV1.Builder().type(BankingProductFeatureType.OFFSET).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Overdraft")
  void bankingProductFeatureOverdraft() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.OVERDRAFT).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Insurance")
  void bankingProductFeatureInsurance() {

    // Missing description
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.INSURANCE).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Insurance Description").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Insurance Description").build()).toString());

  }


  @Test
  @DisplayName("BankingProductFeatureV1 for Balance Transfers")
  void bankingProductFeatureBalanceTransfers() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.BALANCE_TRANSFERS).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Interest Free")
  void bankingProductFeatureInterestFree() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.INTEREST_FREE).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(
            BankingProductFeatureV1.Builder.from(data).additionalValue("Not a Duration").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeatureV1.Builder.from(data).additionalValue("Not a Duration").build())
            .toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("P1D").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Interest Free Transfers")
  void bankingProductFeatureInterestFreeTransfers() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.INTEREST_FREE_TRANSFERS).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    assertFalse(
        validator.validate(
            BankingProductFeatureV1.Builder.from(data).additionalValue("Not a Duration").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeatureV1.Builder.from(data).additionalValue("Not a Duration").build())
            .toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("P1D").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Digital Wallet")
  void bankingProductFeatureDigitalWallet() {

    // Missing description
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.DIGITAL_WALLET).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Digital Wallet Description").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Digital Wallet Description").build()).toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Digital Banking")
  void bankingProductFeatureDigitalBanking() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.DIGITAL_BANKING).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for NPP PayID")
  void bankingProductFeaturePayID() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.NPP_PAYID).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for NPP Enabled")
  void bankingProductFeatureNPPEnabled() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.NPP_ENABLED).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Donate Interest")
  void bankingProductFeatureDonateInterest() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.DONATE_INTEREST).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("Invalid").build())
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Bill Payment Service")
  void bankingProductFeatureBillPayment() {

    // Null Value is correct
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.BILL_PAYMENT).buildPartial();
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Payment Service Description").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Payment Service Description").build()).toString());

  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Complementary Product Discounts")
  void bankingProductFeatureComplementaryProductDiscounts() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.COMPLEMENTARY_PRODUCT_DISCOUNTS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Digital Wallet Description").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Digital Wallet Description").build()).toString());
  }


  @Test
  @DisplayName("BankingProductFeatureV1 for Notifications")
  void bankingProductFeatureNotifications() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.NOTIFICATIONS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Notifications description").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Notifications description").build()).toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Other")
  void bankingProductFeatureOther() {
    BankingProductFeatureV1 data =
        new BankingProductFeatureV1.Builder().type(BankingProductFeatureType.OTHER).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Additional Information on Other feature").build()).isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data)
            .additionalValue("Additional Information on Other feature").build()).toString());
  }

  @Test
  @DisplayName("BankingProductFeatureV1 for Bonus Rewards")
  void bankingProductFeatureBonusRewards() {
    BankingProductFeatureV1 data = new BankingProductFeatureV1.Builder()
        .type(BankingProductFeatureType.BONUS_REWARDS).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .isEmpty(),
        validator
            .validate(
                BankingProductFeatureV1.Builder.from(data).additionalValue("Not A Number").build())
            .toString());

    // AmountString formatted value is invalid
    assertFalse(
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10.00").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10.00").build())
            .toString());

    // An integer is correct for bonus rewards
    assertTrue(
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .isEmpty(),
        validator.validate(BankingProductFeatureV1.Builder.from(data).additionalValue("10").build())
            .toString());

  }
}
