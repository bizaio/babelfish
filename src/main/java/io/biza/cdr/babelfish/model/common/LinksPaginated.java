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

import java.net.URI;
import javax.validation.Valid;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Paginated Links")
public abstract class LinksPaginated {
  /**
   * Minimal field validation possible at POJO level Scenario: [ self, first, prev, next, last ]
   * First Page and not Last: [ Yes, No, No, Yes, Yes ] Last Page and not First: [ Yes, Yes, Yes,
   * No, No ] First and Last Page: [ Yes, No, No, No, No ] Page not First or Last: [ Yes, Yes, Yes,
   * Yes, Yes ]
   */
  @BabelFishModelProperty(
      description = "Fully qualified link that generated the current response document",
      required = true, dataType = "java.lang.String", attributeName = "self")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("self")
  public URI self;

  @BabelFishModelProperty(
      description = "URI to the first page of this set. Mandatory if this response is not the first page",
      dataType = "java.lang.String", attributeName = "first")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("first")
  public URI first;

  @BabelFishModelProperty(
      description = "URI to the previous page of this set. Mandatory if this response is not the prev page",
      dataType = "java.lang.String", attributeName = "prev")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("prev")
  public URI prev;

  @BabelFishModelProperty(
      description = "URI to the next page of this set. Mandatory if this response is not the last page",
      dataType = "java.lang.String", attributeName = "next")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("next")
  public URI next;

  @BabelFishModelProperty(
      description = "URI to the last page of this set. Mandatory if this response is not the last page",
      dataType = "java.lang.String", attributeName = "last")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("last")
  public URI last;
}
