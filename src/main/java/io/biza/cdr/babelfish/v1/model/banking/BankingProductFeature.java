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

@Valid
public class BankingProductFeature
    extends io.biza.cdr.babelfish.model.banking.BankingProductFeature<BankingProductFeature> {
  @AssertTrue(message = "Additional Information must be populated when Feature type is OTHER")
  private boolean isInfoDefined() {
    return Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.OTHER})
        .contains(featureType()) ? FormatChecker.isDefined(additionalInfo()) : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Duration String when Feature type is INTEREST_FREE or INTEREST_FREE_TRANSFERS")
  private boolean isValueDuration() {
    return Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.INTEREST_FREE,
        BankingProductFeatureType.INTEREST_FREE_TRANSFERS}).contains(featureType())
            ? FormatChecker.isDuration(additionalValue())
            : true;
  }

  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Feature type is FREE_TXNS or BONUS_REWARDS")
  private boolean isValuePositiveInteger() {
    return Arrays.asList(new BankingProductFeatureType[] {BankingProductFeatureType.FREE_TXNS,
        BankingProductFeatureType.BONUS_REWARDS}).contains(featureType())
            ? FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is FREE_TXNS_ALLOWANCE")
  private boolean isValueAmount() {
    return Arrays
        .asList(new BankingProductFeatureType[] {BankingProductFeatureType.FREE_TXNS_ALLOWANCE})
        .contains(featureType()) ? FormatChecker.isDecimal(additionalValue()) : true;
  }
}
