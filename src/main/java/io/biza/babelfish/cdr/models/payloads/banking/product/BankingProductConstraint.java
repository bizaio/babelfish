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
package io.biza.babelfish.cdr.models.payloads.banking.product;

import java.util.Arrays;
import javax.validation.Valid;
import io.biza.babelfish.cdr.enumerations.BankingProductConstraintType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingProductConstraint
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductConstraint<BankingProductConstraint> {
  @AssertTrueBabelfish(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_BALANCE, MAX_BALANCE, OPENING_BALANCE, MAX_LIMIT or MIN_LIMIT",
      fields = { "additionalValue" })
  private boolean isValueAmount() {
    return FormatChecker.isDefined(constraintType()) ? (Arrays
        .asList(new BankingProductConstraintType[] {BankingProductConstraintType.MIN_BALANCE,
            BankingProductConstraintType.MAX_BALANCE, BankingProductConstraintType.OPENING_BALANCE,
            BankingProductConstraintType.MIN_LIMIT, BankingProductConstraintType.MAX_LIMIT})
        .contains(constraintType())
            ? FormatChecker.isDefined(additionalValue())
                && FormatChecker.isAmountString(additionalValue())
            : true)
        : true;
  }
}
