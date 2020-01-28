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
package io.biza.babelfish.cdr.banking.product.interfaces;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Period;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.PeriodToStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.StringToPeriodConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductLendingRateInterestPaymentType;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductLendingRateType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Lending Rate Definition")
public interface BankingProductLendingRateV1 {
  @Schema(
      description = "The type of rate (fixed, variable, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonGetter("lendingRateType")
  public BankingProductLendingRateType getType();

  public default BankingProductLendingRateType type() {
    return getType();
  }

  @JsonSetter("lendingRateType")
  public void setType(BankingProductLendingRateType type);

  public default BankingProductLendingRateV1 type(BankingProductLendingRateType rateType) {
    setType(rateType);
    return this;
  }

  @Schema(description = "The rate to be applied", required = true)
  @NotNull
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("rate")
  public BigDecimal getRate();

  public default BigDecimal rate() {
    return getRate();
  }

  @JsonSetter("rate")
  public void setRate(BigDecimal rate);

  public default BankingProductLendingRateV1 rate(BigDecimal rate) {
    setRate(rate);
    return this;
  }

  @Schema(description = "A comparison rate equivalent for this rate")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonGetter("comparisonRate")
  public BigDecimal getComparisonRate();

  public default BigDecimal comparisonRate() {
    return getComparisonRate();
  }

  @JsonSetter("comparisonRate")
  public void setComparisonRate(BigDecimal rate);

  public default BankingProductLendingRateV1 comparisonRate(BigDecimal rate) {
    setComparisonRate(rate);
    return this;
  }

  @Schema(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonGetter("calculationFrequency")
  public Period getCalculationFrequency();

  public default Period calculationFrequency() {
    return getCalculationFrequency();
  }

  @JsonSetter("calculationFrequency")
  public void setCalculationFrequency(Period calculationFrequency);

  public default BankingProductLendingRateV1 calculationFrequency(Period calculationFrequency) {
    setCalculationFrequency(calculationFrequency);
    return this;
  }


  @Schema(
      description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonGetter("applicationFrequency")
  public Period getApplicationFrequency();

  public default Period applicationFrequency() {
    return getApplicationFrequency();
  }

  @JsonSetter("applicationFrequency")
  public void setApplicationFrequency(Period applicationFrequency);

  public default BankingProductLendingRateV1 applicationFrequency(Period applicationFrequency) {
    setApplicationFrequency(applicationFrequency);
    return this;
  }

  @Schema(
      description = "When loan payments are due to be paid within each period. The investment benefit of earlier payments affect the rate that can be offered")
  @JsonGetter("interestPaymentDue")
  public BankingProductLendingRateInterestPaymentType getInterestPaymentDue();

  public default BankingProductLendingRateInterestPaymentType interestPaymentDue() {
    return getInterestPaymentDue();
  }

  @JsonSetter("interestPaymentDue")
  public void setInterestPaymentDue(
      BankingProductLendingRateInterestPaymentType interestPaymentDue);

  public default BankingProductLendingRateV1 interestPaymentDue(
      BankingProductLendingRateInterestPaymentType interestPaymentDue) {
    setInterestPaymentDue(interestPaymentDue);
    return this;
  }

  @Schema(description = "Rate tiers applicable for this rate")
  @Valid
  @JsonGetter("tiers")
  public List<BankingProductRateTierV1> getTiers();

  public default List<BankingProductRateTierV1> tiers() {
    return getTiers();
  }

  @JsonSetter("tiers")
  public void setTiers(List<BankingProductRateTierV1> tiers);

  public default BankingProductLendingRateV1 tiers(List<BankingProductRateTierV1> tiers) {
    setTiers(tiers);
    return this;
  }

  @Schema(
      description = "Generic field containing additional information relevant to the [lendingRateType](#tocSproductlendingratetypedoc) specified. Whether mandatory or not is dependent on the value of [lendingRateType](#tocSproductlendingratetypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  public default String additionalValue() {
    return getAdditionalValue();
  }

  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductLendingRateV1 additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  @Schema(description = "Display text providing more information on the rate.")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }

  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductLendingRateV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  @Schema(description = "Link to a web page with more information on this rate", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @JsonSetter
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductLendingRateV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }
}
