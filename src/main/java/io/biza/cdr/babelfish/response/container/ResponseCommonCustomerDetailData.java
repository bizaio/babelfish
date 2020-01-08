/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.response.container;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.common.CommonOrganisationDetail;
import io.biza.cdr.babelfish.v1.model.common.CommonPersonDetail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode


@BabelFishModel(
    description = "Object containing either a PersonDetail or OrganisationDetail object defined by a type attribute")
public abstract class ResponseCommonCustomerDetailData<T> {
  @BabelFishModelProperty(description = "The type of customer object that is present",
      required = true)
  @JsonProperty("customerUType")
  @NotNull
  @NonNull
  public PayloadTypeCustomer type;

  public PayloadTypeCustomer type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeCustomer type) {
    setType(type);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The Person Record for the Customer")
  @JsonProperty("person")
  public CommonPersonDetail person;

  public CommonPersonDetail person() {
    return getPerson();
  }

  @SuppressWarnings("unchecked")
  public T person(CommonPersonDetail person) {
    setPerson(person);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  public CommonOrganisationDetail organisation;

  public CommonOrganisationDetail organisation() {
    return getOrganisation();
  }

  @SuppressWarnings("unchecked")
  public T organisation(CommonOrganisationDetail organisation) {
    setOrganisation(organisation);
    return (T) this;
  }
}
