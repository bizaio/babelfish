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
import io.biza.babelfish.cdr.v1.enumerations.BankingProductFeeType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingProductFee
    extends io.biza.babelfish.cdr.model.banking.BankingProductFee<BankingProductFee> {
  @AssertTrue(message = "Additional Value must be a Duration String when Fee type is PERIODIC")
  private boolean isValueDuration() {
    return FormatChecker.isDefined(feeType())
        ? (Arrays.asList(new BankingProductFeeType[] {BankingProductFeeType.PERIODIC})
            .contains(feeType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrue(message = "One of amount, balanceRate, transactionRate or accruedRate is mandatory")
  private boolean isAmountOrRateSet() {
    return FormatChecker.isDefined(amount()) || FormatChecker.isDefined(balanceRate())
        || FormatChecker.isDefined(transactionRate()) || FormatChecker.isDefined(accruedRate());
  }

  @AssertTrue(
      message = "Additional Value should be null when Fee Type is TRANSACTION, WITHDRAWAL, DEPOSIT, PAYMENT, PURCHASE, EVENT, UPFRONT or EXIT")
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(feeType()) ? (Arrays
        .asList(new BankingProductFeeType[] {BankingProductFeeType.TRANSACTION,
            BankingProductFeeType.WITHDRAWAL, BankingProductFeeType.DEPOSIT,
            BankingProductFeeType.PAYMENT, BankingProductFeeType.PURCHASE,
            BankingProductFeeType.EVENT, BankingProductFeeType.UPFRONT, BankingProductFeeType.EXIT})
        .contains(feeType()) ? !FormatChecker.isDefined(additionalValue()) : true) : true;
  }
}
