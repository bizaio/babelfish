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
package io.biza.cdr.babelfish.model.common;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description = "Currency Amount")
@Valid
public interface CommonCurrencyAmount {

  @BabelFishModelProperty(
      description = "The current balance of the account at this time. Should align to the current balance available via other channels such as ATM balance enquiry or Internet Banking",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("amount")
  public void setAmount(@NotNull BigDecimal amount);

  default public CommonCurrencyAmount amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @BabelFishModelProperty(description = "Currency Amount Currency Code", required = false,
      dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("currency")
  public Currency getCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("currency")
  public void setCurrency(Currency currency);

  default public CommonCurrencyAmount currency(Currency currency) {
    setCurrency(currency);
    return this;
  }

}

