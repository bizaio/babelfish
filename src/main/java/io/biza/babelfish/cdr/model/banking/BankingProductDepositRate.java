/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Period;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.PeriodToStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.StringToPeriodConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductDepositRateType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "A Description of a Deposit Rate")
public abstract class BankingProductDepositRate<T> {
  @Schema(
      description = "The type of rate (base, bonus, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @NonNull
  @NotNull
  BankingProductDepositRateType depositRateType;

  public BankingProductDepositRateType depositRateType() {
    return getDepositRateType();
  }

  @SuppressWarnings("unchecked")
  public T depositRateType(BankingProductDepositRateType depositRateType) {
    setDepositRateType(depositRateType);
    return (T) this;
  }

  @Schema(description = "The rate to be applied", required = true,
      type = "string")
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  BigDecimal rate;

  public BigDecimal rate() {
    return getRate();
  }

  @SuppressWarnings("unchecked")
  public T rate(BigDecimal rate) {
    setRate(rate);
    return (T) this;
  }

  @Schema(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
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
  Period applicationFrequency;

  public Period applicationFrequency() {
    return getApplicationFrequency();
  }

  @SuppressWarnings("unchecked")
  public T applicationFrequency(Period applicationFrequency) {
    setApplicationFrequency(applicationFrequency);
    return (T) this;
  }

  @Schema(description = "Rate tiers applicable for this rate")
  List<BankingProductRateTier<?>> tiers;

  @Schema(
      description = "Generic field containing additional information relevant to the [depositRateType](#tocSproductdepositratetypedoc) specified. Whether mandatory or not is dependent on the value of [depositRateType](#tocSproductdepositratetypedoc)")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @Schema(description = "Display text providing more information on the rate")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @Schema(description = "Link to a web page with more information on this rate",
      type = "string")
  @JsonSerialize(converter = UriToUriStringConverter.class)
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
