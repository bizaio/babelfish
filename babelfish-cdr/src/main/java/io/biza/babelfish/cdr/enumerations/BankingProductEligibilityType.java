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
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Banking Product Eligibility Criteria Type", enumAsRef = true)
public enum BankingProductEligibilityType implements LabelValueEnumInterface {
  // @formatter:off
  BUSINESS("BUSINESS", "Only Business may apply to the account"), 
  PENSION_RECIPIENT("PENSION_RECIPIENT","A recipient of a government pension may apply for the product"), 
  MIN_AGE("MIN_AGE","Only customers older than a minimum age may apply"),
  MAX_AGE("MAX_AGE","Only customers younger than a maximum age may apply"),
  MIN_INCOME("MIN_INCOME","The customer must have an income greater than a specified threshold to obtain the product"),
  MIN_TURNOVER("MIN_TURNOVER","Only a business with greater than a minimum turnover may apply"), 
  STAFF("STAFF","Only a staff member of the provider may apply"),
  STUDENT("STUDENT","Only students may apply for the product"), 
  EMPLOYMENT_STATUS("EMPLOYMENT_STATUS","An eligibility constraint based on employment status applies"), 
  RESIDENCY_STATUS("RESIDENCY_STATUS","An eligibility constraint based on residency status applies"), 
  NATURAL_PERSON("NATURAL_PERSON","The customer must be a natural person rather than another legal entity"), 
  OTHER("OTHER","Another eligibility criteria exists and is described within Additional Information");
  // @formatter:on

  private String value;

  private String label;

  BankingProductEligibilityType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductEligibilityType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductEligibilityType b : BankingProductEligibilityType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductEligibilityType from " + text,
        BankingProductEligibilityType.class.getSimpleName(), BankingProductEligibilityType.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
