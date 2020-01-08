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
package io.biza.cdr.babelfish.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingPayeeDetail;
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
@ToString
@EqualsAndHashCode(callSuper = true)


@BabelFishModel(description = "Response containing a Banking Payee By Identifier")
public abstract class ResponseBankingPayeeById<T> extends CDRResponse<T> {
  @BabelFishModelProperty(required = true)
  @JsonProperty("data")
  @NotNull
  @NonNull
  public BankingPayeeDetail<?> data;

  public BankingPayeeDetail<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(BankingPayeeDetail<?> data) {
    setData(data);
    return (T) this;
  }
}
