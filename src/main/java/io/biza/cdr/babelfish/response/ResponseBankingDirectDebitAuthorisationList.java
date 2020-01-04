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
package io.biza.cdr.babelfish.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Accessors
@Valid
public interface ResponseBankingDirectDebitAuthorisationList extends CDRResponsePaginated {
  
  @BabelFishModelProperty(required = true)
  @JsonGetter("data")
  public ResponseBankingDirectDebitAuthorisationListData getData();

  @JsonSetter("data")
  public void setData(@NotNull ResponseBankingDirectDebitAuthorisationListData data);

  public default ResponseBankingDirectDebitAuthorisationList data(ResponseBankingDirectDebitAuthorisationListData data) {
    setData(data);
    return this;
  }
}
