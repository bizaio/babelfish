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
package io.biza.babelfish.cdr.models.payloads.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.PayloadTypeAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Physical Address Detail", name = "CommonPhysicalAddressV1")
public class CommonPhysicalAddressV1 extends io.biza.babelfish.cdr.abstracts.models.common.CommonPhysicalAddressV1 {
  @Schema(description = "The type of address object present", required = true)
  @JsonProperty("addressUType")
  @NotNull
  @Valid
  PayloadTypeAddress type;

  @Schema(description = "Address in Simple Address format")
  @JsonProperty("simple")
  @Valid
  CommonSimpleAddressV1 simple;

  @Schema(description = "Address in PAF Format")
  @JsonProperty("paf")
  @Valid
  CommonPAFAddressV1 paf;

}
