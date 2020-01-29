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

@Schema(description = "Banking Product Discount Eligibility Type", enumAsRef = true)
public enum BankingProductDiscountEligibilityType implements LabelValueEnumInterface {
  // @formatter:off
  BUSINESS("BUSINESS","Only Business or other non-person legal entity receives this discount"), 
  PENSION_RECIPIENT("PENSION_RECIPIENT","Only a recipient of a government pension receives this discount"), 
  MIN_AGE("MIN_AGE","Only customers older than a minimum age receive this discount"), 
  MAX_AGE("MAX_AGE","Only customers younger than a maximum age receive this discount"), 
  MIN_INCOME("MIN_INCOME","The customer must have an income greater than a specified threshold to receive this discount"), 
  MIN_TURNOVER("MIN_TURNOVER","Only a business with greater than a minimum turnover receives this discount"), 
  STAFF("STAFF","Only a staff member of the provider receives this discount"), 
  STUDENT("STUDENT","Only students receives this discount"), 
  EMPLOYMENT_STATUS("EMPLOYMENT_STATUS","An discount eligibility constraint based on employment status applies"), 
  RESIDENCY_STATUS("RESIDENCY_STATUS","An discount eligibility constraint based on residency status applies"), 
  NATURAL_PERSON("NATURAL_PERSON","The customer must be a natural person (ie. not a business or non-person legal entity) to receive this discount"), 
  INTRODUCTORY("INTRODUCTORY","This discount is only available during an introductory period"), 
  OTHER("OTHER","Another eligibility criteria exists to receive this discount and is described within Additional Information");
  // @formatter:on

  private String value;

  private String label;

  BankingProductDiscountEligibilityType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductDiscountEligibilityType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (BankingProductDiscountEligibilityType b : BankingProductDiscountEligibilityType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of BankingProductDiscountEligibilityType from " + text,
        BankingProductDiscountEligibilityType.class.getSimpleName(),
        BankingProductDiscountEligibilityType.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
