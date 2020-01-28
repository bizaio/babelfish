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
package io.biza.babelfish.cdr.interfaces;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Valid
@Schema(description = "Paginated Meta Details")
public interface MetaPaginated extends Meta {
  @Schema(description = "The total number of records in the full set.", required = true)
  @NotNull
  @Min(0)
  @Valid
  @JsonGetter("totalRecords")
  public Integer getTotalRecords();

  public default Integer totalRecords() {
    return getTotalRecords();
  }

  @JsonSetter("totalRecords")
  public void setTotalRecords(Integer totalRecords);
  
  public default MetaPaginated totalRecords(Integer totalRecords) {
    setTotalRecords(totalRecords);
    return this;
  }

  @Schema(description = "The total number of pages in the full set.", required = true)
  @NotNull
  @Min(0)
  @Valid
  @JsonGetter("totalPages")
  public Integer getTotalPages();

  public default Integer totalPages() {
    return getTotalPages();
  }

  @JsonSetter("totalPages")
  public void setTotalPages(Integer totalPages);
  
  public default MetaPaginated totalPages(Integer totalPages) {
    setTotalPages(totalPages);
    return this;
  }
}
