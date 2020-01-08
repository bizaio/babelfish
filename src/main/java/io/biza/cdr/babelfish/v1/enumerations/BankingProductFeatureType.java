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
package io.biza.cdr.babelfish.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Feature Type")
public enum BankingProductFeatureType implements LabelValueEnumInterface {
  // @formatter:off
  CARD_ACCESS("CARD_ACCESS", "Card Access"),
  ADDITIONAL_CARDS("ADDITIONAL_CARDS", "Additional Cards"),
  UNLIMITED_TXNS("UNLIMITED_TXNS", "Unlimited Free Transactions"),
  FREE_TXNS("FREE_TXNS", "Free Transaction Inclusion"),
  FREE_TXNS_ALLOWANCE("FREE_TXNS_ALLOWANCE", "Transaction Fee Inclusion"),
  LOYALTY_PROGRAM("LOYALTY_PROGRAM", "Loyalty Program"),
  OFFSET("OFFSET", "Offset Available"),
  OVERDRAFT("OVERDRAFT", "Overdraft Available"),
  REDRAW("REDRAW", "Redraw Available"),
  INSURANCE("INSURANCE", "Insurance Included"),
  BALANCE_TRANSFERS("BALANCE_TRANSFERS", "Balance Transfers Available"),
  INTEREST_FREE("INTEREST_FREE", "Interest Free Purchases"),
  INTEREST_FREE_TRANSFERS("INTEREST_FREE_TRANSFERS", "Interest Free Balance Transfers"),
  DIGITAL_WALLET("DIGITAL_WALLET", "Digital Wallet"),
  DIGITAL_BANKING("DIGITAL_BANKING", "Digital Banking"),
  NPP_PAYID("NPP_PAYID", "PayID Compatible"),
  NPP_ENABLED("NPP_ENABLED", "NPP Enabled"),
  DONATE_INTEREST("DONATE_INTEREST", "Donate Interest Received"),
  BILL_PAYMENT("BILL_PAYMENT", "Automatic Budgeting & Bill Payments"),
  COMPLEMENTARY_PRODUCT_DISCOUNTS("COMPLEMENTARY_PRODUCT_DISCOUNTS", "Complementary Product Discounts"),
  BONUS_REWARDS("BONUS_REWARDS", "Bonus Loyalty Rewards"),
  NOTIFICATIONS("NOTIFICATIONS", "Advanced Notification"),
  OTHER("OTHER", "Other");
  // @formatter:on
  private String value;

  private String label;

  BankingProductFeatureType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductFeatureType fromValue(String text) {
    for (BankingProductFeatureType b : BankingProductFeatureType.values()) {
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
