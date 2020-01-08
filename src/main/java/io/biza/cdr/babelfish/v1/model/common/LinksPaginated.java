/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;

@Valid
public class LinksPaginated
    extends io.biza.cdr.babelfish.model.common.LinksPaginated<LinksPaginated> {
  /**
   * Minimal field validation possible at POJO level Scenario: [ self, first, prev, next, last ]
   * First Page and not Last: [ Yes, No, No, Yes, Yes ] Last Page and not First: [ Yes, Yes, Yes,
   * No, No ] First and Last Page: [ Yes, No, No, No, No ] Page not First or Last: [ Yes, Yes, Yes,
   * Yes, Yes ]
   */
  @AssertTrue(message = "Previous page set but First Page not set")
  public boolean isFirstSetWhenPrevExists() {
    return prev() != null && first() == null ? false : true;
  }

  @AssertTrue(message = "Next page set but Last Page not set")
  public boolean isLastSetWhenNextExists() {
    return next() != null && last() == null ? false : true;
  }

  @AssertTrue(
      message = "While on first and last page (next & prev null), zero links should be defined outside of self")
  public boolean isFirstAndLastPage() {
    return (next() == null && prev() == null) ? ((first() != null || last() != null) ? false : true)
        : true;
  }

  @AssertTrue(message = "First Page URI should contain a parameter of page == 1")
  public boolean isFirstPagePageParamValid() {
    return first() != null
        ? (FormatChecker.mapifyQueryString(first()).get("page") != null
            && FormatChecker.mapifyQueryString(first()).get("page").equals("1"))
        : true;
  }

  @AssertTrue(message = "Next Page URI should have a page equal to current page plus 1")
  public boolean isNextPageParamValid() {
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
  public boolean isPrevPageParamValid() {
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
