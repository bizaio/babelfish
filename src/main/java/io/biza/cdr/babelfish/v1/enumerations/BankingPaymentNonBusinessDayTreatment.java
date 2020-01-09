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
import io.swagger.v3.oas.annotations.media.Schema;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

/**
 * Non Business Day Treatment Specification
 */
@Schema(
    description = "Enumerated field giving the treatment where a scheduled payment date is not a business day. If absent assumed to be ON")
public enum BankingPaymentNonBusinessDayTreatment implements LabelValueEnumInterface {
  // @formatter:off    
  AFTER("AFTER", "If a scheduled payment date is a non-business day the payment will be made on the first business day after the scheduled payment date"),
  BEFORE("BEFORE", "If a scheduled payment date is a non-business day the payment will be made on the first business day before the scheduled payment date"),
  ON("ON", "If a scheduled payment date is a non-business day the payment will be made on that day regardless"),
  ONLY("ONLY", "Payments only occur on business days. If a scheduled payment date is a non-business day the payment will be ignored");
  // @formatter:on  
  private String value;

  private String label;

  BankingPaymentNonBusinessDayTreatment(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingPaymentNonBusinessDayTreatment fromValue(String text) {
    for (BankingPaymentNonBusinessDayTreatment b : BankingPaymentNonBusinessDayTreatment.values()) {
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
