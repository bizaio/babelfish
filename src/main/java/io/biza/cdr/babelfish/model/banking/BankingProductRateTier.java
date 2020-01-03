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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductRateTierApplicationMethod;
import io.biza.cdr.babelfish.v1.enumerations.CommonUnitOfMeasureType;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Defines the criteria and conditions for which a rate applies")
public interface BankingProductRateTier {


  @BabelFishModelProperty(description = "A display name for the tier", required = true)
  @JsonGetter("name")
  public String getName();

  @JsonSetter("name")
  public void setName(@NotNull String name);

  public default BankingProductRateTier name(@NotNull String name) {
    setName(name);
    return this;
  }

  @BabelFishModelProperty(
      description = "The unit of measure that applies to the tierValueMinimum and tierValueMaximum values e.g. 'DOLLAR', 'MONTH' (in the case of term deposit tiers), 'PERCENT' (in the case of loan-to-value ratio or LVR)",
      required = true)
  @JsonGetter("unitOfMeasure")
  public CommonUnitOfMeasureType getUnitOfMeasure();

  @JsonSetter("unitOfMeasure")
  public void setUnitOfMeasure(@NotNull CommonUnitOfMeasureType unitOfMeasure);

  public default BankingProductRateTier unitOfMeasure(
      @NotNull CommonUnitOfMeasureType unitOfMeasure) {
    setUnitOfMeasure(unitOfMeasure);
    return this;
  }

  @BabelFishModelProperty(
      description = "The number of tierUnitOfMeasure units that form the lower bound of the tier. The tier should be inclusive of this value",
      required = true)
  @JsonGetter("minimumValue")
  public BigDecimal getMinimumValue();

  @JsonSetter("minimumValue")
  public void setMinimumValue(@NotNull BigDecimal minimumValue);

  public default BankingProductRateTier minimumValue(@NotNull BigDecimal minimumValue) {
    setMinimumValue(minimumValue);
    return this;
  }

  @BabelFishModelProperty(
      description = "The number of tierUnitOfMeasure units that form the upper bound of the tier or band. For a tier with a discrete value (as opposed to a range of values e.g. 1 month) this must be the same as tierValueMinimum. Where this is the same as the tierValueMinimum value of the next-higher tier the referenced tier should be exclusive of this value. For example a term deposit of 2 months falls into the upper tier of the following tiers: (1 – 2 months, 2 – 3 months). If absent the tier's range has no upper bound.")
  @JsonGetter("maximumValue")
  public BigDecimal getMaximumValue();

  @JsonSetter("maximumValue")
  public void setMaximumValue(@NotNull BigDecimal maximumValue);

  public default BankingProductRateTier maximumValue(@NotNull BigDecimal maximumValue) {
    setMaximumValue(maximumValue);
    return this;
  }

  @BabelFishModelProperty(
      description = "The method used to calculate the amount to be applied using one or more tiers. A single rate may be applied to the entire balance or each applicable tier rate is applied to the portion of the balance that falls into that tier (referred to as 'bands' or 'steps')")
  @JsonGetter("rateApplicationMethod")
  public BankingProductRateTierApplicationMethod getRateApplicationMethod();

  @JsonSetter("rateApplicationMethod")
  public void setRateApplicationMethod(
      BankingProductRateTierApplicationMethod rateApplicationMethod);

  public default BankingProductRateTier rateApplicationMethod(
      BankingProductRateTierApplicationMethod rateApplicationMethod) {
    setRateApplicationMethod(rateApplicationMethod);
    return this;
  }

  @BabelFishModelProperty(description = "Applicability Conditions")
  @JsonGetter("applicabilityConditions")
  public BankingProductRateCondition getApplicabilityConditions();

  @JsonSetter("applicabilityConditions")
  public void setApplicabilityConditions(BankingProductRateCondition applicabilityConditions);

  public default BankingProductRateTier applicabilityConditions(
      BankingProductRateCondition applicabilityConditions) {
    setApplicabilityConditions(applicabilityConditions);
    return this;
  }

  // TODO: Subtier Support removed

}
