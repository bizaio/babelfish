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
import io.biza.babelfish.deprecated.cdr.model.CDRResponse;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDetailV2;
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
@Schema(description = "Response containing a single BankingProductDetail entry")
public abstract class ResponseBankingProductById<T> extends CDRResponse<T> {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @Valid
  BankingProductDetailV2 data;

  public BankingProductDetailV2 data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(BankingProductDetailV2 data) {
    setData(data);
    return (T) this;
  }
}
