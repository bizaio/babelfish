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
package io.biza.cdr.babelfish.v1.response;

import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.v1.response.ResponseBankingDirectDebitAuthorisationListData;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import lombok.NonNull;

public class ResponseBankingDirectDebitAuthorisationList extends io.biza.cdr.babelfish.response.ResponseBankingDirectDebitAuthorisationList {

  public ResponseBankingDirectDebitAuthorisationList(
      @NonNull ResponseBankingDirectDebitAuthorisationListData data,
      @NonNull CDRResponse<LinksPaginated, MetaPaginated> metadata) {
    super(data, metadata);
  }

}