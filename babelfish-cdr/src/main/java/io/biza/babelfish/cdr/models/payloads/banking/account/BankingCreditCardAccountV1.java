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
package io.biza.babelfish.cdr.models.payloads.banking.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.AmountStringToBigDecimalConverter;
import io.biza.babelfish.common.jackson.BigDecimalToAmountStringConverter;
import io.biza.babelfish.common.jackson.CurrencyToStringConverter;
import io.biza.babelfish.common.jackson.LocalDateToStringConverter;
import io.biza.babelfish.common.jackson.StringToCurrencyConverter;
import io.biza.babelfish.common.jackson.StringToLocalDateConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Credit Card Account Details", name = "BankingCreditCardAccountV1")
public class BankingCreditCardAccountV1
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.account.BankingCreditCardAccountV1 {
  @Schema(description = "The minimum payment amount due for the next card payment", required = true,
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @NotNull
  @JsonProperty("minPaymentAmount")
  BigDecimal minPaymentAmount;

  @Schema(description = "The amount due for the next card payment", required = true,
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @NotNull
  @JsonProperty("paymentDueAmount")
  BigDecimal paymentDueAmount;

  @Schema(description = "If absent assumes AUD", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty(value = "paymentCurrency", defaultValue = "AUD")
  @Builder.Default
  Currency paymentCurrency = Currency.getInstance("AUD");

  @Schema(description = "Date that the next payment for the card is due", required = true,
      type = "string", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @NotNull
  @JsonProperty("paymentDueDate")
  LocalDate paymentDueDate;

}
