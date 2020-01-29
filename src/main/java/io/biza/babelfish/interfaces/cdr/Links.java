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

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.inferred.freebuilder.FreeBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductV2;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "CDS Links")
@FreeBuilder
@JsonDeserialize(builder = Links.Builder.class)
public interface Links {
  
  /**
   * Fluent Builder Class
   * Generated by FreeBuilder
   *
   */
  class Builder extends Links_Builder {}
  
  @Schema(description = "Fully qualified link that generated the current response document",
      required = true, type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @NotNull
  @Valid
  @JsonProperty("self")
  public URI self();

}
