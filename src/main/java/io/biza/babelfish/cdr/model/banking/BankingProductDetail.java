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
package io.biza.babelfish.cdr.model.banking;

import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Banking Product Detailed Information", allOf = {BankingProduct.class})
public abstract class BankingProductDetail<T> extends BankingProduct<T> {

  @Schema(
      description = "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also")
  @JsonProperty("bundles")
  @Valid
  List<BankingProductBundle<?>> bundles;

  public List<BankingProductBundle<?>> bundles() {
    return bundles;
  }

  @SuppressWarnings("unchecked")
  public T bundles(List<BankingProductBundle<?>> bundles) {
    this.bundles = bundles;
    return (T) this;
  }

  @Schema(description = "Array of features available for the product")
  @JsonProperty("features")
  @Valid
  List<BankingProductFeature<?>> features;

  public List<BankingProductFeature<?>> features() {
    return features;
  }

  @SuppressWarnings("unchecked")
  public T features(List<BankingProductFeature<?>> features) {
    this.features = features;
    return (T) this;
  }

  @Schema(
      description = "Constraints on the application for or operation of the product such as minimum balances or limit thresholds")
  @JsonProperty("constraints")
  @Valid
  List<BankingProductConstraint<?>> constraints;

  public List<BankingProductConstraint<?>> constraints() {
    return constraints;
  }

  @SuppressWarnings("unchecked")
  public T constraints(List<BankingProductConstraint<?>> constraints) {
    this.constraints = constraints;
    return (T) this;
  }

  @Schema(description = "Eligibility criteria for the product")
  @JsonProperty("eligibility")
  @Valid
  List<BankingProductEligibility<?>> eligibility;

  public List<BankingProductEligibility<?>> eligibility() {
    return eligibility;
  }

  @SuppressWarnings("unchecked")
  public T eligibility(List<BankingProductEligibility<?>> eligibility) {
    this.eligibility = eligibility;
    return (T) this;
  }

  @Schema(description = "Fees applicable for the product")
  @JsonProperty("fees")
  @Valid
  List<BankingProductFee<?>> fees;

  public List<BankingProductFee<?>> fees() {
    return fees;
  }

  @SuppressWarnings("unchecked")
  public T fees(List<BankingProductFee<?>> fees) {
    this.fees = fees;
    return (T) this;
  }

  @Schema(description = "Interest rates available for deposits")
  @JsonProperty("depositRates")
  @Valid
  List<BankingProductDepositRate<?>> depositRates;

  public List<BankingProductDepositRate<?>> depositRates() {
    return depositRates;
  }

  @SuppressWarnings("unchecked")
  public T depositRates(List<BankingProductDepositRate<?>> depositRates) {
    this.depositRates = depositRates;
    return (T) this;
  }

  @Schema(description = "Interest rates charged against lending balances")
  @JsonProperty("lendingRates")
  @Valid
  List<BankingProductLendingRate<?>> lendingRates;

  public List<BankingProductLendingRate<?>> lendingRates() {
    return lendingRates;
  }

  @SuppressWarnings("unchecked")
  public T lendingRates(List<BankingProductLendingRate<?>> lendingRates) {
    this.lendingRates = lendingRates;
    return (T) this;
  }
}
