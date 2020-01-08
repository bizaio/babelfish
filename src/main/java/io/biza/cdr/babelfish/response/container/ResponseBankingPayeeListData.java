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
package io.biza.cdr.babelfish.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.banking.BankingPayee;
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


@BabelFishModel(description = "Object containing a list of BankingPayee objects")
public abstract class ResponseBankingPayeeListData<T> {
  @BabelFishModelProperty(description = "The list of authorisations returned", required = true)
  @JsonProperty("payees")
  @NotNull
  @NonNull
  public List<BankingPayee<?>> payees;

  public List<BankingPayee<?>> payees() {
    return getPayees();
  }

  @SuppressWarnings("unchecked")
  public T payees(List<BankingPayee<?>> data) {
    setPayees(data);
    return (T) this;
  }
}
