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
package io.biza.cdr.babelfish.v1.model;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

@Valid
public class CDRResponsePaginated extends io.biza.cdr.babelfish.model.CDRResponsePaginated {

  @AssertTrue(message = "First and Last Page Detected but Total Pages is >1")
  public boolean isTotalPagesBiggerThanLinks() {
    return (links() != null && links().next() == null && links().prev() == null)
        ? (meta() != null && meta().totalPages() > 1 ? false : true)
        : true;
  }

  /**
   * @AssertTrue(message = "If totalRecords is greater than 0 totalPages should be greater than 0")
   *                     public boolean someRecordsCannotBeZeroPages() { return false; //return
   *                     totalRecords > 0 ? (totalPages > 0 ? true : false) : true; }
   */
}
