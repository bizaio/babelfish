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
import io.biza.babelfish.cdr.v1.model.banking.BankingProductFeature;
import io.biza.babelfish.enumerations.cdr.BankingProductFeatureType;

@DisplayName("BankingProductBundle V1 Tests")
public class BankingProductFeatureV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFeature")
  void bankingProductFeature() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEATURE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEATURE).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Card Access")
  void bankingProductFeatureCardAccess() {

    // Missing description
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.CARD_ACCESS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with Description
    data.setAdditionalValue("Description text for card access");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeature for Additional Cards")
  void bankingProductFeatureAdditionalCards() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.ADDITIONAL_CARDS);

    // Null Value is unlimited cards
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value specifies maximum number of cards
    data.additionalValue("5");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Unlimited Transactions")
  void bankingProductFeatureUnlimitedTransactions() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.UNLIMITED_TXNS);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Free Transactions")
  void bankingProductFeatureFreeTransactions() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.FREE_TXNS);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value specifies maximum number of transactions
    data.additionalValue("10");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeature for Free Transaction Allowance with Value")
  void bankingProductFeatureFreeTransactionsAllowance() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.FREE_TXNS_ALLOWANCE);

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
  @DisplayName("BankingProductFeature for Loyalty Program")
  void bankingProductFeatureLoyaltyProgram() {

    // Missing description
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.LOYALTY_PROGRAM);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Loyalty Program Name");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeature for Offset")
  void bankingProductFeatureOffset() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.OFFSET);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Overdraft")
  void bankingProductFeatureOverdraft() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.OVERDRAFT);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Insurance")
  void bankingProductFeatureInsurance() {

    // Missing description
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.INSURANCE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Insurance Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingProductFeature for Balance Transfers")
  void bankingProductFeatureBalanceTransfers() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.BALANCE_TRANSFERS);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Interest Free")
  void bankingProductFeatureInterestFree() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.INTEREST_FREE);

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
  @DisplayName("BankingProductFeature for Interest Free Transfers")
  void bankingProductFeatureInterestFreeTransfers() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.INTEREST_FREE_TRANSFERS);

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
  @DisplayName("BankingProductFeature for Digital Wallet")
  void bankingProductFeatureDigitalWallet() {

    // Missing description
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.DIGITAL_WALLET);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Digital Wallet Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeature for Digital Banking")
  void bankingProductFeatureDigitalBanking() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.DIGITAL_BANKING);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for NPP PayID")
  void bankingProductFeaturePayID() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.NPP_PAYID);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for NPP Enabled")
  void bankingProductFeatureNPPEnabled() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.NPP_ENABLED);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Donate Interest")
  void bankingProductFeatureDonateInterest() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.DONATE_INTEREST);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Bill Payment Service")
  void bankingProductFeatureBillPayment() {

    // Null Value is correct
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.BILL_PAYMENT);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Payment Service Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeature for Complementary Product Discounts")
  void bankingProductFeatureComplementaryProductDiscounts() {
    BankingProductFeature data = new BankingProductFeature()
        .featureType(BankingProductFeatureType.COMPLEMENTARY_PRODUCT_DISCOUNTS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Digital Wallet Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingProductFeature for Notifications")
  void bankingProductFeatureNotifications() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.NOTIFICATIONS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Notifications description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Other")
  void bankingProductFeatureOther() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    data.setAdditionalInfo("Additional Information on Other feature");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeature for Bonus Rewards")
  void bankingProductFeatureBonusRewards() {
    BankingProductFeature data =
        new BankingProductFeature().featureType(BankingProductFeatureType.BONUS_REWARDS);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is invalid
    data.additionalValue("10.00");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // An integer is correct for bonus rewards
    data.additionalValue("10");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
