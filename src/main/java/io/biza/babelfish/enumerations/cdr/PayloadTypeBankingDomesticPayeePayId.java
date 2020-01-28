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

@Schema(description = "Payload Type: Domestic Payee PayID Identifier Type", enumAsRef = true)
public enum PayloadTypeBankingDomesticPayeePayId implements LabelValueEnumInterface {
  // @formatter:off
  EMAIL("EMAIL", "Email Address"),
  TELEPHONE("TELEPHONE", "Telephone"),
  ABN("ABN", "Australian Business Number"),
  ORG_IDENTIFIER("ORG_IDENTIFIER", "Organisational Identifier");
  // @formatter:on
  private String value;

  private String label;

  PayloadTypeBankingDomesticPayeePayId(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingDomesticPayeePayId fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (PayloadTypeBankingDomesticPayeePayId b : PayloadTypeBankingDomesticPayeePayId.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of PayloadTypeBankingDomesticPayeePayId from " + text,
        PayloadTypeBankingDomesticPayeePayId.class.getSimpleName(),
        PayloadTypeBankingDomesticPayeePayId.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
