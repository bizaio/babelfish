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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Defines a condition for the applicability of a tiered rate")
public interface BankingProductRateTierApplicabilityV1 {
  @Schema(description = "Display text providing more information on the condition")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductRateTierApplicabilityV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @Schema(description = "Link to a web page with more information on this condition",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductRateTierApplicabilityV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }
}
