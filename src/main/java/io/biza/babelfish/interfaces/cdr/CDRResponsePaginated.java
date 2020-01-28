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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "This is a Paginated CDR Response")
@Valid
public interface CDRResponsePaginated {
  @Schema(description = "The Links Object", required = true)
  @NotNull
  @Valid
 public LinksPaginated getLinks();
  
  public default LinksPaginated links() {
    return getLinks();
  }
  
  @JsonSetter("links")
  public void setLinks(LinksPaginated links);

  public default CDRResponsePaginated links(LinksPaginated links) {
    setLinks(links);
    return this;
  }

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @NotNull
  @Valid
  @JsonGetter("meta")
  public MetaPaginated getMeta();

  public default MetaPaginated meta() {
    return getMeta();
  }

  @JsonSetter("meta")
  public void setMeta(MetaPaginated meta);
  
  public default CDRResponsePaginated meta(MetaPaginated meta) {
    setMeta(meta);
    return this;
  }
}
