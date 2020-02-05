package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.BankingProductLendingRateType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductLendingRateV1 {

  public abstract BankingProductLendingRateType lendingRateType();

  public abstract String additionalValue();


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
