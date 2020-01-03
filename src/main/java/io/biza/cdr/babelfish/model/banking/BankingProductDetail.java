/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.util.List;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description =  "Banking Product Detailed Information", parent = BankingProduct.class)
public interface BankingProductDetail extends BankingProduct {

    @BabelFishModelProperty(
        description =  "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also"
    )
    @JsonGetter("bundles")
    public List<BankingProductBundle> getBundles();
    
    @JsonSetter("bundles")
    public void setBundles(List<BankingProductBundle> bundles);
    
    public default BankingProductDetail bundles(List<BankingProductBundle> bundles) {
      setBundles(bundles);
      return this;
    }
    
    public default void addBundle(BankingProductBundle bundle) {
      getBundles().add(bundle);
    }
    
    @BabelFishModelProperty(
        description =  "Array of features available for the product"
    )
    @JsonGetter("features")
    public List<BankingProductFeature> getFeatures();
    
    @JsonSetter("features")
    public void setFeatures(List<BankingProductFeature> features);
    
    public default BankingProductDetail features(List<BankingProductFeature> features) {
      setFeatures(features);
      return this;
    }
    
    public default void addFeature(BankingProductFeature feature) {
      getFeatures().add(feature);
    }
    
    @BabelFishModelProperty(
        description =  "Constraints on the application for or operation of the product such as minimum balances or limit thresholds"
    )
    @JsonGetter("constraints")
    public List<BankingProductConstraint> getConstraints();
    
    @JsonSetter("constraints")
    public void setConstraints(List<BankingProductConstraint> constraints);
    
    public default BankingProductDetail constraints(List<BankingProductConstraint> constraints) {
      setConstraints(constraints);
      return this;
    }
    
    public default void addConstraint(BankingProductConstraint constraint) {
      getConstraints().add(constraint);
    }
    
    @BabelFishModelProperty(
        description =  "Eligibility criteria for the product"
    )
    @JsonGetter("eligibility")
    public List<BankingProductEligibility> getEligibility();
    
    @JsonSetter("eligibility")
    public void setEligibility(List<BankingProductEligibility> eligibility);
    
    public default BankingProductDetail eligibility(List<BankingProductEligibility> eligibility) {
      setEligibility(eligibility);
      return this;
    }
    
    public default void addEligibility(BankingProductEligibility eligibility) {
      getEligibility().add(eligibility);
    }
    
    @BabelFishModelProperty(
        description =  "Fees applicable for the product"
    )
    @JsonGetter("fees")
    public List<BankingProductFee> getFees();
    
    @JsonSetter("fees")
    public void setFees(List<BankingProductFee> fees);
    
    public default BankingProductDetail fees(List<BankingProductFee> fees) {
      setFees(fees);
      return this;
    }
    
    public default void addFee(BankingProductFee fee) {
      getFees().add(fee);
    }
    

    @BabelFishModelProperty(
        description =  "Interest rates available for deposits"
    )
    @JsonGetter("depositRates")
    public List<BankingProductDepositRate> getDepositRates();
    
    @JsonSetter("depositRates")
    public void setDepositRates(List<BankingProductDepositRate> depositRates);
    
    public default BankingProductDetail depositRates(List<BankingProductDepositRate> depositRates) {
      setDepositRates(depositRates);
      return this;
    }
    
    public default void addDepositRate(BankingProductDepositRate depositRate) {
      getDepositRates().add(depositRate);
    }
    
    @BabelFishModelProperty(
        description =  "Interest rates charged against lending balances"
    )
    @JsonGetter("lendingRates")
    public List<BankingProductLendingRate> getLendingRates();
    
    @JsonSetter("lendingRates")
    public void setLendingRates(List<BankingProductLendingRate> lendingRates);
    
    public default BankingProductDetail lendingRates(List<BankingProductLendingRate> lendingRates) {
      setLendingRates(lendingRates);
      return this;
    }
    
    public default void addLendingRate(BankingProductLendingRate lendingRate) {
      getLendingRates().add(lendingRate);
    }}
