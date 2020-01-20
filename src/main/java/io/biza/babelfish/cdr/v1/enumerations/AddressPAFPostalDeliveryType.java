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
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Postal Delivery Type for the Address", enumAsRef = true)
public enum AddressPAFPostalDeliveryType implements LabelValueEnumInterface {
  // @formatter:off
  CARE_OF_PO("CARE PO", "Care Of Post Office"),
  COMMUNITY_MAIL("CMA", "Community Mail Agent"),
  POSTE_RESTANTE("CARE PO", "Poste Restante"),
  COMMUNITY_BAG("CMB", "Community Mail Bag"),
  PRIVATE_BAG("PRIVATE BAG", "Private Mail Bag Service"),
  GPO_BOX("GPO BOX", "General Post Office Box"),
  ROADSIDE_MAIL_BAG("RMB", "Roadside Mail Bag"),
  ROADSIDE_DELIVERY("RSD", "Roadside Delivery"),
  LOCKED_BAG("LOCKED BAG", "Locked Mail Bag Service"),
  ROADSIDE_BOX("RMB", "Roadside Mail Box"),
  MAIL_SERVICE("MS", "Mail Service"),
  ROADSIDE_MAIL("RMS", "Roadside Mail Service"),
  PO_BOX("PO BOX", "Post Office Box"),
  COMMUNITY_AGENT("CPA", "Community Postal Agent");
  // @formatter:on
  private String value;

  private String label;

  AddressPAFPostalDeliveryType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFPostalDeliveryType fromValue(String text) {
    for (AddressPAFPostalDeliveryType b : AddressPAFPostalDeliveryType.values()) {
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
