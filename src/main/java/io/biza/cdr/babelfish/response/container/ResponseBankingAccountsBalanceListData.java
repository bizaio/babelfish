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
package io.biza.cdr.babelfish.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.model.banking.BankingAccount;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
public interface ResponseBankingAccountsBalanceListData {

    @BabelFishModelProperty(
        description = "The list of accounts returned. If the filter results in an empty set then this array may have no records",
        required = true)
    @JsonGetter("balances")
    public List<BankingBalance> getBalances();

    @JsonSetter("balances")
    public void setBalances(@NotNull List<BankingBalance> balances);

    public default ResponseBankingAccountsBalanceListData balances(@NotNull List<BankingBalance> balances) {
      setBalances(balances);
      return this;
    }
}
