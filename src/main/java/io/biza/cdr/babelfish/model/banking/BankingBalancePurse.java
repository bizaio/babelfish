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
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
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

@Valid
@BabelFishModel(description = "An Australian Bank Account Purse Balance Representation")
public interface BankingBalancePurse {

  @BabelFishModelProperty(description = "The balance available for this additional currency purse",
      required = true)
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  public void setAmount(@NotNull BigDecimal amount);

  public default BankingBalancePurse amount(@NotNull BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @BabelFishModelProperty(description = "The currency for the purse")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("currency")
  public Currency getCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("currency")
  public void setCurrency(Currency currency);

  public default BankingBalancePurse currency(Currency currency) {
    setCurrency(currency);
    return this;
  }
}
