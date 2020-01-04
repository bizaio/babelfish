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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Term Deposit Account Description")
public interface BankingTermDepositAccount {

  @BabelFishModelProperty(description = "The lodgement date of the original deposit",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("lodgementDate")
  public LocalDate getLodgementDate();

  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonSetter("lodgementDate")
  public void setLodgementDate(@NotNull LocalDate lodgementDate);

  public default BankingTermDepositAccount lodgementDate(@NotNull LocalDate lodgementDate) {
    setLodgementDate(lodgementDate);
    return this;
  }

  @BabelFishModelProperty(description = "Maturity date for the term deposit", required = true)
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("maturityDate")
  public LocalDate getMaturityDate();

  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonSetter("maturityDate")
  public void setMaturityDate(@NotNull LocalDate maturityDate);

  public default BankingTermDepositAccount maturityDate(@NotNull LocalDate maturityDate) {
    setMaturityDate(maturityDate);
    return this;
  }

  @BabelFishModelProperty(
      description = "Amount to be paid upon maturity. If absent it implies the amount to paid is variable and cannot currently be calculated",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("maturityAmount")
  public BigDecimal getMaturityAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("maturityAmount")
  public void setMaturityAmount(BigDecimal maturityAmount);

  public default BankingTermDepositAccount maturityAmount(BigDecimal maturityAmount) {
    setMaturityAmount(maturityAmount);
    return this;
  }

  @BabelFishModelProperty(description = "Maturity Amount Currency", dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonGetter("maturityCurrency")
  public Currency getMaturityCurrency();

  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonSetter("maturityCurrency")
  public void setMaturityCurrency(Currency maturityCurrency);

  public default BankingTermDepositAccount maturityCurrency(Currency maturityCurrency) {
    setMaturityCurrency(maturityCurrency);
    return this;
  }

  @BabelFishModelProperty(description = "Current instructions on action to be taken at maturity",
      required = true)
  @JsonGetter("maturityInstructions")
  public BankingTermDepositMaturityInstructions getMaturityInstructions();

  @JsonSetter("maturityInstructions")
  public void setMaturityInstructions(
      @NotNull BankingTermDepositMaturityInstructions maturityInstructions);

  public default BankingTermDepositAccount maturityInstructions(
      @NotNull BankingTermDepositMaturityInstructions maturityInstructions) {
    setMaturityInstructions(maturityInstructions);
    return this;
  }
}
