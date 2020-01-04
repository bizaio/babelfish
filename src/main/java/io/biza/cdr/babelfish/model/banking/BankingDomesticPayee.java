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
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Representation of a Domestic Payee Detail")
public abstract class BankingDomesticPayee {
  @BabelFishModelProperty(description = "Type of account object included.", required = true)
  @NotNull
  @NonNull
  PayloadTypeBankingDomesticPayee payeeAccountUType;

  private BankingDomesticPayeeAccount account;

  private BankingDomesticPayeeCard card;

  private BankingDomesticPayeePayId payId;

  @AssertTrue(
      message = "Payee Account Type must supply matching Payee Account Type Specific Information")
  private boolean isAccountTypeCorrect() {
    if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.ACCOUNT)) {
      return account != null && card == null && payId == null ? true : false;
    } else if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.CARD)) {
      return card != null && account == null && payId == null ? true : false;
    } else if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.PAY_ID)) {
      return payId != null && account == null && card == null ? true : false;
    }
    return false;
  }
}
