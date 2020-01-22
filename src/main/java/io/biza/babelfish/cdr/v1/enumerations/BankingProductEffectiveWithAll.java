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
package io.biza.babelfish.cdr.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exception.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Banking Product Effective Filter with All", enumAsRef = true)
public enum BankingProductEffectiveWithAll implements LabelValueEnumInterface {
  // @formatter:off
  CURRENT("CURRENT", "Products Currently Available in Market"), 
  FUTURE("FUTURE", "Products Currently Available in the Future"), 
  ALL("ALL", "All Products available now and in the future");
  // @formatter:on
  private String value;

  private String label;

  BankingProductEffectiveWithAll(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductEffectiveWithAll fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductEffectiveWithAll b : BankingProductEffectiveWithAll.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductEffectiveWithAll from " + text,
        BankingProductEffectiveWithAll.class.getSimpleName(),
        BankingProductEffectiveWithAll.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
