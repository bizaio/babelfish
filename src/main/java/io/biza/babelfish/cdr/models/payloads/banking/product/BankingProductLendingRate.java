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
package io.biza.babelfish.cdr.models.payloads.banking.product;

import java.util.Arrays;
import javax.validation.Valid;
import io.biza.babelfish.cdr.enumerations.BankingProductLendingRateType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingProductLendingRate extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductLendingRate<BankingProductLendingRate> {
  @AssertTrueBabelfish(
      message = "Additional Value must be an Duration String when Lending Rate Type INTRODUCTORY or FIXED",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(lendingRateType())
        ? (Arrays.asList(new BankingProductLendingRateType[] {
            BankingProductLendingRateType.INTRODUCTORY, BankingProductLendingRateType.FIXED})
            .contains(lendingRateType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be String when Lending Rate Type is DISCOUNT, PENALTY, FLOATING, MARKET_LINKED, BUNDLE_DISCOUNT_FIXED or BUNDLE_DISCOUNT_VARIABLE",
      fields = {"additionalValue"})
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

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Lending Rate Type is VARIABLE, CASH_ADVANCE or PURCHASE",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(lendingRateType())
        ? (Arrays
            .asList(new BankingProductLendingRateType[] {BankingProductLendingRateType.VARIABLE,
                BankingProductLendingRateType.CASH_ADVANCE, BankingProductLendingRateType.PURCHASE})
            .contains(lendingRateType()) ? !FormatChecker.isDefined(additionalValue()) : true)
        : true;
  }
}
