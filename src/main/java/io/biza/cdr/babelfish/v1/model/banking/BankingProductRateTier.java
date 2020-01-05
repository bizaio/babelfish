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
package io.biza.cdr.babelfish.v1.model.banking;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.v1.enumerations.CommonUnitOfMeasureType;

@Valid
public class BankingProductRateTier
    extends io.biza.cdr.babelfish.model.banking.BankingProductRateTier<BankingProductRateTier> {
  @AssertTrue(
      message = "Minimum and Maximum values must be equal when Unit Of Measure is a discrete value")
  private boolean isMinMaxEqual() {
    return Arrays.asList(
        new CommonUnitOfMeasureType[] {CommonUnitOfMeasureType.DAY, CommonUnitOfMeasureType.MONTH})
        .contains(unitOfMeasure()) && maximumValue() != null ? (minimumValue() == maximumValue())
            : true;
  }
}
