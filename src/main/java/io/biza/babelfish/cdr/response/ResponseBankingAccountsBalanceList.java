/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.response.container.ResponseBankingAccountsBalanceListData;
import io.biza.babelfish.deprecated.cdr.model.CDRResponsePaginated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


@Schema(description = "Response containing a Banking Accounts Balances List")
public abstract class ResponseBankingAccountsBalanceList<T> extends CDRResponsePaginated<T> {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @Valid
  ResponseBankingAccountsBalanceListData<?> data;

  public ResponseBankingAccountsBalanceListData<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(ResponseBankingAccountsBalanceListData<?> data) {
    setData(data);
    return (T) this;
  }
}
