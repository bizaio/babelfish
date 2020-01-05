/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Representation of a Domestic Payee Detail")
public abstract class BankingDomesticPayee<T extends BankingDomesticPayee<T>> {
  @BabelFishModelProperty(description = "Type of account object included.", required = true)
  @NotNull
  @NonNull
  PayloadTypeBankingDomesticPayee payeeAccountUType;

  public PayloadTypeBankingDomesticPayee payeeAccountUType() {
    return getPayeeAccountUType();
  }

  @SuppressWarnings("unchecked")
  public T payeeAccountUType(PayloadTypeBankingDomesticPayee payeeAccountUType) {
    setPayeeAccountUType(payeeAccountUType);
    return (T) this;
  }

  private BankingDomesticPayeeAccount<?> account;

  public BankingDomesticPayeeAccount<?> account() {
    return getAccount();
  }

  @SuppressWarnings("unchecked")
  public T account(BankingDomesticPayeeAccount<?> account) {
    setAccount(account);
    return (T) this;
  }

  private BankingDomesticPayeeCard<?> card;

  public BankingDomesticPayeeCard<?> card() {
    return getCard();
  }

  @SuppressWarnings("unchecked")
  public T card(BankingDomesticPayeeCard<?> card) {
    setCard(card);
    return (T) this;
  }

  private BankingDomesticPayeePayId<?> payId;

  public BankingDomesticPayeePayId<?> payId() {
    return getPayId();
  }

  @SuppressWarnings("unchecked")
  public T payId(BankingDomesticPayeePayId<?> payId) {
    setPayId(payId);
    return (T) this;
  }

}
