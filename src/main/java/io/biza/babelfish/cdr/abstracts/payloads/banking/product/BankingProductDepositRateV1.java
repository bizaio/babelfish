package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductDepositRateType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductDepositRateV1 {

  public abstract BankingProductDepositRateType depositRateType();

  public abstract String additionalValue();

  @AssertTrueBabelfish(
      message = "Additional Value must be a Duration String when Fee type is FIXED or INTRODUCTORY",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.FIXED,
            BankingProductDepositRateType.INTRODUCTORY}).contains(depositRateType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must String when Deposit Rate Type is BONUS, BUNDLE_BONUS, FLOATING or MARKET_LINKED",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.BONUS,
            BankingProductDepositRateType.BUNDLE_BONUS, BankingProductDepositRateType.FLOATING,
            BankingProductDepositRateType.MARKET_LINKED}).contains(depositRateType())
                ? FormatChecker.isNotEmpty(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Deposit Rate Type is VARIABLE",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays
            .asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.VARIABLE})
            .contains(depositRateType()) ? !FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }
}
