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
import io.biza.cdr.babelfish.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.model.banking.BankingPayee;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Accessors
@Valid
public interface ResponseBankingPayeeListData extends CDRResponsePaginated {
    
    @BabelFishModelProperty(description = "The list of authorisations returned", required = true)
    @JsonGetter("payees")
    public List<BankingPayee> getPayees();

    @JsonSetter("payees")
    public void setPayees(@NotNull List<BankingPayee> payees);

    public default ResponseBankingPayeeListData payees(@NotNull List<BankingPayee> payees) {
      setPayees(payees);
      return this;
    }
}
