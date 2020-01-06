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
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Domestic Payment Payee Details")
public abstract class BankingDomesticPayeeAccount<T> {
  @BabelFishModelProperty(description = "Name of the account to pay to", required = true)
  @NonNull
  @NotNull
  String accountName;

  public String accountName() {
    return getAccountName();
  }

  @SuppressWarnings("unchecked")
  public T accountName(String accountName) {
    setAccountName(accountName);
    return (T) this;
  }

  @BabelFishModelProperty(description = "BSB of the account to pay to", required = true)
  @NonNull
  @NotNull
  String bsb;

  public String bsb() {
    return getBsb();
  }

  @SuppressWarnings("unchecked")
  public T bsb(String bsb) {
    setBsb(bsb);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Number of the account to pay to", required = true)
  @NonNull
  @NotNull
  String accountNumber;

  public String accountNumber() {
    return getAccountNumber();
  }

  @SuppressWarnings("unchecked")
  public T accountNumber(String accountNumber) {
    setAccountNumber(accountNumber);
    return (T) this;
  }
}
