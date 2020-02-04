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
package io.biza.babelfish.cdr.models.responses;

import io.biza.babelfish.cdr.abstracts.responses.CDRResponseV1;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDataV1;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Response containing a data object with customer details of either PERSON or ORGANISATION object", name = "ResponseCommonCustomerV1")
public class ResponseCommonCustomerV1 extends CDRResponseV1 {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @Valid
  ResponseCommonCustomerDataV1 data;
  
  @Schema(description = "The Links Object", required = true)
  @JsonProperty("links")
  @NotNull
  @Valid
  LinksV1 links;

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @JsonProperty("meta")
  @Valid
  MetaV1 meta;

}
