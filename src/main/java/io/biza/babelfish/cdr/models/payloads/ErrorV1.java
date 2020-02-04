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
package io.biza.babelfish.cdr.models.payloads;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error Information")
public class ErrorV1 {
  @Schema(description = "Must be one of the following: 0001 – Account not able to be found",
      required = true)
  @JsonProperty("code")
  @NotNull
  String code;

  @Schema(description = "Must be one of the following: Invalid account", required = true)
  @JsonProperty("title")
  @NotNull
  String title;

  @Schema(description = "ID of the account not found", required = true)
  @JsonProperty("detail")
  @NotNull
  String detail;

  @Schema(description = "Optional additional data for specific error types")
  @JsonProperty("meta")
  Object meta;

}
