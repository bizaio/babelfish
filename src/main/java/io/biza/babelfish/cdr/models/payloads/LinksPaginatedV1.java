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
package io.biza.babelfish.cdr.models.payloads;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "Paginated Links")
public class LinksPaginatedV1 {

  @Schema(description = "Fully qualified link that generated the current response document",
      required = true, type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("self")
  @NotNull
  @Valid
  URI self;

  @Schema(
      description = "URI to the first page of this set. Mandatory if this response is not the first page",
      type = "string", name = "first", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("first")
  @Valid
  URI first;

  @Schema(
      description = "URI to the previous page of this set. Mandatory if this response is not the prev page",
      type = "string", name = "prev", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("prev")
  @Valid
  URI prev;

  @Schema(
      description = "URI to the next page of this set. Mandatory if this response is not the last page",
      type = "string", name = "next", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("next")
  @Valid
  URI next;

  @Schema(
      description = "URI to the last page of this set. Mandatory if this response is not the last page",
      type = "string", name = "last", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("last")
  @Valid
  URI last;

  /**
   * Minimal field validation possible at POJO level Scenario: [ self, first, prev, next, last ]
   * First Page and not Last: [ Yes, No, No, Yes, Yes ] Last Page and not First: [ Yes, Yes, Yes,
   * No, No ] First and Last Page: [ Yes, No, No, No, No ] Page not First or Last: [ Yes, Yes, Yes,
   * Yes, Yes ]
   */
  @JsonIgnore
  @AssertTrue(message = "Previous page set but First Page not set")
  private boolean isFirstSetWhenPrevExists() {
    return prev() != null && first() == null ? false : true;
  }

  @AssertTrue(message = "Next page set but Last Page not set")
  private boolean isLastSetWhenNextExists() {
    return next() != null && last() == null ? false : true;
  }

  @AssertTrue(
      message = "While on first and last page (next & prev null), zero links should be defined outside of self")
  private boolean isFirstAndLastPage() {
    return (next() == null && prev() == null) ? ((first() != null || last() != null) ? false : true)
        : true;
  }

  @AssertTrue(message = "First Page URI should contain a parameter of page == 1")
  private boolean isFirstPagePageParamValid() {
    return first() != null
        ? (FormatChecker.mapifyQueryString(first()).get("page") != null
            && FormatChecker.mapifyQueryString(first()).get("page").equals("1"))
        : true;
  }

  @AssertTrue(message = "Next Page URI should have a page equal to current page plus 1")
  private boolean isNextPageParamValid() {
    if (self() != null && next() != null) {
      int selfPage = Integer.parseInt(FormatChecker.mapifyQueryString(self()).get("page"));
      int nextPage = Integer.parseInt(FormatChecker.mapifyQueryString(next()).get("page"));
      selfPage++;

      if (selfPage == nextPage) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  @AssertTrue(message = "Prev Page URI should have a page equal to current page minus 1")
  private boolean isPrevPageParamValid() {
    if (self() != null && prev() != null) {
      int selfPage = Integer.parseInt(FormatChecker.mapifyQueryString(self()).get("page"));
      int prevPage = Integer.parseInt(FormatChecker.mapifyQueryString(prev()).get("page"));
      selfPage--;

      if (selfPage == prevPage) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }
}
