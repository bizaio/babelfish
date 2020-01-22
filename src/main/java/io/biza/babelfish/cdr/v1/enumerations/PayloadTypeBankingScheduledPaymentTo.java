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

@Schema(description = "Payload Type: Banking Scheduled Payment Target", enumAsRef = true)
public enum PayloadTypeBankingScheduledPaymentTo implements LabelValueEnumInterface {
  // @formatter:off
  ACCOUNT_ID("accountId", "Transfer to a specific Account ID"),
  PAYEE_ID("payeeId", "Payment to a specific Payee ID"),
  DOMESTIC("domestic", "Domestic Payment"),
  BILLER("biller", "BPAY Payment"),
  INTERNATIONAL("international", "International Payment");
  // @formatter:on
  private String value;

  private String label;

  PayloadTypeBankingScheduledPaymentTo(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingScheduledPaymentTo fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (PayloadTypeBankingScheduledPaymentTo b : PayloadTypeBankingScheduledPaymentTo.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of PayloadTypeBankingScheduledPaymentTo from " + text,
        PayloadTypeBankingScheduledPaymentTo.class.getSimpleName(),
        PayloadTypeBankingScheduledPaymentTo.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
