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
package io.biza.cdr.babelfish.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@AllArgsConstructor
@Data
@BabelFishModel(
    description = "The CDS Response Container contains either <Links,Meta> or <LinksPaginated,MetaPaginated>, look at related Response entities to identify which combination is applied")
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class CDRResponse<L, M> {
  @BabelFishModelProperty(description = "The Links Object", required = true)
  @NonNull
  @NotNull
  public L links;

  @BabelFishModelProperty(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @NonNull
  @NotNull
  public M meta;

}
