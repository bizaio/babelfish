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
package io.biza.babelfish.cdr.models.payloads.banking.product;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.BankingProductRateTierApplicationMethod;
import io.biza.babelfish.cdr.enumerations.CommonUnitOfMeasureType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Defines the criteria and conditions for which a rate applies", name = "BankingProductRateTierV1")
public class BankingProductRateTierV1 {
  @Schema(description = "A display name for the tier", required = true)
  @NotNull
  @JsonProperty("name")
  String name;

  @Schema(
      description = "The unit of measure that applies to the tierValueMinimum and tierValueMaximum values e.g. 'DOLLAR', 'MONTH' (in the case of term deposit tiers), 'PERCENT' (in the case of loan-to-value ratio or LVR)",
      required = true)
  @NotNull
  @JsonProperty("unitOfMeasure")
  CommonUnitOfMeasureType unitOfMeasure;

  @Schema(
      description = "The number of tierUnitOfMeasure units that form the lower bound of the tier. The tier should be inclusive of this value",
      required = true)
  @NotNull
  @JsonProperty("minimumValue")
  BigDecimal minimumValue;

  @Schema(
      description = "The number of tierUnitOfMeasure units that form the upper bound of the tier or band. For a tier with a discrete value (as opposed to a range of values e.g. 1 month) this must be the same as tierValueMinimum. Where this is the same as the tierValueMinimum value of the next-higher tier the referenced tier should be exclusive of this value. For example a term deposit of 2 months falls into the upper tier of the following tiers: (1 – 2 months, 2 – 3 months). If absent the tier's range has no upper bound.")
  @JsonProperty("maximumValue")
  BigDecimal maximumValue;

  @Schema(
      description = "The method used to calculate the amount to be applied using one or more tiers. A single rate may be applied to the entire balance or each applicable tier rate is applied to the portion of the balance that falls into that tier (referred to as 'bands' or 'steps')")
  @JsonProperty("rateApplicationMethod")
  BankingProductRateTierApplicationMethod rateApplicationMethod;

  @Schema(description = "Banking Product Rate Applicability Conditions")
  @JsonProperty("applicabilityConditions")
  @Valid
  BankingProductRateTierApplicabilityV1 applicabilityConditions;
}
