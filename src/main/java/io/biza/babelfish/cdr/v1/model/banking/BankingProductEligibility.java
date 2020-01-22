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
package io.biza.babelfish.cdr.v1.model.banking;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductEligibilityType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingProductEligibility extends
    io.biza.babelfish.cdr.model.banking.BankingProductEligibility<BankingProductEligibility> {
  @AssertTrue(message = "Additional Information must be populated when Eligibility type is OTHER")
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.OTHER})
            .contains(eligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE")
  private boolean isValuePositiveInteger() {
    return FormatChecker.isDefined(eligibilityType())
        ? (Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.MIN_AGE,
            BankingProductEligibilityType.MAX_AGE}).contains(eligibilityType())
                ? FormatChecker.isPositiveInteger(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER")
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

  @AssertTrue(
      message = "Additional Value must String when Eligibility type is EMPLOYMENT_STATUS or RESIDENCY_STATUS")
  private boolean isValueString() {
    return FormatChecker.isDefined(eligibilityType()) ? (Arrays.asList(
        new BankingProductEligibilityType[] {BankingProductEligibilityType.EMPLOYMENT_STATUS,
            BankingProductEligibilityType.RESIDENCY_STATUS})
        .contains(eligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true) : true;
  }

  @AssertTrue(
      message = "Additional Value should be null when Eligibility type is STAFF, STUDENT, NATURAL_PERSON, BUSINESS, PENSION_RECIPIENT or OTHER")
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
