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
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;

@Valid
public class BankingProductFeeDiscount extends
    io.biza.cdr.babelfish.model.banking.BankingProductFeeDiscount<BankingProductFeeDiscount> {
  @AssertTrue(
      message = "Additional Value must be an Amount String when Discount type is BALANCE, DEPOSITS or PAYMENTS")
  private boolean isValueAmount() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays
            .asList(new BankingProductDiscountType[] {BankingProductDiscountType.BALANCE,
                BankingProductDiscountType.DEPOSITS, BankingProductDiscountType.PAYMENTS})
            .contains(discountType()) ? FormatChecker.isDefined(additionalValue()) && FormatChecker.isAmountString(additionalValue()) : true)
        : true;
  }

  @AssertTrue(message = "Additional Value must be an Duration String when Discount type is FEE_CAP")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountType())
        ? (Arrays.asList(new BankingProductDiscountType[] {BankingProductDiscountType.FEE_CAP})
            .contains(discountType()) ? FormatChecker.isDefined(additionalValue()) && FormatChecker.isDuration(additionalValue()) : true)
        : true;
  }
  
  @AssertTrue(
      message = "Additional Value should be null when Discount Type is ELIGIBILITY_ONLY")
  private boolean isValueStringNull() {
    return FormatChecker
        .isDefined(discountType())
            ? (Arrays
                .asList(new BankingProductDiscountType[] {
                    BankingProductDiscountType.ELIGIBILITY_ONLY})
                .contains(discountType()) ? !FormatChecker.isNotEmpty(additionalValue())
                    : true)
            : true;
  }
}
