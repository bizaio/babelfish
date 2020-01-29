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
@Schema(description = "Paginated Links")
@FreeBuilder
@JsonDeserialize(builder = LinksPaginated.Builder.class)
public interface LinksPaginated extends Links {
  
  /*
  * Fluent Builder Class
  * Generated by FreeBuilder
  *
  */
 class Builder extends LinksPaginated_Builder {}

  @Schema(
      description = "URI to the first page of this set. Mandatory if this response is not the first page",
      type = "string", name = "first", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonProperty("first")
  public URI first();

  @Schema(
      description = "URI to the previous page of this set. Mandatory if this response is not the prev page",
      type = "string", name = "prev", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonProperty("prev")
  public URI prev();

  @Schema(
      description = "URI to the next page of this set. Mandatory if this response is not the last page",
      type = "string", name = "next", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonProperty("next")
  public URI next();

  @Schema(
      description = "URI to the last page of this set. Mandatory if this response is not the last page",
      type = "string", name = "last", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonProperty("last")
  public URI last();

}
