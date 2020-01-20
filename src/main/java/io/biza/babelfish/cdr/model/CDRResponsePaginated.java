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
package io.biza.babelfish.cdr.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.model.common.LinksPaginated;
import io.biza.babelfish.cdr.model.common.MetaPaginated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Schema(description = "This is a Paginated CDR Response")
@Valid
@Getter
@Setter
public abstract class CDRResponsePaginated<T> {
  @Schema(description = "The Links Object", required = true)
  @JsonProperty("links")
  @NotNull
  @NonNull
  @Valid
  LinksPaginated<?> links;

  public LinksPaginated<?> links() {
    return getLinks();
  }

  @SuppressWarnings("unchecked")
  public T links(LinksPaginated<?> links) {
    setLinks(links);
    return (T) this;
  }

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @JsonProperty("meta")
  @NotNull
  @NonNull
  @Valid
  MetaPaginated<?> meta;

  public MetaPaginated<?> meta() {
    return getMeta();
  }

  @SuppressWarnings("unchecked")
  public T meta(MetaPaginated<?> meta) {
    setMeta(meta);
    return (T) this;
  }
}
