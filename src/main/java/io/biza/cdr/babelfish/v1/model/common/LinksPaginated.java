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
package io.biza.cdr.babelfish.v1.model.common;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
public class LinksPaginated implements io.biza.cdr.babelfish.model.common.LinksPaginated {
  URI self;
  URI first;
  URI prev;
  URI next;
  URI last;

  @Override
  public URI getSelf() {
    return self;
  }

  @Override
  public void setSelf(URI self) {
    this.self = self;
  }

  @Override
  public URI getFirst() {
    return first;
  }

  @Override
  public void setFirst(URI first) {
    this.first = first;
  }

  @Override
  public URI getPrev() {
    return prev;
  }

  @Override
  public void setPrev(URI prev) {
    this.prev = prev;
  }

  @Override
  public URI getNext() {
    return next;
  }

  @Override
  public void setNext(URI next) {
    this.next = next;
  }

  @Override
  public URI getLast() {
    return last;
  }

  @Override
  public void setLast(URI last) {
    this.last = last;
  }

  /**
   * Minimal field validation possible at POJO level Scenario: [ self, first, prev, next, last ]
   * First Page and not Last: [ Yes, No, No, Yes, Yes ] Last Page and not First: [ Yes, Yes, Yes,
   * No, No ] First and Last Page: [ Yes, No, No, No, No ] Page not First or Last: [ Yes, Yes, Yes,
   * Yes, Yes ]
   */
  @AssertTrue(message = "Previous page set but First Page not set")
  private boolean isFirstSetWhenPrevExists() {
    return prev != null && first == null ? false : true;
  }

  @AssertTrue(message = "Next page set but Last Page not set")
  private boolean isLastSetWhenNextExists() {
    return next != null && last == null ? false : true;
  }

  @AssertTrue(
      message = "While on first and last page (next & prev null), zero links should be defined outside of self")
  private boolean isFirstAndLastPage() {
    return (next == null && prev == null) ? ((first != null || last != null) ? false : true) : true;
  }

}
