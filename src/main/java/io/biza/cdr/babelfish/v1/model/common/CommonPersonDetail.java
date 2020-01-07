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
import io.biza.cdr.babelfish.support.FormatChecker;

@Valid
public class CommonPersonDetail extends io.biza.cdr.babelfish.model.common.CommonPersonDetail<CommonPersonDetail> {
  @AssertTrue(
      message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose")
  private boolean isPhysicalAddressesCorrect() {
    return FormatChecker.isAddressPopulated(physicalAddresses);
  }
}
