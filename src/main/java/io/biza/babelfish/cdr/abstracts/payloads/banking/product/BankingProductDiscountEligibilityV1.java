package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountEligibilityType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductDiscountEligibilityV1 {

  public abstract BankingProductDiscountEligibilityType discountEligibilityType();

  public abstract String additionalValue();

  public abstract String additionalInfo();

  @AssertTrueBabelfish(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE",
      fields = {"additionalValue"})
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

  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER",
      fields = {"additionalValue"})
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

  @AssertTrueBabelfish(
      message = "Additional Value must be an Duration String when Eligibility type is INTRODUCTORY",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays
            .asList(new BankingProductDiscountEligibilityType[] {
                BankingProductDiscountEligibilityType.INTRODUCTORY})
            .contains(discountEligibilityType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must be String when Discount Eligibility Type is EMPLOYMENT_STATUS or RESIDENCY_STATUS",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(discountEligibilityType())
        ? (Arrays.asList(new BankingProductDiscountEligibilityType[] {
            BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS,
            BankingProductDiscountEligibilityType.RESIDENCY_STATUS}).contains(
                discountEligibilityType()) ? FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }


  @AssertTrueBabelfish(
      message = "Additional Value should be null when Discount Eligibility type is BUSINESS, STAFF, NATURAL_PERSON or OTHER",
      fields = {"additionalValue"})
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

  @AssertTrueBabelfish(
      message = "Additional Information must be populated when Discount Eligibility type is OTHER",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(discountEligibilityType()) ? (Arrays.asList(
        new BankingProductDiscountEligibilityType[] {BankingProductDiscountEligibilityType.OTHER})
        .contains(discountEligibilityType()) ? FormatChecker.isDefined(additionalInfo()) : true)
        : true;
  }
}
