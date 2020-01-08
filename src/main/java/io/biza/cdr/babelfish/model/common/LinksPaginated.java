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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@BabelFishModel(description = "Paginated Links")
public abstract class LinksPaginated<T> extends Links<T> {

  @BabelFishModelProperty(
      description = "URI to the first page of this set. Mandatory if this response is not the first page",
      dataType = "java.lang.String", attributeName = "first")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("first")
  @Valid
  public URI first;

  public URI first() {
    return getFirst();
  }

  @SuppressWarnings("unchecked")
  public T first(URI first) {
    setFirst(first);
    return (T) this;
  }


  @BabelFishModelProperty(
      description = "URI to the previous page of this set. Mandatory if this response is not the prev page",
      dataType = "java.lang.String", attributeName = "prev")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("prev")
  @Valid
  public URI prev;

  public URI prev() {
    return getPrev();
  }

  @SuppressWarnings("unchecked")
  public T prev(URI prev) {
    setPrev(prev);
    return (T) this;
  }


  @BabelFishModelProperty(
      description = "URI to the next page of this set. Mandatory if this response is not the last page",
      dataType = "java.lang.String", attributeName = "next")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("next")
  @Valid
  public URI next;

  public URI next() {
    return getNext();
  }

  @SuppressWarnings("unchecked")
  public T next(URI next) {
    setNext(next);
    return (T) this;
  }


  @BabelFishModelProperty(
      description = "URI to the last page of this set. Mandatory if this response is not the last page",
      dataType = "java.lang.String", attributeName = "last")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("last")
  @Valid
  public URI last;

  public URI last() {
    return getLast();
  }

  @SuppressWarnings("unchecked")
  public T last(URI last) {
    setLast(last);
    return (T) this;
  }

}
