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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingDomesticPayeeCard
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.domestic.BankingDomesticPayeeCard<BankingDomesticPayeeCard> {
  @AssertTrue(message = "Card Number MUST be Masked PAN Format")
  private boolean isPanMasked() {
    if (cardNumber() == null) {
      return true;
    }
    // We check masked PAN by checking for explicit types that are unmasked
    if (cardNumber().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")
        || cardNumber().matches("\\d{4} \\d{4} \\d{4} \\d{4}") || cardNumber().matches("\\d{16}")) {
      return false;
    } else {
      return true;
    }
  }
}
