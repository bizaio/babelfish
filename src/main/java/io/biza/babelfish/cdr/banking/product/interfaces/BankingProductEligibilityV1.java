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
package io.biza.babelfish.cdr.banking.product.interfaces;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductEligibilityType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Eligibility criteria to obtain a particular banking product")
public interface BankingProductEligibilityV1 {
  @Schema(
      description = "The type of eligibility criteria described.  See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonGetter("eligibilityType")
  public BankingProductEligibilityType getType();

  public default BankingProductEligibilityType type() {
    return getType();
  }
  
  @JsonSetter("eligibilityType")
  public void setType(BankingProductEligibilityType type);

  public default BankingProductEligibilityV1 type(BankingProductEligibilityType eligibilityType) {
    setType(eligibilityType);
    return this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [eligibilityType](#tocSproducteligibilitytypedoc) specified.  Whether mandatory or not is dependent on the value of [eligibilityType](#tocSproducteligibilitytypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  public default String additionalValue() {
    return getAdditionalValue();
  }
  
  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductEligibilityV1 additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }
  
  @Schema(
      description = "Display text providing more information on the eligibility criteria. Mandatory if the [eligibilityType](#tocSproducteligibilitytypedoc) field is set to OTHER")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }
  
  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductEligibilityV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @Schema(description = "Link to a web page with more information on this eligibility criteria",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @Valid
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }
  
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductEligibilityV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }
}
