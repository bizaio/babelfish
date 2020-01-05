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
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingAccount;

@Valid
public class BankingAccountDetail extends io.biza.cdr.babelfish.model.banking.BankingAccountDetail {
  
  @AssertTrue(message = "Account Number must not be an unmasked PAN")
  private boolean isAccountNumberUnmaskedPan() {
    return FormatChecker.isPanNumber(accountNumber) ? false : true;
  }
  
  @AssertTrue(message = "Account Type must supply matching Account Type Specific Information")
  private boolean isAccountTypeCorrect() {
    if (specificAccountUType().equals(PayloadTypeBankingAccount.TERM_DEPOSIT)) {
      return termDeposit() != null && creditCard() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.CREDIT_CARD)) {
      return creditCard() != null && termDeposit() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.LOAN)) {
      return loan() != null && creditCard() == null && termDeposit() == null ? true : false;
    }
    return false;
  }
}
