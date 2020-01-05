/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductEligibilityType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Eligibility criteria to obtain a particular banking product")
public abstract class BankingProductEligibility<T extends BankingProductEligibility<T>> {
  @BabelFishModelProperty(
      description = "The type of eligibility criteria described.  See the next section for an overview of valid values and their meaning",
      required = true)
  @NonNull
  @NotNull
  BankingProductEligibilityType eligibilityType;

  public BankingProductEligibilityType eligibilityType() {
    return getEligibilityType();
  }

  @SuppressWarnings("unchecked")
  public T eligibilityType(BankingProductEligibilityType eligibilityType) {
    setEligibilityType(eligibilityType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [eligibilityType](#tocSproducteligibilitytypedoc) specified.  Whether mandatory or not is dependent on the value of [eligibilityType](#tocSproducteligibilitytypedoc)")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Display text providing more information on the eligibility criteria. Mandatory if the [eligibilityType](#tocSproducteligibilitytypedoc) field is set to OTHER")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Link to a web page with more information on this eligibility criteria",
      dataType = "java.lang.String")
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

  @AssertTrue(message = "Additional Information must be populated when Eligibility type is OTHER")
  private boolean isInfoDefined() {
    return Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.OTHER})
        .contains(eligibilityType) ? FormatChecker.isDefined(additionalInfo) : true;
  }

  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE")
  private boolean isValuePositiveInteger() {
    return Arrays.asList(new BankingProductEligibilityType[] {BankingProductEligibilityType.MIN_AGE,
        BankingProductEligibilityType.MAX_AGE}).contains(eligibilityType)
            ? FormatChecker.isPositiveInteger(additionalValue)
            : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER")
  private boolean isValueAmount() {
    return Arrays.asList(new BankingProductEligibilityType[] {
        BankingProductEligibilityType.MIN_INCOME, BankingProductEligibilityType.MIN_TURNOVER})
        .contains(eligibilityType) ? FormatChecker.isDecimal(additionalValue) : true;
  }
}
