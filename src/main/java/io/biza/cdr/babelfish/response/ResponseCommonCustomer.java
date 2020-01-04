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

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingPayeeDetail;
import io.biza.cdr.babelfish.response.container.ResponseCommonCustomerData;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Valid
public interface ResponseCommonCustomer extends CDRResponse {

  @BabelFishModelProperty(required = true)
  @JsonGetter("data")
  public ResponseCommonCustomerData getData();

  @JsonSetter("data")
  public void setData(@NotNull ResponseCommonCustomerData data);

  public default ResponseCommonCustomer data(@NotNull ResponseCommonCustomerData data) {
    setData(data);
    return this;
  }

}
