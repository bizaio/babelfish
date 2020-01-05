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
package io.biza.cdr.babelfish.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.Setter;

@BabelFishModel(description = "The CDR Response")
@Valid
@Getter
@Setter
public abstract class CDRResponse<T extends CDRResponse<T>> {
  @BabelFishModelProperty(description = "The Links Object", required = true)
  @JsonProperty("links")
  @NotNull
  @Valid
  Links<?> links;

  public Links<?> links() {
    return getLinks();
  }

  @SuppressWarnings("unchecked")
  public T links(Links<?> links) {
    setLinks(links);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @JsonProperty("meta")
  @NotNull
  @Valid
  Meta<?> meta;

  public Meta<?> meta() {
    return getMeta();
  }

  @SuppressWarnings("unchecked")
  public T meta(Meta<?> meta) {
    setMeta(meta);
    return (T) this;
  }
}
