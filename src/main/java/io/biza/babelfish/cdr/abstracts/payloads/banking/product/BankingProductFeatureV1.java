package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductFeatureType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductFeatureV1 {

  public abstract BankingProductFeatureType featureType();

  public abstract String additionalValue();

  public abstract String additionalInfo();

  @AssertTrueBabelfish(
      message = "Card Access: Should describe the card types that this product can be linked to",
      fields = {"additionalValue"})
  private boolean isCardAccessValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.CARD_ACCESS)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Cards: Should specify the maximum number of additional cards. If no maximum should be left empty",
      fields = {"additionalValue"})
  private boolean isAdditionalCardsValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.ADDITIONAL_CARDS)
            ? !FormatChecker.isDefined(additionalValue())
                || FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Free Transactions: Should specify the number of free transactions included",
      fields = {"additionalValue"})
  private boolean isFreeTransactionsValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.FREE_TXNS)
            ? FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Transactions Allowance: Should specify in AUD decimal format the amount of transaction fees discounted (eg. 10.00)",
      fields = {"additionalValue"})
  private boolean isFreeTransactionAllowanceValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.FREE_TXNS_ALLOWANCE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Loyalty Program: Should specify the name of the loyalty program",
      fields = {"additionalValue"})
  private boolean isLoyaltyProgramValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.LOYALTY_PROGRAM)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Insurance: Should provide a text description of the type of insurance (eg. Travel Insurance)",
      fields = {"additionalValue"})
  private boolean isInsuranceValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.INSURANCE)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Interest Free Purchases: Should provide an interest free period in ISO-8601 Duration format",
      fields = {"additionalValue"})
  private boolean isInterestFreePurchases() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.INTEREST_FREE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isDurationString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Interest Free Balance Transfers: Should provide an interest free period in ISO-8601 Duration format",
      fields = {"additionalValue"})
  private boolean isInterestFreeBalanceTransfer() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.INTEREST_FREE_TRANSFERS)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isDurationString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Digital Wallet: The name or band of the Digital Wallet provided",
      fields = {"additionalValue"})
  private boolean isDigitalWalletValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.DIGITAL_WALLET)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Complementary Product Offering: Should be a description of the complementary offering",
      fields = {"additionalValue"})
  private boolean isComplementaryOfferValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.COMPLEMENTARY_PRODUCT_DISCOUNTS)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Bonus Rewards: Should specify number of points available (eg. 10000)",
      fields = {"additionalValue"})
  private boolean isBonusRewardsValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.BONUS_REWARDS)
            ? FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Notifications: Provide a description of notification capability",
      fields = {"additionalValue"})
  private boolean isNotificationsValid() {
    return FormatChecker.isDefined(featureType())
        && featureType().equals(BankingProductFeatureType.NOTIFICATIONS)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Additional Value should be null with this Feature Type",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker
        .isDefined(featureType())
            ? (Arrays
                .asList(new BankingProductFeatureType[] {BankingProductFeatureType.UNLIMITED_TXNS,
                    BankingProductFeatureType.OFFSET, BankingProductFeatureType.OVERDRAFT,
                    BankingProductFeatureType.REDRAW, BankingProductFeatureType.BALANCE_TRANSFERS,
                    BankingProductFeatureType.DIGITAL_BANKING, BankingProductFeatureType.NPP_PAYID,
                    BankingProductFeatureType.NPP_ENABLED,
                    BankingProductFeatureType.DONATE_INTEREST, BankingProductFeatureType.OTHER})
                .contains(featureType()) ? !FormatChecker.isDefined(additionalValue()) : true)
            : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Information must be populated when Feature type is OTHER",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(featureType())
        ? (Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.OTHER})
            .contains(featureType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

}
