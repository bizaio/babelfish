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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;

@Valid
public class BankingPayeeDetail
    extends io.biza.cdr.babelfish.model.banking.BankingPayeeDetail<BankingPayeeDetail> {
  @AssertTrue(
      message = "One and only one of domestic, biller and international should be populated based on payeeUType")
  private boolean isUTypePopulated() {
    if (payeeUType().equals(PayloadTypeBankingPayee.DOMESTIC)) {
      return domestic() != null && biller() == null && international() == null;
    } else if (payeeUType().equals(PayloadTypeBankingPayee.BILLER)) {
      return biller() != null && domestic() == null && international() == null;
    } else if (payeeUType().equals(PayloadTypeBankingPayee.INTERNATIONAL)) {
      return international() != null && biller() == null && domestic() == null;
    }
    return false;
  }
}
