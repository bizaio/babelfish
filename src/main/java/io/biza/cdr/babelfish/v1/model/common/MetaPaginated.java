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
public class MetaPaginated extends io.biza.cdr.babelfish.model.common.MetaPaginated<MetaPaginated> {
  @AssertTrue(message = "If totalRecords is 0 totalPages MUST be 0")
  public boolean isZeroTotalRecordsMatchesZeroPages() {
    return (totalRecords() != null && totalRecords() == 0) ? ((totalPages() != null && totalPages() == 0) ? true : false) : true;
  }
  
  @AssertTrue(message = "If totalPages is 0 then totalRecords should be 0")
  public boolean isZeroTotalPagesButNotZeroRecords() {
    return (totalPages() != null && totalPages() == 0) ? ((totalRecords() != null && totalRecords() == 0) ? true : false) : true;
  }
  

}
