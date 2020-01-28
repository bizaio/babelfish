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

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingAccount;
import io.biza.babelfish.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingAccountDetail
    extends io.biza.babelfish.cdr.model.banking.BankingAccountDetail<BankingAccountDetail> {

  @AssertTrue(message = "Account Number must not be an unmasked PAN")
  private boolean isAccountNumberUnmaskedPan() {
    return FormatChecker.isPanNumber(accountNumber()) ? false : true;
  }

  @AssertTrue(message = "Account Type must supply matching Account Type Specific Information")
  private boolean isAccountTypeCorrect() {
    // Return true if not defined, @NotNull will pick this up later
    if (specificAccountUType() == null) {
      return true;
    }

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
