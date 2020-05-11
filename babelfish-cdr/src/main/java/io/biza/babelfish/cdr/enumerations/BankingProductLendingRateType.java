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
package io.biza.babelfish.cdr.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Banking Product: Lending Rate Type", enumAsRef = true)
public enum BankingProductLendingRateType implements LabelValueEnumInterface {
  // @formatter:off  
  FIXED("FIXED", "Fixed rate for a period of time"),
  VARIABLE("VARIABLE", "A variable base rate for the product"),
  INTRODUCTORY("INTRODUCTORY", "An introductory discount that will expire after a set period"),
  FLOATING("FLOATING", "A floating rate is relatively fixed but still adjusts under specific circumstances"),
  MARKET_LINKED("MARKET_LINKED", "A rate that is linked to a specific market, commodity or asset class"),
  DISCOUNT("DISCOUNT","A specific discount rate that may be applied. A discount rate reduces the interest payable."),
  PENALTY("PENALTY","A specific penalty rate that may be applied. A penalty rate increases the interest payable"),
  CASH_ADVANCE("CASH_ADVANCE","Specific rate applied to cash advances from the account"),
  PURCHASE("PURCHASE","Specific rate applied to purchases from the account"),
  BUNDLE_DISCOUNT_FIXED("BUNDLE_DISCOUNT_FIXED","A discount rate off the fixed rate obtained by originating a bundle instead of a standalone product"),
  BUNDLE_DISCOUNT_VARIABLE("BUNDLE_DISCOUNT_VARIABLE","A discount rate off the variable rate obtained by originating a bundle instead of a standalone product");
  // @formatter:on	
  private String value;

  private String label;

  BankingProductLendingRateType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductLendingRateType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductLendingRateType b : BankingProductLendingRateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductLendingRateType from " + text,
        BankingProductLendingRateType.class.getSimpleName(), BankingProductLendingRateType.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
