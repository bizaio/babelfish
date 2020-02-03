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
package io.biza.babelfish.cdr.models.payloads.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class MetaPaginated extends io.biza.babelfish.cdr.abstracts.payloads.MetaPaginated<MetaPaginated> {
  @AssertTrue(message = "If totalRecords is 0 totalPages MUST be 0")
  private boolean isZeroTotalRecordsMatchesZeroPages() {
    return (totalRecords() != null && totalRecords() == 0)
        ? ((totalPages() != null && totalPages() == 0) ? true : false)
        : true;
  }

  @AssertTrue(message = "If totalPages is 0 then totalRecords should be 0")
  private boolean isZeroTotalPagesButNotZeroRecords() {
    return (totalPages() != null && totalPages() == 0)
        ? ((totalRecords() != null && totalRecords() == 0) ? true : false)
        : true;
  }


}