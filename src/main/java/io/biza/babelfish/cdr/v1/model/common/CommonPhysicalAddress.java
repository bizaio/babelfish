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
package io.biza.babelfish.cdr.v1.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.enumerations.cdr.PayloadTypeAddress;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonPhysicalAddress
    extends io.biza.babelfish.cdr.model.common.CommonPhysicalAddress<CommonPhysicalAddress> {
  @AssertTrue(
      message = "One and only one of simple or paf should be populated based on addressUType")
  public boolean isAddressTypeSpecifiedIsPopulated() {
    if (type() == null) {
      return true;
    }

    if (type().equals(PayloadTypeAddress.SIMPLE)) {
      return simple() != null && paf() == null;
    } else if (type().equals(PayloadTypeAddress.PAF)) {
      return paf() != null && simple() == null;
    }
    return false;
  }
}
