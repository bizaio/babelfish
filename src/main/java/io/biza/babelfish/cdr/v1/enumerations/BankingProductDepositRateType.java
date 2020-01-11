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
package io.biza.babelfish.cdr.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Banking Product Deposit Rate Type")
public enum BankingProductDepositRateType implements LabelValueEnumInterface {
  FIXED("FIXED", "Fixed rate for a period of time"), BONUS("BONUS",
      "A bonus rate available by meeting a specific criteria"), BUNDLE_BONUS("BUNDLE_BONUS",
          "A bonus rate obtained by originating a bundle instead of a standalone product"), VARIABLE(
              "VARIABLE", "A variable base rate for the product"), INTRODUCTORY("INTRODUCTORY",
                  "An introductory bonus that will expire after a set period"), FLOATING("FLOATING",
                      "A floating rate is relatively fixed but still adjusts under specific circumstances"), MARKET_LINKED(
                          "MARKET_LINKED",
                          "A rate that is linked to a specific market, commodity or asset class");

  private String value;

  private String label;

  BankingProductDepositRateType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductDepositRateType fromValue(String text) {
    for (BankingProductDepositRateType b : BankingProductDepositRateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
