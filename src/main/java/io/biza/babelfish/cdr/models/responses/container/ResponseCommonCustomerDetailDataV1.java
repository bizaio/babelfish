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
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationDetail;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonDetailV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Object containing either a PersonDetail or OrganisationDetail object defined by a type attribute",
    name = "ResponseCommonCustomerDetailData")
public class ResponseCommonCustomerDetailDataV1
    extends io.biza.babelfish.cdr.abstracts.responses.container.ResponseCommonCustomerDetailDataV1 {
  @Schema(description = "The type of customer object that is present", required = true)
  @JsonProperty("customerUType")
  @NotNull
  @Valid
  PayloadTypeCustomer type;

  @Schema(description = "The Person Record for the Customer")
  @JsonProperty("person")
  @Valid
  CommonPersonDetailV1 person;

  @Schema(description = "The Organisation Record for the Customer")
  @JsonProperty("organisation")
  @Valid
  CommonOrganisationDetail organisation;

}
