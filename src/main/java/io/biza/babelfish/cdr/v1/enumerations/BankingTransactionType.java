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

@Schema(description = "Banking: Transaction Type")
public enum BankingTransactionType implements LabelValueEnumInterface {
  // @formatter:off  
  FEE("FEE", "Prescribed Account Fee"),
  INTEREST_CHARGED("INTEREST_CHARGED", "Interest Charged"),
  INTEREST_PAID("INTEREST_PAID", "Interest Paid"),
  TRANSFER_OUTGOING("TRANSFER_OUTGOING", "Account Transfer (Outgoing)"),
  TRANSFER_INCOMING("TRANSFER_INCOMING", "Account Transfer (Incoming)"),
  PAYMENT("PAYMENT", "External Account Payment"),
  DIRECT_DEBIT("DIRECT_DEBIT", "Direct Debit Charge"),
  OTHER("OTHER", "Other Transaction");
  // @formatter:on
  private String value;

  private String label;

  BankingTransactionType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingTransactionType fromValue(String text) {
    for (BankingTransactionType b : BankingTransactionType.values()) {
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
