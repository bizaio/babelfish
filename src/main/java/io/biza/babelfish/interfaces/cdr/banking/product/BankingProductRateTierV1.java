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

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.babelfish.enumerations.cdr.BankingProductRateTierApplicationMethod;
import io.biza.babelfish.enumerations.cdr.CommonUnitOfMeasureType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Defines the criteria and conditions for which a rate applies")
public interface BankingProductRateTierV1 {
  @Schema(description = "A display name for the tier", required = true)
  @NotNull
  @JsonGetter("name")
  public String getName();
  
  public default String name() {
    return getName();
  }  

  @JsonSetter("name")
  public void setName(String name);
  
  public default BankingProductRateTierV1 name(String name) {
    setName(name);
    return this;
  }

  @Schema(
      description = "The unit of measure that applies to the tierValueMinimum and tierValueMaximum values e.g. 'DOLLAR', 'MONTH' (in the case of term deposit tiers), 'PERCENT' (in the case of loan-to-value ratio or LVR)",
      required = true)
  @NotNull
  @JsonGetter("unitOfMeasure")
  public CommonUnitOfMeasureType getUnitOfMeasure();

  public default CommonUnitOfMeasureType unitOfMeasure() {
    return getUnitOfMeasure();
  }

  @JsonSetter("unitOfMeasure")
  public void setUnitOfMeasure(CommonUnitOfMeasureType unitOfMeasure);
  
  public default BankingProductRateTierV1 unitOfMeasure(CommonUnitOfMeasureType unitOfMeasure) {
    setUnitOfMeasure(unitOfMeasure);
    return this;
  }

  @Schema(
      description = "The number of tierUnitOfMeasure units that form the lower bound of the tier. The tier should be inclusive of this value",
      required = true)
  @NotNull
  @JsonGetter("minimumValue")
  public BigDecimal getMinimumValue();

  public default BigDecimal minimumValue() {
    return getMinimumValue();
  }
  
  @JsonSetter("minimumValue")
  public void setMinimumValue(BigDecimal minimumValue);

  public default BankingProductRateTierV1 minimumValue(BigDecimal minimumValue) {
    setMinimumValue(minimumValue);
    return this;
  }

  @Schema(
      description = "The number of tierUnitOfMeasure units that form the upper bound of the tier or band. For a tier with a discrete value (as opposed to a range of values e.g. 1 month) this must be the same as tierValueMinimum. Where this is the same as the tierValueMinimum value of the next-higher tier the referenced tier should be exclusive of this value. For example a term deposit of 2 months falls into the upper tier of the following tiers: (1 – 2 months, 2 – 3 months). If absent the tier's range has no upper bound.")
  @JsonGetter("maximumValue")
  public BigDecimal getMaximumValue();

  public default BigDecimal maximumValue() {
    return getMaximumValue();
  }
  
  @JsonSetter("maximumValue")
  public void setMaximumValue(BigDecimal maximumValue);

  public default BankingProductRateTierV1 maximumValue(BigDecimal maximumValue) {
    setMaximumValue(maximumValue);
    return this;
  }

  @Schema(
      description = "The method used to calculate the amount to be applied using one or more tiers. A single rate may be applied to the entire balance or each applicable tier rate is applied to the portion of the balance that falls into that tier (referred to as 'bands' or 'steps')")
  @JsonGetter("rateApplicationMethod")
  public BankingProductRateTierApplicationMethod getRateApplicationMethod();

  public default BankingProductRateTierApplicationMethod rateApplicationMethod() {
    return getRateApplicationMethod();
  }
  
  @JsonSetter("rateApplicationMethod")
  public void setRateApplicationMethod(BankingProductRateTierApplicationMethod rateApplicationMethod);

  public default BankingProductRateTierV1 rateApplicationMethod(BankingProductRateTierApplicationMethod rateApplicationMethod) {
    setRateApplicationMethod(rateApplicationMethod);
    return this;
  }

  @Schema(description = "Banking Product Rate Applicability Conditions")
  @Valid
  @JsonGetter("applicabilityConditions")
  public BankingProductRateTierApplicabilityV1 getApplicabilityConditions();

  public default BankingProductRateTierApplicabilityV1 applicabilityConditions() {
    return getApplicabilityConditions();
  }
  
  @JsonSetter("applicabilityConditions")
  public void setApplicabilityConditions(BankingProductRateTierApplicabilityV1 applicabilityConditions);

  public default BankingProductRateTierV1 applicabilityConditions(BankingProductRateTierApplicabilityV1 applicabilityConditions) {
    setApplicabilityConditions(applicabilityConditions);
    return this;
  }

}
