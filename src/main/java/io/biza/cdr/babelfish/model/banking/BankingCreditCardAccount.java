/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import javax.validation.Valid;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Credit Card Account Details")
public abstract class BankingCreditCardAccount<T extends BankingCreditCardAccount<T>> {
  @BabelFishModelProperty(description = "The minimum payment amount due for the next card payment",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal minPaymentAmount;

  public BigDecimal minPaymentAmount() {
    return getMinPaymentAmount();
  }

  @SuppressWarnings("unchecked")
  public T minPaymentAmount(BigDecimal minPaymentAmount) {
    setMinPaymentAmount(minPaymentAmount);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The amount due for the next card payment", required = true,
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal paymentDueAmount;

  public BigDecimal paymentDueAmount() {
    return getPaymentDueAmount();
  }

  @SuppressWarnings("unchecked")
  public T paymentDueAmount(BigDecimal paymentDueAmount) {
    setPaymentDueAmount(paymentDueAmount);
    return (T) this;
  }

  @BabelFishModelProperty(description = "If absent assumes AUD", dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency paymentCurrency = Currency.getInstance("AUD");

  public Currency paymentCurrency() {
    return getPaymentCurrency();
  }

  @SuppressWarnings("unchecked")
  public T paymentCurrency(Currency paymentCurrency) {
    setPaymentCurrency(paymentCurrency);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Date that the next payment for the card is due",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
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
