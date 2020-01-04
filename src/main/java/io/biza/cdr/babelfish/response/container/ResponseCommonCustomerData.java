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
package io.biza.cdr.babelfish.response.container;

import io.biza.cdr.babelfish.model.banking.BankingPayee;
import io.biza.cdr.babelfish.model.common.CommonOrganisation;
import io.biza.cdr.babelfish.model.common.CommonPerson;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Valid
public interface ResponseCommonCustomerData {

  @BabelFishModelProperty(description = "The type of customer object that is present",
      required = true)
  @JsonGetter("customerUType")
  public PayloadTypeCustomer getType();

  @JsonSetter("customerUType")
  public void setType(@NotNull PayloadTypeCustomer type);

  public default ResponseCommonCustomerData type(@NotNull PayloadTypeCustomer type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(description = "The Person Record for the Customer")
  @JsonGetter("person")
  public CommonPerson getPerson();

  @JsonSetter("person")
  public void setPerson(CommonPerson person);

  public default ResponseCommonCustomerData person(CommonPerson person) {
    setPerson(person);
    return this;
  }

  @BabelFishModelProperty(description = "The Organisation Record for the Customer")
  @JsonGetter("organisation")
  public CommonOrganisation getOrganisation();

  @JsonSetter("organisation")
  public void setOrganisation(CommonOrganisation organisation);

  public default ResponseCommonCustomerData organisation(CommonOrganisation organisation) {
    setOrganisation(organisation);
    return this;
  }

}
