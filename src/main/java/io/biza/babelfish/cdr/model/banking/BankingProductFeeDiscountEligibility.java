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
package io.biza.babelfish.cdr.model.banking;

import java.net.URI;
import javax.validation.Valid;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductDiscountEligibilityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Banking Product Discount Eligibility Details")
public abstract class BankingProductFeeDiscountEligibility<T> {
  @Schema(
      description = "The type of the specific eligibility constraint for a discount",
      required = true)
  BankingProductDiscountEligibilityType discountEligibilityType;

  public BankingProductDiscountEligibilityType discountEligibilityType() {
    return getDiscountEligibilityType();
  }

  @SuppressWarnings("unchecked")
  public T discountEligibilityType(BankingProductDiscountEligibilityType discountEligibilityType) {
    setDiscountEligibilityType(discountEligibilityType);
    return (T) this;
  }

  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @Schema(
      description = "Display text providing more information on this eligibility constraint")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @Schema(
      description = "Link to a web page with more information on this eligibility constraint",
      type = "string")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI additionalInfoUri;

  public URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return (T) this;
  }
}
