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
package io.biza.babelfish.cdr.interfaces;

import java.net.URI;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Valid
@Schema(description = "Paginated Links")
public interface LinksPaginated extends Links {

  @Schema(
      description = "URI to the first page of this set. Mandatory if this response is not the first page",
      type = "string", name = "first", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonGetter("first")
  public URI getFirst();

  public default URI first() {
    return getFirst();
  }
  
  @JsonSetter("first")
  public void setFirst(URI first);

  public default LinksPaginated first(URI first) {
    setFirst(first);
    return this;
  }


  @Schema(
      description = "URI to the previous page of this set. Mandatory if this response is not the prev page",
      type = "string", name = "prev", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonGetter("prev")
  public URI getPrev();

  public default URI prev() {
    return getPrev();
  }
  
  @JsonSetter("prev")
  public void setPrev(URI prev);

  public default LinksPaginated prev(URI prev) {
    setPrev(prev);
    return this;
  }


  @Schema(
      description = "URI to the next page of this set. Mandatory if this response is not the last page",
      type = "string", name = "next", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonGetter("next")
  public URI getNext();

  public default URI next() {
    return getNext();
  }
  
  @JsonSetter("next")
  public void setNext(URI next);

  public default LinksPaginated next(URI next) {
    setNext(next);
    return this;
  }


  @Schema(
      description = "URI to the last page of this set. Mandatory if this response is not the last page",
      type = "string", name = "last", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonGetter("last")
  public URI getLast();

  public default URI last() {
    return getLast();
  }
  
  @JsonSetter("last")
  public void setLast(URI last);

  public default LinksPaginated last(URI last) {
    setLast(last);
    return this;
  }

}
