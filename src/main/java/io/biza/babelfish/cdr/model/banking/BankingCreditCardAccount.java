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
package io.biza.babelfish.cdr.model.banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
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

@Schema(description = "Credit Card Account Details")
public abstract class BankingCreditCardAccount<T> {
  @Schema(description = "The minimum payment amount due for the next card payment", required = true,
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @NotNull
  @JsonProperty("minPaymentAmount")
  BigDecimal minPaymentAmount;

  public BigDecimal minPaymentAmount() {
    return getMinPaymentAmount();
  }

  @SuppressWarnings("unchecked")
  public T minPaymentAmount(BigDecimal minPaymentAmount) {
    setMinPaymentAmount(minPaymentAmount);
    return (T) this;
  }

  @Schema(description = "The amount due for the next card payment", required = true,
      type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @NotNull
  @JsonProperty("paymentDueAmount")
  BigDecimal paymentDueAmount;

  public BigDecimal paymentDueAmount() {
    return getPaymentDueAmount();
  }

  @SuppressWarnings("unchecked")
  public T paymentDueAmount(BigDecimal paymentDueAmount) {
    setPaymentDueAmount(paymentDueAmount);
    return (T) this;
  }

  @Schema(description = "If absent assumes AUD", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("paymentCurrency")
  Currency paymentCurrency = Currency.getInstance("AUD");

  public Currency paymentCurrency() {
    return getPaymentCurrency();
  }

  @SuppressWarnings("unchecked")
  public T paymentCurrency(Currency paymentCurrency) {
    setPaymentCurrency(paymentCurrency);
    return (T) this;
  }

  @Schema(description = "Date that the next payment for the card is due", required = true,
      type = "string", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @NotNull
  @JsonProperty("paymentDueDate")
  LocalDate paymentDueDate;

  public LocalDate paymentDueDate() {
    return getPaymentDueDate();
  }

  @SuppressWarnings("unchecked")
  public T paymentDueDate(LocalDate paymentDueDate) {
    setPaymentDueDate(paymentDueDate);
    return (T) this;
  }
}
