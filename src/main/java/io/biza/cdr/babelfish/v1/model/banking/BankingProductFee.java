/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.banking;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;

@Valid
public class BankingProductFee extends io.biza.cdr.babelfish.model.banking.BankingProductFee<BankingProductFee> {
  @AssertTrue(message = "Additional Value must be a Duration String when Fee type is PERIODIC")
  private boolean isValueDuration() {
    return Arrays.asList(new BankingProductFeeType[] {BankingProductFeeType.PERIODIC})
        .contains(feeType()) ? FormatChecker.isDuration(additionalValue()) : true;
  }

  @AssertTrue(message = "One of amount, balanceRate, transactionRate or accruedRate is mandatory")
  private boolean isAmountOrRateSet() {
    return amount() != null || balanceRate() != null || transactionRate() != null
        || accruedRate() != null;
  }
}
