package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.util.Arrays;
import java.util.List;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountEligibilityV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductDiscountV1 {

  public abstract BankingProductDiscountType discountType();

  public abstract List<BankingProductDiscountEligibilityV1> eligibility();

  public abstract String additionalValue();


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
