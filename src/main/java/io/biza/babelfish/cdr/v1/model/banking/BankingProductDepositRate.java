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
import io.biza.babelfish.enumerations.cdr.BankingProductDepositRateType;
import io.biza.babelfish.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingProductDepositRate extends
    io.biza.babelfish.cdr.model.banking.product.BankingProductDepositRate<BankingProductDepositRate> {
  @AssertTrue(
      message = "Additional Value must be a Duration String when Fee type is FIXED or INTRODUCTORY")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.FIXED,
            BankingProductDepositRateType.INTRODUCTORY}).contains(depositRateType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(
      message = "Additional Value must String when Deposit Rate Type is BONUS, BUNDLE_BONUS, FLOATING or MARKET_LINKED")
  private boolean isValueString() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.BONUS,
            BankingProductDepositRateType.BUNDLE_BONUS, BankingProductDepositRateType.FLOATING,
            BankingProductDepositRateType.MARKET_LINKED}).contains(depositRateType())
                ? FormatChecker.isNotEmpty(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(message = "Additional Value should be null when Deposit Rate Type is VARIABLE")
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays
            .asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.VARIABLE})
            .contains(depositRateType()) ? !FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }
}
