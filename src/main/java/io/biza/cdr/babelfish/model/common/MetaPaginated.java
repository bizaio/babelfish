/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
@BabelFishModel(description = "Paginated Meta Details", parent = Meta.class)
public interface MetaPaginated {
  @BabelFishModelProperty(description = "The total number of records in the full set.",
      required = true)
  @JsonGetter("totalRecords")
  public Integer getTotalRecords();
  @JsonSetter("totalRecords")
  public void setTotalRecords(@NotNull Integer totalRecords);
  public default MetaPaginated totalRecords(@NotNull Integer totalRecords) {
    setTotalRecords(totalRecords);
    return this;
  }

  @BabelFishModelProperty(description = "The total number of pages in the full set.",
      required = true)
  @JsonGetter("totalPages")
  public Integer getTotalPages();
  @JsonSetter("totalPages")
  public void setTotalPages(@NotNull Integer totalPages);
  public default MetaPaginated totalPages(@NotNull Integer totalPages) {
    setTotalPages(totalPages);
    return this;
  }
}
