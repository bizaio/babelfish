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
package io.biza.babelfish.interfaces.cdr;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.inferred.freebuilder.FreeBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Paginated Meta Details")
@FreeBuilder
@JsonDeserialize(builder = MetaPaginated.Builder.class)
public interface MetaPaginated extends Meta {
  
  /**
   * Fluent Builder Class
   * Generated by FreeBuilder
   *
   */
  class Builder extends MetaPaginated_Builder {}
  
  @Schema(description = "The total number of records in the full set.", required = true)
  @NotNull
  @Min(0)
  @Valid
  @JsonProperty("totalRecords")
  public Integer totalRecords();

  @Schema(description = "The total number of pages in the full set.", required = true)
  @NotNull
  @Min(0)
  @Valid
  @JsonProperty("totalPages")
  public Integer totalPages();

}
