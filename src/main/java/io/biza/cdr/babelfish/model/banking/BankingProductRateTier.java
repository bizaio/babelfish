/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductRateTierApplicationMethod;
import io.biza.cdr.babelfish.v1.enumerations.CommonUnitOfMeasureType;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Defines the criteria and conditions for which a rate applies")
public abstract class BankingProductRateTier<T extends BankingProductRateTier<T>> {
  @BabelFishModelProperty(description = "A display name for the tier", required = true)
  @NonNull
  @NotNull
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The unit of measure that applies to the tierValueMinimum and tierValueMaximum values e.g. 'DOLLAR', 'MONTH' (in the case of term deposit tiers), 'PERCENT' (in the case of loan-to-value ratio or LVR)",
      required = true)
  @NonNull
  @NotNull
  CommonUnitOfMeasureType unitOfMeasure;

  public CommonUnitOfMeasureType unitOfMeasure() {
    return getUnitOfMeasure();
  }

  @SuppressWarnings("unchecked")
  public T unitOfMeasure(CommonUnitOfMeasureType unitOfMeasure) {
    setUnitOfMeasure(unitOfMeasure);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The number of tierUnitOfMeasure units that form the lower bound of the tier. The tier should be inclusive of this value",
      required = true)
  @NonNull
  @NotNull
  BigDecimal minimumValue;

  public BigDecimal minimumValue() {
    return getMinimumValue();
  }

  @SuppressWarnings("unchecked")
  public T minimumValue(BigDecimal minimumValue) {
    setMinimumValue(minimumValue);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The number of tierUnitOfMeasure units that form the upper bound of the tier or band. For a tier with a discrete value (as opposed to a range of values e.g. 1 month) this must be the same as tierValueMinimum. Where this is the same as the tierValueMinimum value of the next-higher tier the referenced tier should be exclusive of this value. For example a term deposit of 2 months falls into the upper tier of the following tiers: (1 – 2 months, 2 – 3 months). If absent the tier's range has no upper bound.")
  BigDecimal maximumValue;

  public BigDecimal maximumValue() {
    return getMaximumValue();
  }

  @SuppressWarnings("unchecked")
  public T maximumValue(BigDecimal maximumValue) {
    setMaximumValue(maximumValue);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The method used to calculate the amount to be applied using one or more tiers. A single rate may be applied to the entire balance or each applicable tier rate is applied to the portion of the balance that falls into that tier (referred to as 'bands' or 'steps')")
  BankingProductRateTierApplicationMethod rateApplicationMethod;

  public BankingProductRateTierApplicationMethod rateApplicationMethod() {
    return getRateApplicationMethod();
  }

  @SuppressWarnings("unchecked")
  public T rateApplicationMethod(BankingProductRateTierApplicationMethod rateApplicationMethod) {
    setRateApplicationMethod(rateApplicationMethod);
    return (T) this;
  }

  @BabelFishModelProperty
  BankingProductRateCondition<?> applicabilityConditions;

  public BankingProductRateCondition<?> applicabilityConditions() {
    return getApplicabilityConditions();
  }

  @SuppressWarnings("unchecked")
  public T applicabilityConditions(BankingProductRateCondition<?> applicabilityConditions) {
    setApplicabilityConditions(applicabilityConditions);
    return (T) this;
  }

  @AssertTrue(
      message = "Minimum and Maximum values must be equal when Unit Of Measure is a discrete value")
  private boolean isMinMaxEqual() {
    return Arrays.asList(
        new CommonUnitOfMeasureType[] {CommonUnitOfMeasureType.DAY, CommonUnitOfMeasureType.MONTH})
        .contains(unitOfMeasure) && maximumValue != null ? (minimumValue == maximumValue) : true;
  }
}
