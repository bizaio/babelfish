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
package io.biza.babelfish.cdr.v1.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.model.common.CommonPhysicalAddressWithPurpose;
import io.biza.babelfish.cdr.v1.enumerations.AddressPurpose;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonPersonDetail
    extends io.biza.babelfish.cdr.model.common.CommonPersonDetail<CommonPersonDetail> {
  @AssertTrue(
      message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose")
  private boolean isPhysicalAddressesCorrect() {
    if (physicalAddresses() == null) {
      return true;
    }

    int registeredCount = 0;
    int mailCount = 0;
    for (CommonPhysicalAddressWithPurpose<?> oneAddress : physicalAddresses()) {
      if (oneAddress.purpose() == null) {
        continue;
      }
      if (oneAddress.purpose().equals(AddressPurpose.REGISTERED)) {
        registeredCount++;
      }
      if (oneAddress.purpose().equals(AddressPurpose.MAIL)) {
        mailCount++;
      }
    }

    if (registeredCount == 1 && mailCount <= 1) {
      return true;
    }
    return false;
  }
}
