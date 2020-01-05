/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Banking Transaction Detailed Information",
    parent = BankingTransaction.class)
public abstract class BankingTransactionDetail<T extends BankingTransactionDetail<T>> {
  @JsonUnwrapped
  @BabelFishModelProperty(hidden = true)
  @Valid
  BankingTransaction<?> bankingTransaction;

  public BankingTransaction<?> bankingTransaction() {
    return getBankingTransaction();
  }

  @SuppressWarnings("unchecked")
  public T bankingTransaction(BankingTransaction<?> bankingTransaction) {
    setBankingTransaction(bankingTransaction);
    return (T) this;
  }

  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  BankingTransactionDetailExtendedData<?> extendedData;

  public BankingTransactionDetailExtendedData<?> extendedData() {
    return getExtendedData();
  }

  @SuppressWarnings("unchecked")
  public T extendedData(BankingTransactionDetailExtendedData<?> extendedData) {
    setExtendedData(extendedData);
    return (T) this;
  }
}
