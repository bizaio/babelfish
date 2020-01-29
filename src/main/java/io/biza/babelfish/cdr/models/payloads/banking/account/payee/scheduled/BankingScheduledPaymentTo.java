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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentTo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingScheduledPaymentTo extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled.BankingScheduledPaymentTo<BankingScheduledPaymentTo> {
  @AssertTrue(
      message = "One and only one of accountId, payeeId, domestic, biller, international should be populated based on type")
  private boolean isUTypePopulated() {
    if (type() == null) {
      return true;
    }
    if (type().equals(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID)) {
      return accountId() != null && payeeId() == null && domestic() == null && biller() == null
          && international() == null;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentTo.PAYEE_ID)) {
      return payeeId() != null && accountId() == null && domestic() == null && biller() == null
          && international() == null;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentTo.DOMESTIC)) {
      return domestic() != null && accountId() == null && payeeId() == null && biller() == null
          && international() == null;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentTo.BILLER)) {
      return biller() != null && accountId() == null && payeeId() == null && domestic() == null
          && international() == null;
    } else if (type().equals(PayloadTypeBankingScheduledPaymentTo.INTERNATIONAL)) {
      return international() != null && accountId() == null && payeeId() == null
          && domestic() == null && biller() == null;
    }
    return false;
  }
}
