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
import javax.validation.Valid;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(
    description = "Object that contains links to additional information on specific topics")
public abstract class BankingProductAdditionalInformation<T extends BankingProductAdditionalInformation<T>> {
  @BabelFishModelProperty(description = "General overview of the product",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI overviewUri;

  public URI overviewUri() {
    return getOverviewUri();
  }

  @SuppressWarnings("unchecked")
  public T overviewUri(URI overviewUri) {
    setOverviewUri(overviewUri);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Terms and conditions for the product",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI termsUri;

  public URI termsUri() {
    return getTermsUri();
  }

  @SuppressWarnings("unchecked")
  public T termsUri(URI termsUri) {
    setTermsUri(termsUri);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Eligibility rules and criteria for the product",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI eligibilityUri;

  public URI eligibilityUri() {
    return getEligibilityUri();
  }

  @SuppressWarnings("unchecked")
  public T eligibilityUri(URI eligibilityUri) {
    setEligibilityUri(eligibilityUri);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Description of fees, pricing, discounts, exemptions and bonuses for the product",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI feesAndPricingUri;

  public URI feesAndPricingUri() {
    return getFeesAndPricingUri();
  }

  @SuppressWarnings("unchecked")
  public T feesAndPricingUri(URI feesAndPricingUri) {
    setFeesAndPricingUri(feesAndPricingUri);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Description of a bundle that this product can be part of",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI bundleUri;

  public URI bundleUri() {
    return getBundleUri();
  }

  @SuppressWarnings("unchecked")
  public T bundleUri(URI bundleUri) {
    setBundleUri(bundleUri);
    return (T) this;
  }
}
