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
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingProductDiscount extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductDiscount<BankingProductDiscount> {

  @AssertTrueBabelfish(
      message = "Eligibility Criteria must be populated when Discount type is ELIGIBILITY_ONLY",
      fields = {"eligibility"})
  private boolean isEligibilityPopulated() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays
            .asList(new BankingProductDiscountType[] {BankingProductDiscountType.ELIGIBILITY_ONLY})
            .contains(discountType()) ? (eligibility() != null && eligibility().size() > 0) : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Discount type is BALANCE, DEPOSITS or PAYMENTS",
      fields = {"additionalValue"})
  private boolean isValueAmount() {
    return FormatChecker.isDefined(discountType()) ? (Arrays
        .asList(new BankingProductDiscountType[] {BankingProductDiscountType.BALANCE,
            BankingProductDiscountType.DEPOSITS, BankingProductDiscountType.PAYMENTS})
        .contains(discountType())
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Duration String when Discount type is FEE_CAP",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays.asList(new BankingProductDiscountType[] {BankingProductDiscountType.FEE_CAP})
            .contains(discountType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Discount Type is ELIGIBILITY_ONLY",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays
            .asList(new BankingProductDiscountType[] {BankingProductDiscountType.ELIGIBILITY_ONLY})
            .contains(discountType()) ? !FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }
}
