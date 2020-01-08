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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonPAFAddress
    extends io.biza.cdr.babelfish.model.common.CommonPAFAddress<CommonPAFAddress> {
  @AssertTrue(
      message = "Thoroughfare Suffixes should only be set when Thoroughfare Numbers are set")
  private boolean isInvalidSuffixes() {
    if (thoroughfareNumber1Suffix() != null && thoroughfareNumber1() == null) {
      return false;
    }
    if (thoroughfareNumber2Suffix() != null && thoroughfareNumber2() == null) {
      return false;
    }
    return true;
  }

  @AssertTrue(
      message = "Thoroughfare Number 2 must only be set when Thoroughfare Number 1 exists and must be greater than Thoroughfare Number 1")
  private boolean isThoroughFareNumber2LargerThan1() {
    if (thoroughfareNumber1() == null) {
      if (thoroughfareNumber2() == null) {
        return true;
      } else {
        return false;
      }
    } else {
      if (thoroughfareNumber2() == null) {
        return false;
      } else {
        if (thoroughfareNumber2() > thoroughfareNumber1()) {
          return true;
        } else {
          return false;
        }
      }
    }
  }
}
