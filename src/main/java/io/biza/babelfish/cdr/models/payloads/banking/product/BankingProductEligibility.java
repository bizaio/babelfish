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
import io.biza.babelfish.cdr.enumerations.BankingProductEligibilityType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingProductEligibility extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductEligibility<BankingProductEligibility> {
  @AssertTrueBabelfish(
      message = "Additional Information must be populated when Eligibility type is OTHER",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.OTHER})
            .contains(eligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE",
      fields = {"additionalValue"})
  private boolean isValuePositiveInteger() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.MIN_AGE,
            BankingProductEligibilityType.MAX_AGE}).contains(eligibilityType())
                ? FormatChecker.isPositiveInteger(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER",
      fields = {"additionalValue"})
  private boolean isValueAmount() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {
            BankingProductEligibilityType.MIN_INCOME, BankingProductEligibilityType.MIN_TURNOVER})
            .contains(eligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isAmountString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must String when Eligibility type is EMPLOYMENT_STATUS or RESIDENCY_STATUS",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(eligibilityType()) ? (Arrays.asList(
        new BankingProductEligibilityType[] {BankingProductEligibilityType.EMPLOYMENT_STATUS,
            BankingProductEligibilityType.RESIDENCY_STATUS})
        .contains(eligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true) : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Eligibility type is STAFF, STUDENT, NATURAL_PERSON, BUSINESS, PENSION_RECIPIENT or OTHER",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.STAFF,
            BankingProductEligibilityType.NATURAL_PERSON, BankingProductEligibilityType.OTHER,
            BankingProductEligibilityType.BUSINESS, BankingProductEligibilityType.PENSION_RECIPIENT,
            BankingProductEligibilityType.STUDENT}).contains(eligibilityType())
                ? !FormatChecker.isNotEmpty(additionalValue())
                : true)
        : true;
  }
}
