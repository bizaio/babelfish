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
package io.biza.babelfish.cdr.response.container;

import io.biza.babelfish.cdr.model.common.CommonOrganisation;
import io.biza.babelfish.cdr.model.common.CommonPerson;
import io.biza.babelfish.enumerations.cdr.PayloadTypeCustomer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode


@Schema(
    description = "Object containing either a CommonPerson or CommonOrganisation object defineed by a type")
public abstract class ResponseCommonCustomerData<T> {
  @Schema(description = "The type of customer object that is present", required = true)
  @JsonProperty("customerUType")
  @NotNull
  @Valid
  PayloadTypeCustomer type;

  public PayloadTypeCustomer type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeCustomer type) {
    setType(type);
    return (T) this;
  }

  @Schema(description = "The Person Record for the Customer")
  @JsonProperty("person")
  public CommonPerson<?> person;

  public CommonPerson<?> person() {
    return getPerson();
  }

  @SuppressWarnings("unchecked")
  public T person(CommonPerson<?> person) {
    setPerson(person);
    return (T) this;
  }

  @Schema(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  public CommonOrganisation<?> organisation;

  public CommonOrganisation<?> organisation() {
    return getOrganisation();
  }

  @SuppressWarnings("unchecked")
  public T organisation(CommonOrganisation<?> organisation) {
    setOrganisation(organisation);
    return (T) this;
  }
}
