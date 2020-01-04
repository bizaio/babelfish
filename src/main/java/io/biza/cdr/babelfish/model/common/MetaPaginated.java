/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Paginated Meta Details", parent = Meta.class)
public abstract class MetaPaginated extends Meta {
  @BabelFishModelProperty(description = "The total number of records in the full set.",
      required = true)
  @JsonProperty("totalRecords")
  @NotNull
  @NonNull
  public Integer totalRecords;

  @BabelFishModelProperty(description = "The total number of pages in the full set.",
      required = true)
  @JsonProperty("totalPages")
  @NotNull
  @NonNull
  public Integer totalPages;
}
