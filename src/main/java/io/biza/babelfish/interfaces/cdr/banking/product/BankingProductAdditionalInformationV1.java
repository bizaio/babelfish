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
package io.biza.babelfish.interfaces.cdr.banking.product;

import java.net.URI;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Object that contains links to additional information on specific topics")
public interface BankingProductAdditionalInformationV1 {
  /**
   * Get a URL to a web page which provides a General Overview of the Product
   * 
   * @return a URL
   */
  @Schema(description = "General overview of the product", type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("overviewUri")
  public URI getOverview();

  /**
   * Set the URL to a General Overview of the Product
   * 
   * @param overviewUri as a URL to set for the overview
   */
  @JsonSetter("overviewUri")
  public void setOverview(URI overviewUri);

  public default URI overview() {
    return getOverview();
  }

  public default BankingProductAdditionalInformationV1 overview(URI overviewUri) {
    setOverview(overviewUri);
    return this;
  }

  /**
   * Get the web page address for the Product Terms and Conditions
   * 
   * @return URI containing the T&C web page
   */
  @Schema(description = "Terms and conditions for the product", type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("termsUri")
  public URI getTerms();

  /**
   * Set the web page for the Product Terms and Conditions
   * 
   * @param termsUri containing a URI to the T&C's
   */
  @JsonSetter("termsUri")
  public void setTerms(URI termsUri);

  public default URI terms() {
    return getTerms();
  }

  public default BankingProductAdditionalInformationV1 terms(URI termsUri) {
    setTerms(termsUri);
    return this;
  }

  /**
   * Eligibility rules and criteria for the product
   * 
   * @return URI containing eligibility rules for the product
   */
  @Schema(description = "Eligibility rules and criteria for the product", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("eligibilityUri")
  public URI getEligibility();

  /**
   * Set the URI to the eligibility rules and criteria
   * 
   * @param eligibilityUri containing URL to rules and criteria webpage
   */
  @JsonSetter("eligibilityUri")
  public void setEligibility(URI eligibilityUri);

  public default URI eligibility() {
    return getEligibility();
  }

  public default BankingProductAdditionalInformationV1 eligibility(URI eligibilityUri) {
    setEligibility(eligibilityUri);
    return this;
  }

  /**
   * Get a URI to a location with fees, pricing, discounts, exemptions and bonuses
   * 
   * @return a URI to a web page with fees, pricing, discounts etc.
   */
  @Schema(
      description = "Description of fees, pricing, discounts, exemptions and bonuses for the product",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("feesAndPricingUri")
  public URI getFeesAndPricing();

  /**
   * Set the URI to the Fees and Pricing
   * 
   * @param feesAndPricing containing a URI to Fees and Pricing
   */
  @JsonSetter("feesAndPricingUri")
  public void setFeesAndPricing(URI feesAndPricing);

  public default URI feesAndPricing() {
    return getFeesAndPricing();
  }

  public default BankingProductAdditionalInformationV1 feesAndPricing(URI feesAndPricingUri) {
    setFeesAndPricing(feesAndPricingUri);
    return this;
  }

  /**
   * Get a URI to a Bundle that this product is part of
   * 
   * @return a URI to the Bundle description
   */
  @Schema(description = "Description of a bundle that this product can be part of", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("bundleUri")
  public URI getBundle();

  /**
   * Set a URI to a Bundle that this product is part of
   * 
   * @param bundleUri linking to the bundle information
   */
  @JsonSetter("bundleUri")
  public void setBundle(URI bundleUri);

  public default URI bundle() {
    return getBundle();
  }

  public default BankingProductAdditionalInformationV1 bundle(URI bundleUri) {
    setBundle(bundleUri);
    return this;
  }
}
