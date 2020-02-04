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
package io.biza.babelfish.cdr.models.responses.container;

import io.biza.babelfish.cdr.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Object containing either a CommonPerson or CommonOrganisation object defineed by a type", name = "ResponseCommonCustomerData")
public class ResponseCommonCustomerDataV1 {
  @Schema(description = "The type of customer object that is present", required = true)
  @JsonProperty("customerUType")
  @NotNull
  @Valid
  PayloadTypeCustomer type;

  @Schema(description = "The Person Record for the Customer")
  @JsonProperty("person")
  public CommonPersonV1 person;

  @Schema(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  public CommonOrganisationV1 organisation;
  
  @AssertTrue(message = "Only person should be populated when customer type is set to PERSON")
  private boolean isPersonPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.PERSON)) {
      return person() != null && organisation() == null;
    } else {
      return true;
    }
  }

  @AssertTrue(
      message = "Only organisation should be populated when customer type is set to ORGANISATION")
  private boolean isOrganisationPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.ORGANISATION)) {
      return organisation() != null && person() == null;
    } else {
      return true;
    }
  }
}
