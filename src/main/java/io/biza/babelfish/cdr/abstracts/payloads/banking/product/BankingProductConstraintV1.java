package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductConstraintType;
import io.biza.babelfish.cdr.enumerations.BankingProductFeatureType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductConstraintV1 {

  public abstract BankingProductConstraintType constraintType();

  public abstract String additionalValue();

  @AssertTrueBabelfish(
      message = "Minimum Balance: Should be specified in AmountString format (eg. 1000.00)",
      fields = {"additionalValue"})
  private boolean isMinBalanceValid() {
    return FormatChecker.isDefined(constraintType())
        && constraintType().equals(BankingProductConstraintType.MIN_BALANCE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Maximum Balance: Should be specified in AmountString format (eg. 1000.00)",
      fields = {"additionalValue"})
  private boolean isMaxBalanceValid() {
    return FormatChecker.isDefined(constraintType())
        && constraintType().equals(BankingProductConstraintType.MAX_BALANCE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Opening Balance: Should be specified in AmountString format (eg. 1000.00)",
      fields = {"additionalValue"})
  private boolean isOpeningBalanceValid() {
    return FormatChecker.isDefined(constraintType())
        && constraintType().equals(BankingProductConstraintType.OPENING_BALANCE)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Minimum Limit: Should be specified in AmountString format (eg. 1000.00)",
      fields = {"additionalValue"})
  private boolean isMinimumLimitValid() {
    return FormatChecker.isDefined(constraintType())
        && constraintType().equals(BankingProductConstraintType.MIN_LIMIT)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Maximum Limit: Should be specified in AmountString format (eg. 1000.00)",
      fields = {"additionalValue"})
  private boolean isMaximumLimitValid() {
    return FormatChecker.isDefined(constraintType())
        && constraintType().equals(BankingProductConstraintType.MAX_LIMIT)
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true;
  }
}
