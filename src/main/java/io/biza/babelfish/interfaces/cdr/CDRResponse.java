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

@Schema(description = "The CDR Response")
@Valid
public interface CDRResponse {
  @Schema(description = "The Links Object", required = true)
  @NotNull
  @Valid
  @JsonGetter("links")
  public Links getLinks();
  
  public default Links links() {
    return getLinks();
  }
  
  @JsonSetter("links")
  public void setLinks(Links links);

  public default CDRResponse links(Links links) {
    setLinks(links);
    return this;
  }

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @Valid
  @JsonGetter("meta")
  public Meta getMeta();

  public default Meta meta() {
    return getMeta();
  }

  @JsonSetter("meta")
  public void setMeta(Meta meta);
  
  public default CDRResponse meta(Meta meta) {
    setMeta(meta);
    return this;
  }
}
