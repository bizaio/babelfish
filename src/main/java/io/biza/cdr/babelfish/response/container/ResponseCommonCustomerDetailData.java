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

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.common.CommonOrganisationDetail;
import io.biza.cdr.babelfish.v1.model.common.CommonPersonDetail;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
public abstract class ResponseCommonCustomerDetailData {
  @BabelFishModelProperty(description = "The type of customer object that is present",
      required = true)
  @JsonProperty("customerUType")
  @NotNull
  @NonNull
  public PayloadTypeCustomer type;

  @BabelFishModelProperty(description = "The Person Record for the Customer")
  @JsonProperty("person")
  public CommonPersonDetail person;

  @BabelFishModelProperty(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  public CommonOrganisationDetail organisation;
}
