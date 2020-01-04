/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.banking;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;

@Valid
public class BankingProductConstraint
    extends io.biza.cdr.babelfish.model.banking.BankingProductConstraint {
  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_BALANCE, MAX_BALANCE, OPENING_BALANCE, MAX_LIMIT or MIN_LIMIT")
  private boolean isValueAmount() {
    return Arrays.asList(new BankingProductConstraintType[] {
        BankingProductConstraintType.MIN_BALANCE, BankingProductConstraintType.MAX_BALANCE,
        BankingProductConstraintType.OPENING_BALANCE, BankingProductConstraintType.MIN_LIMIT,
        BankingProductConstraintType.MAX_LIMIT
    }).contains(constraintType()) ? FormatChecker.isDecimal(additionalValue()) : true;
  }
}
