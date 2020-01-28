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

@Schema(description = "Banking Product: Rate Tier Application Method", enumAsRef = true)
public enum BankingProductRateTierApplicationMethod implements LabelValueEnumInterface {
  // @formatter:off
  WHOLE_BALANCE("WHOLE_BALANCE", "Rate is applied to the entire balance"),
  PER_TIER("PER_TIER", "Rate is applied on a per tier basis");
  // @formatter:on
  private String value;

  private String label;

  BankingProductRateTierApplicationMethod(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductRateTierApplicationMethod fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductRateTierApplicationMethod b : BankingProductRateTierApplicationMethod
        .values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductRateTierApplicationMethod from " + text,
        BankingProductRateTierApplicationMethod.class.getSimpleName(),
        BankingProductRateTierApplicationMethod.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
