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
import io.biza.babelfish.cdr.model.banking.BankingAccountDetail;
import io.biza.babelfish.deprecated.cdr.model.CDRResponse;
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
@Schema(description = "Response containing a single Banking Account Detail")
public abstract class ResponseBankingAccountById<T> extends CDRResponse<T> {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @Valid
  BankingAccountDetail<?> data;

  public BankingAccountDetail<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(BankingAccountDetail<?> data) {
    setData(data);
    return (T) this;
  }
}
