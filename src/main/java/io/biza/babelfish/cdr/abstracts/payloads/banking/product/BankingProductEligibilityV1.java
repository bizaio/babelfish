package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductEligibilityType;
import io.biza.babelfish.cdr.enumerations.BankingProductFeatureType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductEligibilityV1 {

  public abstract BankingProductEligibilityType eligibilityType();

  public abstract String additionalValue();

  public abstract String additionalInfo();


  @AssertTrueBabelfish(message = "Other Criteria: Additional Information must be populated",
      fields = {"additionalInfo"})
  private boolean isInfoDefined() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.OTHER)
            ? FormatChecker.isDefined(additionalInfo())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Minimum Age: Should specify an integer of the minimum age in years (eg. 18)",
      fields = {"additionalValue"})
  private boolean isMinAgeValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.MIN_AGE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Maximum Age: Should specify an integer of the minimum age in years (eg. 65)",
      fields = {"additionalValue"})
  private boolean isMaxAgeValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.MAX_AGE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isPositiveInteger(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Minimum Income: Should be specified in AUD Decimal Format (eg. 40000.00)",
      fields = {"additionalValue"})
  private boolean isMinIncomeValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.MIN_INCOME)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Minimum Turnover: Should be specified in AUD Decimal Format (eg. 40000.00)",
      fields = {"additionalValue"})
  private boolean isMinTurnoverValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.MIN_TURNOVER)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Employment Status: A description of the employment status required",
      fields = {"additionalValue"})
  private boolean isEmploymentStatusValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.EMPLOYMENT_STATUS)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Residency Status: A description of the residency status required",
      fields = {"additionalValue"})
  private boolean isResidencyStatusValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.RESIDENCY_STATUS)
            ? FormatChecker.isNotEmpty(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(message = "Other Criteria: Additional Information must be populated",
      fields = {"additionalInfo"})
  private boolean isOtherCriteriaValid() {
    return FormatChecker.isDefined(eligibilityType())
        && eligibilityType().equals(BankingProductEligibilityType.OTHER)
            ? FormatChecker.isNotEmpty(additionalInfo())
            : true;
  }

  @AssertTrueBabelfish(message = "Should not be specified for this Eligibility Type",
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
