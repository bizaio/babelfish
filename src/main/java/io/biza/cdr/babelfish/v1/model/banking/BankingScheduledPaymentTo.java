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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPayment;

@Valid
public class BankingScheduledPaymentTo
    extends io.biza.cdr.babelfish.model.banking.BankingScheduledPaymentTo {
  @AssertTrue(
      message = "One and only one of accountId, payeeId, domestic, biller, international should be populated based on toUType")
  private boolean isUTypePopulated() {
    if (toUType().equals(PayloadTypeBankingScheduledPayment.ACCOUNT_ID)) {
      return accountId() != null && payeeId() == null && domestic() == null && biller() == null
          && international() == null;
    } else if (toUType().equals(PayloadTypeBankingScheduledPayment.PAYEE_ID)) {
      return payeeId() != null && accountId() == null && domestic() == null && biller() == null
          && international() == null;
    } else if (toUType().equals(PayloadTypeBankingScheduledPayment.DOMESTIC)) {
      return domestic() != null && accountId() == null && payeeId() == null && biller() == null
          && international() == null;
    } else if (toUType().equals(PayloadTypeBankingScheduledPayment.BILLER)) {
      return biller() != null && accountId() == null && payeeId() == null && domestic() == null
          && international() == null;
    } else if (toUType().equals(PayloadTypeBankingScheduledPayment.INTERNATIONAL)) {
      return international() != null && accountId() == null && payeeId() == null
          && domestic() == null && biller() == null;
    }
    return false;
  }
}
