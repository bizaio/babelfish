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
package io.biza.babelfish.enumerations.cdr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.exception.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Banking Product Lending Rate Interest Payment Configuration",
    enumAsRef = true)
public enum BankingProductLendingRateInterestPaymentType implements LabelValueEnumInterface {
  // @formatter:off  
  IN_ARREARS("IN_ARREARS", "Interest Paid in Arrears"),
  IN_ADVANCE("IN_ADVANCE", "Interest Paid in Advance");
  // @formatter:on  
  private String value;

  private String label;

  BankingProductLendingRateInterestPaymentType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductLendingRateInterestPaymentType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductLendingRateInterestPaymentType b : BankingProductLendingRateInterestPaymentType
        .values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductLendingRateInterestPaymentType from " + text,
        BankingProductLendingRateInterestPaymentType.class.getSimpleName(),
        BankingProductLendingRateInterestPaymentType.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
