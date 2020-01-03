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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateInterestPaymentType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateType;
import io.biza.cdr.babelfish.converters.BigDecimalToRateStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.RateStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Product Lending Rate Definition")
public interface BankingProductLendingRate {

  @BabelFishModelProperty(
      description = "The type of rate (fixed, variable, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @JsonGetter("lendingRateType")
  public BankingProductLendingRateType getType();

  @JsonSetter("lendingRateType")
  public void setType(@NotNull BankingProductLendingRateType type);

  public default BankingProductLendingRate type(@NotNull BankingProductLendingRateType type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(description = "The rate to be applied", required = true)
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("rate")
  public BigDecimal getRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("rate")
  public void setRate(BigDecimal rate);

  public default BankingProductLendingRate rate(BigDecimal rate) {
    setRate(rate);
    return this;
  }

  @BabelFishModelProperty(description = "A comparison rate equivalent for this rate")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonGetter("comparisonRate")
  public BigDecimal getComparisonRate();

  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonSetter("comparisonRate")
  public void setComparisonRate(BigDecimal comparisonComparisonRate);

  public default BankingProductLendingRate comparisonComparisonRate(BigDecimal comparisonRate) {
    setComparisonRate(comparisonRate);
    return this;
  }

  @BabelFishModelProperty(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonGetter("calculationFrequency")
  public Period getCalculationFrequency();

  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonSetter("calculationFrequency")
  public void setCalculationFrequency(Period calculationFrequency);

  public default BankingProductLendingRate calculationFrequency(Period calculationFrequency) {
    setCalculationFrequency(calculationFrequency);
    return this;
  }

  @BabelFishModelProperty(
      description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonGetter("applicationFrequency")
  public Period getApplicationFrequency();

  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonSetter("applicationFrequency")
  public void setApplicationFrequency(Period applicationFrequency);

  public default BankingProductLendingRate applicationFrequency(Period applicationFrequency) {
    setApplicationFrequency(applicationFrequency);
    return this;
  }

  @BabelFishModelProperty(
      description = "When loan payments are due to be paid within each period. The investment benefit of earlier payments affect the rate that can be offered")
  @JsonGetter("interestPaymentDue")
  public BankingProductLendingRateInterestPaymentType getInterestPaymentDue();

  @JsonSetter("interestPaymentDue")
  public void setInterestPaymentDue(
      BankingProductLendingRateInterestPaymentType interestPaymentDue);

  public default BankingProductLendingRate interestPaymentDue(
      BankingProductLendingRateInterestPaymentType interestPaymentDue) {
    setInterestPaymentDue(interestPaymentDue);
    return this;
  }

  @BabelFishModelProperty(description = "Rate tiers applicable for this rate")
  @JsonGetter("tiers")
  public List<BankingProductRateTier> getRateTiers();

  @JsonSetter("tiers")
  public void setRateTiers(List<BankingProductRateTier> tiers);

  public default BankingProductLendingRate tiers(List<BankingProductRateTier> tiers) {
    setRateTiers(tiers);
    return this;
  }

  public default void addRateTier(BankingProductRateTier tier) {
    getRateTiers().add(tier);
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [lendingRateType](#tocSproductlendingratetypedoc) specified. Whether mandatory or not is dependent on the value of [lendingRateType](#tocSproductlendingratetypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductLendingRate additionalValue(@NotNull String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the rate.")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductLendingRate additionalInfo(@NotNull String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @BabelFishModelProperty(description = "Link to a web page with more information on this rate",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductLendingRate additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }


}
