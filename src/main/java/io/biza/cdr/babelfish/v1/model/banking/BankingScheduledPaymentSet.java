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

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = true)


public class BankingScheduledPaymentSet extends
    io.biza.cdr.babelfish.model.banking.BankingScheduledPaymentSet<BankingScheduledPaymentSet> {

  @AssertTrue(message = "amount must be set when isAmountCalculated is FALSE")
  private boolean isAmountSet() {
    if (isAmountCalculated() == null || !isAmountCalculated()) {
      return amount() != null;
    } else {
      return true;
    }
  }

}
