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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Paginated Meta Details", parent = Meta.class)
public abstract class MetaPaginated<T extends MetaPaginated<T>> {
  @BabelFishModelProperty(description = "The total number of records in the full set.",
      required = true)
  @JsonProperty("totalRecords")
  @NotNull
  @NonNull
  @Min(0)
  @Valid
  public Integer totalRecords;

  public Integer totalRecords() {
    return getTotalRecords();
  }

  @SuppressWarnings("unchecked")
  public T totalRecords(Integer totalRecords) {
    setTotalRecords(totalRecords);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The total number of pages in the full set.",
      required = true)
  @JsonProperty("totalPages")
  @NotNull
  @NonNull
  @Min(0)
  @Valid
  public Integer totalPages;

  public Integer totalPages() {
    return getTotalPages();
  }

  @SuppressWarnings("unchecked")
  public T totalPages(Integer totalPages) {
    setTotalPages(totalPages);
    return (T) this;
  }
}
