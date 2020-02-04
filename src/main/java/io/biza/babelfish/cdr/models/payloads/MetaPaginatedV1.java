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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "Paginated Meta Details")
public class MetaPaginatedV1 {
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
  
  @AssertTrue(message = "If totalRecords is 0 totalPages MUST be 0")
  private boolean isZeroTotalRecordsMatchesZeroPages() {
    return (totalRecords() != null && totalRecords() == 0)
        ? ((totalPages() != null && totalPages() == 0) ? true : false)
        : true;
  }

  @AssertTrue(message = "If totalPages is 0 then totalRecords should be 0")
  private boolean isZeroTotalPagesButNotZeroRecords() {
    return (totalPages() != null && totalPages() == 0)
        ? ((totalRecords() != null && totalRecords() == 0) ? true : false)
        : true;
  }

}
