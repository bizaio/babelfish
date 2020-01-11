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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductEligibilityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Eligibility criteria to obtain a particular banking product")
public abstract class BankingProductEligibility<T> {
  @Schema(
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

  @Schema(
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

  @Schema(
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

  @Schema(
      description = "Link to a web page with more information on this eligibility criteria",
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
