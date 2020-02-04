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
import java.net.URI;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.BigDecimalToRateStringConverter;
import io.biza.babelfish.cdr.converters.PeriodToStringConverter;
import io.biza.babelfish.cdr.converters.RateStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.StringToPeriodConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BankingProductDepositRateType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
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
@Schema(description = "A Description of a Deposit Rate", name = "BankingProductDepositRateV1")
public class BankingProductDepositRateV1 {
  @Schema(
      description = "The type of rate (base, bonus, etc). See the next section for an overview of valid values and their meaning",
      required = true)
  @NotNull
  @JsonProperty("depositRateType")
  BankingProductDepositRateType depositRateType;

  @Schema(description = "The rate to be applied", required = true, type = "string")
  @NotNull(message = "Rate to be applied is required")
  @JsonSerialize(converter = BigDecimalToRateStringConverter.class)
  @JsonDeserialize(converter = RateStringToBigDecimalConverter.class)
  @JsonProperty("rate")
  @Min(0)
  @Max(2)
  BigDecimal rate;

  @Schema(
      description = "The period after which the rate is applied to the balance to calculate the amount due for the period. Calculation of the amount is often daily (as balances may change) but accumulated until the total amount is 'applied' to the account (see applicationFrequency). Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("calculationFrequency")
  Period calculationFrequency;

  @Schema(
      description = "The period after which the calculated amount(s) (see calculationFrequency) are 'applied' (i.e. debited or credited) to the account. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
      type = "string")
  @JsonSerialize(converter = PeriodToStringConverter.class)
  @JsonDeserialize(converter = StringToPeriodConverter.class)
  @JsonProperty("applicationFrequency")
  Period applicationFrequency;

  @Schema(description = "Rate tiers applicable for this rate")
  @JsonProperty("tiers")
  @Valid
  List<BankingProductRateTierV1> tiers;

  @Schema(
      description = "Generic field containing additional information relevant to the [depositRateType](#tocSproductdepositratetypedoc) specified. Whether mandatory or not is dependent on the value of [depositRateType](#tocSproductdepositratetypedoc)")
  @JsonProperty("additionalValue")
  String additionalValue;

  @Schema(description = "Display text providing more information on the rate")
  @JsonProperty("additionalInfo")
  String additionalInfo;

  @Schema(description = "Link to a web page with more information on this rate", type = "string",
      format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("additionalInfoUri")
  URI additionalInfoUri;
  
  @AssertTrueBabelfish(
      message = "Additional Value must be a Duration String when Fee type is FIXED or INTRODUCTORY",
      fields = {"additionalValue"})
  private boolean isValueDuration() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.FIXED,
            BankingProductDepositRateType.INTRODUCTORY}).contains(depositRateType())
                ? FormatChecker.isDefined(additionalValue())
                    && FormatChecker.isDurationString(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value must String when Deposit Rate Type is BONUS, BUNDLE_BONUS, FLOATING or MARKET_LINKED",
      fields = {"additionalValue"})
  private boolean isValueString() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays.asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.BONUS,
            BankingProductDepositRateType.BUNDLE_BONUS, BankingProductDepositRateType.FLOATING,
            BankingProductDepositRateType.MARKET_LINKED}).contains(depositRateType())
                ? FormatChecker.isNotEmpty(additionalValue())
                : true)
        : true;
  }

  @AssertTrueBabelfish(
      message = "Additional Value should be null when Deposit Rate Type is VARIABLE",
      fields = {"additionalValue"})
  private boolean isValueStringNull() {
    return FormatChecker.isDefined(depositRateType())
        ? (Arrays
            .asList(new BankingProductDepositRateType[] {BankingProductDepositRateType.VARIABLE})
            .contains(depositRateType()) ? !FormatChecker.isNotEmpty(additionalValue()) : true)
        : true;
  }
}
