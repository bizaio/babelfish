/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
@BabelFishModel(description = "Paginated Links")
public interface LinksPaginated {
	
	/**
	 * Minimal field validation possible at POJO level
	 * Scenario: [ self, first, prev, next, last ]
	 * First Page and not Last: [ Yes, No, No, Yes, Yes ]
	 * Last Page and not First: [ Yes, Yes, Yes, No, No ]
	 * First and Last Page:     [ Yes, No, No, No, No ]
	 * Page not First or Last:  [ Yes, Yes, Yes, Yes, Yes ]
	 */
	@BabelFishModelProperty(description = "Fully qualified link that generated the current response document", required = true, dataType = "java.lang.String", attributeName = "self")
	@JsonSerialize(converter = UriToUriStringConverter.class)
	@JsonGetter("self")
	public URI getSelf();
	@JsonDeserialize(converter = UriStringToUriConverter.class)
	@JsonSetter("self")
    public void setSelf(URI self);
	public default LinksPaginated self(URI self) {
	  setSelf(self);
	  return this;
	}

	@BabelFishModelProperty(description = "URI to the first page of this set. Mandatory if this response is not the first page", dataType = "java.lang.String", attributeName = "first")
    @JsonSerialize(converter = UriToUriStringConverter.class)
	@JsonGetter("first")
    public URI getFirst();
	@JsonSetter("first")
    @JsonDeserialize(converter = UriStringToUriConverter.class)
    public void setFirst(URI first);
    public default LinksPaginated first(URI first) {
      setFirst(first);
      return this;
    }

	@BabelFishModelProperty(description = "URI to the previous page of this set. Mandatory if this response is not the prev page", dataType = "java.lang.String", attributeName = "prev")
    @JsonSerialize(converter = UriToUriStringConverter.class)
	@JsonGetter("prev")
    public URI getPrev();
    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("prev")
    public void setPrev(URI prev);
    public default LinksPaginated prev(URI prev) {
      setPrev(prev);
      return this;
    }

	@BabelFishModelProperty(description = "URI to the next page of this set. Mandatory if this response is not the last page", dataType = "java.lang.String", attributeName = "next")
    @JsonSerialize(converter = UriToUriStringConverter.class)
	@JsonGetter("next")
    public URI getNext();
    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("next")
    public void setNext(URI next);
    public default LinksPaginated next(URI next) {
      setNext(next);
      return this;
    }

	@BabelFishModelProperty(description = "URI to the last page of this set. Mandatory if this response is not the last page", dataType = "java.lang.String", attributeName = "last")
    @JsonSerialize(converter = UriToUriStringConverter.class)
	@JsonGetter("last")
    public URI getLast();
    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("last")
    public void setLast(URI last);
    public default LinksPaginated last(URI last) {
      setLast(last);
      return this;
    }
	
}
