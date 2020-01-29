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
package io.biza.babelfish.cdr.abstracts.payloads;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "The CDR Response")
@Valid
@Getter
@Setter
public abstract class CDRResponse<T> {
  @Schema(description = "The Links Object", required = true)
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

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @JsonProperty("meta")
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
