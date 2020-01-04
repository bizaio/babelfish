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

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

@Valid
public class LinksPaginated extends io.biza.cdr.babelfish.model.common.LinksPaginated {
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
