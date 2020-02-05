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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Valid
@ToString
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "Paginated Meta Details", name = "MetaPaginatedV1")
public class MetaPaginatedV1 extends io.biza.babelfish.cdr.abstracts.payloads.MetaPaginatedV1 {
  @Schema(description = "The total number of records in the full set.", required = true)
  @JsonProperty("totalRecords")
  @NotNull
  @Min(0)
  @Valid
  Integer totalRecords;

  @Schema(description = "The total number of pages in the full set.", required = true)
  @JsonProperty("totalPages")
  @NotNull
  @Min(0)
  @Valid
  Integer totalPages;


}
