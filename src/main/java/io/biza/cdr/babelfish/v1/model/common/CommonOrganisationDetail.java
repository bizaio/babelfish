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
import io.biza.cdr.babelfish.model.common.CommonPhysicalAddressWithPurpose;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.AddressPurpose;

@Valid
public class CommonOrganisationDetail
    extends io.biza.cdr.babelfish.model.common.CommonOrganisationDetail<CommonOrganisationDetail> {

  @AssertTrue(
      message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose")
  private boolean isPhysicalAddressesCorrect() {
    if (physicalAddresses() == null) {
      return true;
    }

    int registeredCount = 0;
    int mailCount = 0;
    for (CommonPhysicalAddressWithPurpose<?> oneAddress : physicalAddresses()) {
      if (oneAddress.purpose().equals(AddressPurpose.REGISTERED)) {
        registeredCount++;
      }
      if (oneAddress.purpose().equals(AddressPurpose.MAIL)) {
        mailCount++;
      }
    }
    if (registeredCount != 1) {
      return false;
    }
    if (mailCount != 0 && mailCount != 1) {
      return false;
    }
    return true;
  }

}
