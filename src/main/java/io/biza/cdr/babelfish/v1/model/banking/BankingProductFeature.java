/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.banking;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeatureType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = true)


public class BankingProductFeature
    extends io.biza.cdr.babelfish.model.banking.BankingProductFeature<BankingProductFeature> {

  @AssertTrue(message = "Additional Information must be populated when Feature type is OTHER")
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(featureType())
        ? (Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.OTHER})
            .contains(featureType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value should be null or an integer when Feature Type is ADDITIONAL_CARDS")
  private boolean isValueNullOrInteger() {
    return FormatChecker.isDefined(featureType()) ? (Arrays
        .asList(new BankingProductFeatureType[] {BankingProductFeatureType.ADDITIONAL_CARDS})
        .contains(featureType())
            ? (!FormatChecker.isDefined(additionalValue())
                || FormatChecker.isPositiveInteger(additionalValue()))
            : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be populated with a String when Feature type is CARD_ACCESS, INSURANCE, COMPLEMENTARY_PRODUCT_DISCOUNTS, LOYALTY_PROGRAM, DIGITAL_WALLET or NOTIFICATIONS")
  private boolean isValueString() {
    return FormatChecker.isDefined(featureType())
        ? (Arrays
            .asList(new BankingProductFeatureType[] {BankingProductFeatureType.CARD_ACCESS,
                BankingProductFeatureType.DIGITAL_WALLET, BankingProductFeatureType.INSURANCE,
                BankingProductFeatureType.COMPLEMENTARY_PRODUCT_DISCOUNTS,
                BankingProductFeatureType.NOTIFICATIONS, BankingProductFeatureType.LOYALTY_PROGRAM})
            .contains(featureType()) ? FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Duration String when Feature type is INTEREST_FREE or INTEREST_FREE_TRANSFERS")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(featureType())
        ? (Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.INTEREST_FREE,
            BankingProductFeatureType.INTEREST_FREE_TRANSFERS}).contains(featureType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDuration(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Feature type is FREE_TXNS or BONUS_REWARDS")
  private boolean isValuePositiveInteger() {
    return FormatChecker.isDefined(featureType())
        ? (Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.FREE_TXNS,
            BankingProductFeatureType.BONUS_REWARDS}).contains(featureType())
                ? FormatChecker.isPositiveInteger(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Feature type is FREE_TXNS_ALLOWANCE")
  private boolean isValueAmount() {
    return FormatChecker.isDefined(featureType()) ? (Arrays
        .asList(new BankingProductFeatureType[] {BankingProductFeatureType.FREE_TXNS_ALLOWANCE})
        .contains(featureType())
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value should be null when Feature Type is UNLIMITED_TXNS, OFFSET, OVERDRAFT, REDRAW, BALANCE_TRANSFERS, DIGITAL_BANKING, NPP_PAYID, NPP_ENABLED, DONATE_INTEREST, OTHER")
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
}
