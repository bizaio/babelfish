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
import java.time.LocalDate;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingTermDepositMaturityInstructions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Term Deposit Account Description")
public abstract class BankingTermDepositAccount<T> {
  @BabelFishModelProperty(description = "The lodgement date of the original deposit",
      required = true, dataType = "java.lang.String")
  @NonNull
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate lodgementDate;

  public LocalDate lodgementDate() {
    return getLodgementDate();
  }

  @SuppressWarnings("unchecked")
  public T lodgementDate(LocalDate lodgementDate) {
    setLodgementDate(lodgementDate);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Maturity date for the term deposit", required = true)
  @NonNull
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate maturityDate;

  public LocalDate maturityDate() {
    return getMaturityDate();
  }

  @SuppressWarnings("unchecked")
  public T maturityDate(LocalDate maturityDate) {
    setMaturityDate(maturityDate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Amount to be paid upon maturity. If absent it implies the amount to paid is variable and cannot currently be calculated",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal maturityAmount;

  public BigDecimal maturityAmount() {
    return getMaturityAmount();
  }

  @SuppressWarnings("unchecked")
  public T maturityAmount(BigDecimal maturityAmount) {
    setMaturityAmount(maturityAmount);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Maturity Amount Currency", dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency maturityCurrency = Currency.getInstance("AUD");

  public Currency maturityCurrency() {
    return getMaturityCurrency();
  }

  @SuppressWarnings("unchecked")
  public T maturityCurrency(Currency maturityCurrency) {
    setMaturityCurrency(maturityCurrency);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Current instructions on action to be taken at maturity",
      required = true)
  @NonNull
  @NotNull
  BankingTermDepositMaturityInstructions maturityInstructions;

  public BankingTermDepositMaturityInstructions maturityInstructions() {
    return getMaturityInstructions();
  }

  @SuppressWarnings("unchecked")
  public T maturityInstructions(BankingTermDepositMaturityInstructions maturityInstructions) {
    setMaturityInstructions(maturityInstructions);
    return (T) this;
  }
}
