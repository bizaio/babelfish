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

import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.v1.model.common.CommonOrganisationDetail;
import io.biza.babelfish.cdr.v1.model.common.CommonPersonDetail;
import io.swagger.v3.oas.annotations.media.Schema;
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
@ToString(callSuper = true)
@EqualsAndHashCode
@Schema(
    description = "Object containing either a PersonDetail or OrganisationDetail object defined by a type attribute")
public abstract class ResponseCommonCustomerDetailData<T> {
  @Schema(description = "The type of customer object that is present", required = true)
  @JsonProperty("customerUType")
  @NotNull
  @NonNull
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
  @Valid
  CommonPersonDetail person;

  public CommonPersonDetail person() {
    return getPerson();
  }

  @SuppressWarnings("unchecked")
  public T person(CommonPersonDetail person) {
    setPerson(person);
    return (T) this;
  }

  @Schema(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  @Valid
  CommonOrganisationDetail organisation;

  public CommonOrganisationDetail organisation() {
    return getOrganisation();
  }

  @SuppressWarnings("unchecked")
  public T organisation(CommonOrganisationDetail organisation) {
    setOrganisation(organisation);
    return (T) this;
  }
}
