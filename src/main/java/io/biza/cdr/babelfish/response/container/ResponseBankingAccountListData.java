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
package io.biza.cdr.babelfish.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.banking.BankingAccount;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode
@BabelFishModel(description = "Response containing a list of Banking Accounts")
public abstract class ResponseBankingAccountListData<T> {
  @BabelFishModelProperty(
      description = "The list of accounts returned. If the filter results in an empty set then this array may have no records",
      required = true)
  @JsonProperty("accounts")
  @NotNull
  @NonNull
  @Valid
  public List<BankingAccount<?>> accounts;

  public List<BankingAccount<?>> accounts() {
    return getAccounts();
  }

  @SuppressWarnings("unchecked")
  public T accounts(List<BankingAccount<?>> accounts) {
    setAccounts(accounts);
    return (T) this;
  }
}
