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
package io.biza.babelfish.cdr.v1.model;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CDRResponsePaginated
    extends io.biza.babelfish.deprecated.cdr.model.CDRResponsePaginated<CDRResponsePaginated> {

  @AssertTrue(message = "First and Last Page Detected but Total Pages is >1")
  public boolean isTotalPagesBiggerThanLinks() {
    return (links() != null && links().next() == null && links().prev() == null)
        ? (meta() != null && meta().totalPages() > 1 ? false : true)
        : true;
  }

  @AssertTrue(message = "Last Page URI page parameter should match totalPages")
  public boolean isLastPagePageParamValid() {
    return (links() != null && links().last() != null && meta() != null
        && meta().totalPages() != null)
            ? ((Integer.parseInt(
                FormatChecker.mapifyQueryString(links().last()).get("page")) != meta().totalPages())
                    ? false
                    : true)
            : true;
  }
}
