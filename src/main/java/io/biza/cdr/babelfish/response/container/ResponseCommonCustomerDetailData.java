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

import io.biza.cdr.babelfish.model.common.CommonOrganisation;
import io.biza.cdr.babelfish.model.common.CommonPerson;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.v1.model.common.CommonOrganisationDetail;
import io.biza.cdr.babelfish.v1.model.common.CommonPersonDetail;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Valid
public interface ResponseCommonCustomerDetailData {
  
  @BabelFishModelProperty(description = "The type of customer object that is present",
      required = true)
  @JsonGetter("customerUType")
  public PayloadTypeCustomer getType();

  @JsonSetter("customerUType")
  public void setType(@NotNull PayloadTypeCustomer type);

  public default ResponseCommonCustomerDetailData type(@NotNull PayloadTypeCustomer type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(description = "The Person Record for the Customer")
  @JsonGetter("person")
  public CommonPersonDetail getPerson();

  @JsonSetter("person")
  public void setPerson(CommonPersonDetail person);

  public default ResponseCommonCustomerDetailData person(CommonPersonDetail person) {
    setPerson(person);
    return this;
  }

  @BabelFishModelProperty(description = "The Organisation Record for the Customer")
  @JsonGetter("organisation")
  public CommonOrganisationDetail getOrganisation();

  @JsonSetter("organisation")
  public void setOrganisation(CommonOrganisationDetail organisation);

  public default ResponseCommonCustomerDetailData organisation(CommonOrganisationDetail organisation) {
    setOrganisation(organisation);
    return this;
  }
  
}
