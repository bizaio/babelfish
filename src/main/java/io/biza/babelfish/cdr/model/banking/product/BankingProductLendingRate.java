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
package io.biza.babelfish.cdr.model.banking.product;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Period;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.BigDecimalToRateStringConverter;
import io.biza.babelfish.converter.cdr.PeriodToStringConverter;
import io.biza.babelfish.converter.cdr.RateStringToBigDecimalConverter;
import io.biza.babelfish.converter.cdr.StringToPeriodConverter;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.biza.babelfish.enumerations.cdr.BankingProductLendingRateInterestPaymentType;
import io.biza.babelfish.enumerations.cdr.BankingProductLendingRateType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "Banking Product Lending Rate Definition")
public abstract class BankingProductLendingRate<T> {
  @Schema(
      description = "The type of rate (fixed, variable, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonProperty("lendingRateType")
  BankingProductLendingRateType lendingRateType;

  public BankingProductLendingRateType lendingRateType() {
    return getLendingRateType();
  }

  @SuppressWarnings("unchecked")
  public T lendingRateType(BankingProductLendingRateType lendingRateType) {
    setLendingRateType(lendingRateType);
    return (T) this;
  }

  @Schema(description = "The rate to be applied", required = true)
  @NotNull
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("rate")
  BigDecimal rate;

  public BigDecimal rate() {
    return getRate();
  }

  @SuppressWarnings("unchecked")
  public T rate(BigDecimal rate) {
    setRate(rate);
    return (T) this;
  }

  @Schema(description = "A comparison rate equivalent for this rate")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("comparisonRate")
  BigDecimal comparisonRate;

  public BigDecimal comparisonRate() {
    return getComparisonRate();
  }

  @SuppressWarnings("unchecked")
  public T comparisonRate(BigDecimal comparisonRate) {
    setComparisonRate(comparisonRate);
    return (T) this;
  }

  @Schema(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("calculationFrequency")
  Period calculationFrequency;

  public Period calculationFrequency() {
    return getCalculationFrequency();
  }

  @SuppressWarnings("unchecked")
  public T calculationFrequency(Period calculationFrequency) {
    setCalculationFrequency(calculationFrequency);
    return (T) this;
  }

  @Schema(
      description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("applicationFrequency")
  Period applicationFrequency;

  public Period applicationFrequency() {
    return getApplicationFrequency();
  }

  @SuppressWarnings("unchecked")
  public T applicationFrequency(Period applicationFrequency) {
    setApplicationFrequency(applicationFrequency);
    return (T) this;
  }

  @Schema(
      description = "When loan payments are due to be paid within each period. The investment benefit of earlier payments affect the rate that can be offered")
  @JsonProperty("interestPaymentDue")
  BankingProductLendingRateInterestPaymentType interestPaymentDue;

  public BankingProductLendingRateInterestPaymentType interestPaymentDue() {
    return getInterestPaymentDue();
  }

  @SuppressWarnings("unchecked")
  public T interestPaymentDue(BankingProductLendingRateInterestPaymentType interestPaymentDue) {
    setInterestPaymentDue(interestPaymentDue);
    return (T) this;
  }

  @Schema(description = "Rate tiers applicable for this rate")
  @JsonProperty("tiers")
  @Valid
  List<BankingProductRateTier<?>> tiers;

  public List<BankingProductRateTier<?>> tiers() {
    return getTiers();
  }

  @SuppressWarnings("unchecked")
  public T tiers(List<BankingProductRateTier<?>> tiers) {
    setTiers(tiers);
    return (T) this;
  }


  @Schema(
      description = "Generic field containing additional information relevant to the [lendingRateType](#tocSproductlendingratetypedoc) specified. Whether mandatory or not is dependent on the value of [lendingRateType](#tocSproductlendingratetypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @Schema(description = "Display text providing more information on the rate.")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @Schema(description = "Link to a web page with more information on this rate", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;

  public URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return (T) this;
  }
}
