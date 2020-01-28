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

import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Detailed Information", allOf = {BankingProductV2.class})
public interface BankingProductDetailV2 extends BankingProductV2 {

  /**
   * Get an array of bundles this product belongs to
   * @return List of Product Bundle information
   */
  @Schema(
      description = "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also")
  @JsonGetter("bundles")
  @Valid
  public List<BankingProductBundleV1> getBundles();

  public default List<BankingProductBundleV1> bundles() {
    return getBundles();
  }
  
  /**
   * Set an array of bundles this product belongs to
   * @param bundles containing a list of product bundles
   */
  @JsonSetter("bundles")
  public void setBundles(List<BankingProductBundleV1> bundles);
  
  public default BankingProductDetailV2 bundles(List<BankingProductBundleV1> bundles) {
    setBundles(bundles);
    return this;
  }

  /**
   * Get an array of product features
   * @return an array of Product Feature objects
   */
  @Schema(description = "Array of features available for the product")
  @JsonGetter("features")
  @Valid
  public List<BankingProductFeatureV1> getFeatures();

  public default List<BankingProductFeatureV1> features() {
    return getFeatures();
  }

  /**
   * Set Banking Product Features
   * @param features as a List of BankingProductFeature
   */
  @JsonSetter("features")
  public void setFeatures(List<BankingProductFeatureV1> features);
  
  public default BankingProductDetailV2 features(List<BankingProductFeatureV1> features) {
    setFeatures(features);
    return this;
  }

  /**
   * Get Banking Product Constraints
   * @return a list of constraints for the product
   */
  @Schema(
      description = "Constraints on the application for or operation of the product such as minimum balances or limit thresholds")
  @JsonGetter("constraints")
  @Valid
  public List<BankingProductConstraintV1> getConstraints();
  
  public default List<BankingProductConstraintV1> constraints() {
    return getConstraints();
  }
  
  /**
   * Set Banking Product Constraints
   * @param constraints containing a list of Product Constraints
   */
  @JsonSetter("constraints")
  public void setConstraints(List<BankingProductConstraintV1> constraints);
  
  public default BankingProductDetailV2 constraints(List<BankingProductConstraintV1> constraints) {
    setConstraints(constraints);
    return this;
  }

  /**
   * Get Eligibility requirements for a product
   * @return a list of eligibility criteria
   */
  @Schema(description = "Eligibility criteria for the product")
  @JsonGetter("eligibility")
  @Valid
  public List<BankingProductEligibilityV1> getEligibility();
  
  public default List<BankingProductEligibilityV1> eligibility() {
    return getEligibility();
  }
  
  /**
   * Set Eligibility criteria for a product
   * @param eligibility containing a list of Banking Product Eligibility components
   */
  @JsonSetter("eligibility")
  public void setEligibility(List<BankingProductEligibilityV1> eligibility);
  
  public default BankingProductDetailV2 eligibility(List<BankingProductEligibilityV1> eligibility) {
    setEligibility(eligibility);
    return this;
  }

  /**
   * Get a list of product fees applicable for this product
   * @return List of BankingProductFee for this product
   */
  @Schema(description = "Fees applicable for the product")
  @JsonGetter("fees")
  @Valid
  public List<BankingProductFeeV1> getFees();

  public default List<BankingProductFeeV1> fees() {
    return getFees();
  }
  

  @JsonSetter("fees")
  public void setFees(List<BankingProductFeeV1> fees);

  public default BankingProductDetailV2 fees(List<BankingProductFeeV1> fees) {
    setFees(fees);
    return this;
  }

  @Schema(description = "Interest rates available for deposits")
  @JsonGetter("depositRates")
  @Valid
  public List<BankingProductDepositRateV1> getDepositRates();

  public default List<BankingProductDepositRateV1> depositRates() {
    return getDepositRates();
  }

  @JsonSetter("depositRates")
  public void setDepositRates(List<BankingProductDepositRateV1> depositRates);
  
  public default BankingProductDetailV2 depositRates(List<BankingProductDepositRateV1> depositRates) {
    setDepositRates(depositRates);
    return this;
  }

  @Schema(description = "Interest rates charged against lending balances")
  @Valid
  @JsonGetter("lendingRates")
  public List<BankingProductLendingRateV1> getLendingRates();
  
  public default List<BankingProductLendingRateV1> lendingRates() {
    return getLendingRates();
  }
  
  @JsonSetter("lendingRates")
  public void setLendingRates(List<BankingProductLendingRateV1> lendingRates);

  public default BankingProductDetailV2 lendingRates(List<BankingProductLendingRateV1> lendingRates) {
    setLendingRates(lendingRates);
    return this;
  }
}
