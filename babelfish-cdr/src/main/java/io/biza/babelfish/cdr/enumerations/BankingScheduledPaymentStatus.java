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

@Schema(description = "Banking: Scheduled Payment Status", enumAsRef = true)
public enum BankingScheduledPaymentStatus implements LabelValueEnumInterface {
  // @formatter:off
  ACTIVE("ACTIVE", "Active Scheduled Payment"),
  SKIP("SKIP", "Active but Skip Next Scheduled Payment"),
  INACTIVE("INACTIVE", "Inactive Scheduled Payment");
  // @formatter:on
  private String value;

  private String label;

  BankingScheduledPaymentStatus(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingScheduledPaymentStatus fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingScheduledPaymentStatus b : BankingScheduledPaymentStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingScheduledPaymentStatus from " + text,
        BankingScheduledPaymentStatus.class.getSimpleName(), BankingScheduledPaymentStatus.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
