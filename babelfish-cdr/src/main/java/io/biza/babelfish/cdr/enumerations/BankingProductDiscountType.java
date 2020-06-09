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

@Schema(description = "Banking Product Discount Type", enumAsRef = true)
public enum BankingProductDiscountType implements LabelValueEnumInterface {
  // @formatter:off
  BALANCE("BALANCE", "Discount on a fee for maintaining a set balance. As the discount applies to a fee the period is the same as for the fee"), 
  DEPOSITS("DEPOSITS", "Discount for depositing a certain amount of money in a period. As the discount applies to a fee the period is the same as for the fee"), 
  PAYMENTS("PAYMENTS", "Discount for outbound payments from the account under a certain amount of money in a period. As the discount applies to a fee the period is the same as for the fee"), 
  FEE_CAP("FEE_CAP", "The amount, balanceRate, transactionRate or calculatedInterestRate fields of the discount represent the maximum amount charged in a time period"), 
  ELIGIBILITY_ONLY("ELIGIBILITY_ONLY", "Discount applies based on customer eligibility (eligibility array must be populated)");
  // @formatter:on
  private String value;

  private String label;

  BankingProductDiscountType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductDiscountType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductDiscountType b : BankingProductDiscountType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductDiscountType from " + text,
        BankingProductDiscountType.class.getSimpleName(), BankingProductDiscountType.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
