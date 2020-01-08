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
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingProductLendingRate extends
    io.biza.cdr.babelfish.model.banking.BankingProductLendingRate<BankingProductLendingRate> {
  @AssertTrue(
      message = "Additional Value must be an Duration String when Lending Rate Type INTRODUCTORY or FIXED")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(lendingRateType())
        ? (Arrays.asList(new BankingProductLendingRateType[] {
            BankingProductLendingRateType.INTRODUCTORY, BankingProductLendingRateType.FIXED})
            .contains(lendingRateType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDuration(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be String when Lending Rate Type is DISCOUNT, PENALTY, FLOATING, MARKET_LINKED, BUNDLE_DISCOUNT_FIXED or BUNDLE_DISCOUNT_VARIABLE")
  private boolean isValueString() {
    return FormatChecker
        .isDefined(lendingRateType())
            ? (Arrays
                .asList(new BankingProductLendingRateType[] {BankingProductLendingRateType.DISCOUNT,
                    BankingProductLendingRateType.PENALTY, BankingProductLendingRateType.FLOATING,
                    BankingProductLendingRateType.MARKET_LINKED,
                    BankingProductLendingRateType.BUNDLE_DISCOUNT_FIXED,
                    BankingProductLendingRateType.BUNDLE_DISCOUNT_VARIABLE})
                .contains(lendingRateType()) ? FormatChecker.isNotEmpty(additionalValue()) : true)
            : true;
  }

  @AssertTrue(
      message = "Additional Value should be null when Lending Rate Type is VARIABLE, CASH_ADVANCE or PURCHASE")
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(lendingRateType())
        ? (Arrays
            .asList(new BankingProductLendingRateType[] {BankingProductLendingRateType.VARIABLE,
                BankingProductLendingRateType.CASH_ADVANCE, BankingProductLendingRateType.PURCHASE})
            .contains(lendingRateType()) ? !FormatChecker.isDefined(additionalValue()) : true)
        : true;
  }
}
