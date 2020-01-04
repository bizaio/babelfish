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
import java.net.URI;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateType;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Banking Product Lending Rate Definition")
public abstract class BankingProductLendingRate {
  @BabelFishModelProperty(
      description = "The type of rate (fixed, variable, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @NonNull
  @NotNull
  BankingProductLendingRateType lendingRateType;

  @BabelFishModelProperty(description = "The rate to be applied", required = true)
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  BigDecimal rate;

  @BabelFishModelProperty(description = "A comparison rate equivalent for this rate")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  BigDecimal comparisonRate;

  @BabelFishModelProperty(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  Period calculationFrequency;

  @BabelFishModelProperty(
      description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  Period applicationFrequency;

  @BabelFishModelProperty(
      description = "When loan payments are due to be paid within each period. The investment benefit of earlier payments affect the rate that can be offered")
  BankingProductLendingRateType interestPaymentDue;

  @BabelFishModelProperty(description = "Rate tiers applicable for this rate")
  List<BankingProductRateTier> tiers;

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [lendingRateType](#tocSproductlendingratetypedoc) specified. Whether mandatory or not is dependent on the value of [lendingRateType](#tocSproductlendingratetypedoc)")
  String additionalValue;

  @BabelFishModelProperty(description = "Display text providing more information on the rate.")
  String additionalInfo;

  @BabelFishModelProperty(description = "Link to a web page with more information on this rate",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI additionalInfoUri;

  @AssertTrue(
      message = "Additional Value must be an Duration String when Lending Rate Type INTRODUCTORY or FIXED")
  private boolean isValueDuration() {
    return Arrays.asList(new BankingProductLendingRateType[] {
        BankingProductLendingRateType.INTRODUCTORY, BankingProductLendingRateType.FIXED
    }).contains(lendingRateType) ? FormatChecker.isDuration(additionalValue) : true;
  }
}
