/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.v1.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeBankingDomesticPayee;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class BankingDomesticPayee
    extends io.biza.babelfish.cdr.model.banking.BankingDomesticPayee<BankingDomesticPayee> {
  @AssertTrue(
      message = "Payee Account Type must supply matching Payee Account Type Specific Information")
  private boolean isAccountTypeCorrectlyPopulated() {
    if (payeeAccountType() == null) {
      return true;
    }

    if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.ACCOUNT)) {
      return account() != null && card() == null && payId() == null ? true : false;
    } else if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.CARD)) {
      return card() != null && account() == null && payId() == null ? true : false;
    } else if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.PAY_ID)) {
      return payId() != null && account() == null && card() == null ? true : false;
    }
    return false;
  }


}
