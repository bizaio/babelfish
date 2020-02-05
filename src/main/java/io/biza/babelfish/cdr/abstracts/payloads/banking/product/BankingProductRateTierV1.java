package io.biza.babelfish.cdr.abstracts.payloads.banking.product;

import java.math.BigDecimal;
import io.biza.babelfish.cdr.enumerations.CommonUnitOfMeasureType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingProductRateTierV1 {

  public abstract CommonUnitOfMeasureType unitOfMeasure();

  public abstract BigDecimal minimumValue();

  public abstract BigDecimal maximumValue();

  @AssertTrueBabelfish(message = "Percentage based Rate Tier: Minimum value should be >=-1 and <=1",
      fields = {"minimumValue"})
  private boolean isMinimumValuePercentage() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.PERCENT)
        && FormatChecker.isDefined(minimumValue())
            ? (minimumValue().compareTo(new BigDecimal(-1)) >= -1
                && minimumValue().compareTo(new BigDecimal(-1)) <= 1)
            : true;
  }

  @AssertTrueBabelfish(message = "Percentage based Rate Tier: Maximum value should be >=-1 and <=1",
      fields = {"maximumValue"})
  private boolean isMaximumValuePercentage() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.PERCENT)
        && FormatChecker.isDefined(maximumValue())
            ? (maximumValue().compareTo(new BigDecimal(-1)) >= -1
                && maximumValue().compareTo(new BigDecimal(-1)) <= 1)
            : true;
  }

  @AssertTrueBabelfish(
      message = "Month based Rate Tier: Value should be a discrete number (eg. 0 or 0.00)",
      fields = {"minimumValue"})
  private boolean isMinimumValueMonth() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.MONTH)
        && FormatChecker.isDefined(minimumValue())
            ? minimumValue().stripTrailingZeros().scale() <= 0
            : true;
  }

  @AssertTrueBabelfish(
      message = "Month based Rate Tier: Value should be a discrete number (eg. 0 or 0.00)",
      fields = {"maximumValue"})
  private boolean isMaximumValueMonth() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.MONTH)
        && FormatChecker.isDefined(maximumValue())
            ? maximumValue().stripTrailingZeros().scale() <= 0
            : true;
  }

  @AssertTrueBabelfish(
      message = "Day based Rate Tier: Value should be a discrete number (eg. 0 or 0.00)",
      fields = {"minimumValue"})
  private boolean isMinimumValueDay() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.DAY)
        && FormatChecker.isDefined(minimumValue())
            ? minimumValue().stripTrailingZeros().scale() <= 0
            : true;
  }

  @AssertTrueBabelfish(
      message = "Day based Rate Tier: Value should be a discrete number (eg. 0 or 0.00)",
      fields = {"maximumValue"})
  private boolean isMaximumValueDay() {
    return FormatChecker.isDefined(unitOfMeasure())
        && unitOfMeasure().equals(CommonUnitOfMeasureType.DAY)
        && FormatChecker.isDefined(maximumValue())
            ? maximumValue().stripTrailingZeros().scale() <= 0
            : true;
  }

}
