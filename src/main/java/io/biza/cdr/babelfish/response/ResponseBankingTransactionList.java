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
import io.biza.cdr.babelfish.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.response.container.ResponseBankingTransactionListData;
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
@EqualsAndHashCode(callSuper = true)


public abstract class ResponseBankingTransactionList<T> extends CDRResponsePaginated<T> {
  @BabelFishModelProperty(required = true)
  @JsonProperty("data")
  @NotNull
  @NonNull
  public ResponseBankingTransactionListData<?> data;

  public ResponseBankingTransactionListData<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(ResponseBankingTransactionListData<?> data) {
    setData(data);
    return (T) this;
  }
}
