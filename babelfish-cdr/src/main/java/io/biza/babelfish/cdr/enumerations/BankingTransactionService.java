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

@Schema(description = "Banking Transaction: NPP Service Overlay", enumAsRef = true)
public enum BankingTransactionService implements LabelValueEnumInterface {
  // @formatter:off    
  X2P101("X2P1.01", "X2P1.01 Overlay");
  // @formatter:on
  private String value;

  private String label;

  BankingTransactionService(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingTransactionService fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingTransactionService b : BankingTransactionService.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingTransactionService from " + text,
        BankingTransactionService.class.getSimpleName(), BankingTransactionService.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
