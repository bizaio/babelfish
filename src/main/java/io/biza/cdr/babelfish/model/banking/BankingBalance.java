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
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "A Representation of a Banking Account Balance")
public abstract class BankingBalance {
  @BabelFishModelProperty(
      description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @NotNull
  @NonNull
  String accountId;

  @BabelFishModelProperty(
      description = "The balance of the account at this time. Should align to the balance available via other channels such as Internet Banking. Assumed to be negative if the customer has money owing",
      required = true, dataType = "java.lang.String")
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  BigDecimal currentBalance;

  @BabelFishModelProperty(
      description = "Balance representing the amount of funds available for transfer. Assumed to be zero or positive",
      required = true, dataType = "java.lang.String")
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal availableBalance;

  @BabelFishModelProperty(
      description = "Object representing the maximum amount of credit that is available for this account. Assumed to be zero if absent",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal creditLimit;

  @BabelFishModelProperty(
      description = "Object representing the available limit amortised according to payment schedule. Assumed to be zero if absent",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal amortisedLimit = BigDecimal.ZERO;

  @BabelFishModelProperty(
      description = "The currency for the balance amounts. If absent assumed to be AUD",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  Currency currency;

  @BabelFishModelProperty(
      description = "Optional array of balances for the account in other currencies. Included to support accounts that support multi-currency purses such as Travel Cards")
  List<BankingBalancePurse> purses;
}
