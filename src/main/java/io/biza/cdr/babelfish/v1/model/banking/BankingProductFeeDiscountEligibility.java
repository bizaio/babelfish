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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingProductFeeDiscountEligibility extends
    io.biza.cdr.babelfish.model.banking.BankingProductFeeDiscountEligibility<BankingProductFeeDiscountEligibility> {
  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE")
  private boolean isValuePositiveInteger() {
    return FormatChecker
        .isDefined(discountEligibilityType())
            ? (Arrays
                .asList(new BankingProductDiscountEligibilityType[] {
                    BankingProductDiscountEligibilityType.MIN_AGE,
                    BankingProductDiscountEligibilityType.MAX_AGE})
                .contains(discountEligibilityType())
                    ? FormatChecker.isPositiveInteger(additionalValue())
                    : true)
            : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER")
  private boolean isValueAmount() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays
            .asList(new BankingProductDiscountEligibilityType[] {
                BankingProductDiscountEligibilityType.MIN_INCOME,
                BankingProductDiscountEligibilityType.MIN_TURNOVER})
            .contains(discountEligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isAmountString(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Duration String when Eligibility type is INTRODUCTORY")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays
            .asList(new BankingProductDiscountEligibilityType[] {
                BankingProductDiscountEligibilityType.INTRODUCTORY})
            .contains(discountEligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDuration(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be String when Discount Eligibility Type is EMPLOYMENT_STATUS or RESIDENCY_STATUS")
  private boolean isValueString() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays.asList(new BankingProductDiscountEligibilityType[] {
            BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS,
            BankingProductDiscountEligibilityType.RESIDENCY_STATUS}).contains(
                discountEligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }


  @AssertTrue(
      message = "Additional Value should be null when Discount Eligibility type is BUSINESS, STAFF, NATURAL_PERSON or OTHER")
  private boolean isValueStringNull() {
    return FormatChecker
        .isDefined(discountEligibilityType())
            ? (Arrays
                .asList(new BankingProductDiscountEligibilityType[] {
                    BankingProductDiscountEligibilityType.BUSINESS,
                    BankingProductDiscountEligibilityType.STAFF,
                    BankingProductDiscountEligibilityType.NATURAL_PERSON,
                    BankingProductDiscountEligibilityType.OTHER})
                .contains(discountEligibilityType()) ? !FormatChecker.isNotEmpty(additionalValue())
                    : true)
            : true;
  }

  @AssertTrue(
      message = "Additional Information must be populated when Discount Eligibility type is OTHER")
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(discountEligibilityType()) ? (Arrays.asList(
        new BankingProductDiscountEligibilityType[] {BankingProductDiscountEligibilityType.OTHER})
        .contains(discountEligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

}
