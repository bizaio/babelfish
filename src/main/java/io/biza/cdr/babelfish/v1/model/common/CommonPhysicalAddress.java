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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;

@Valid
public class CommonPhysicalAddress
    extends io.biza.cdr.babelfish.model.common.CommonPhysicalAddress {
  @AssertTrue(
      message = "One and only one of simple or paf should be populated based on addressUType")
  private boolean isUTypePopulated() {
    if (addressType().equals(PayloadTypeAddress.SIMPLE)) {
      return simple != null && paf == null;
    } else if (addressType.equals(PayloadTypeAddress.PAF)) {
      return paf != null && simple == null;
    }
    return false;
  }
}
