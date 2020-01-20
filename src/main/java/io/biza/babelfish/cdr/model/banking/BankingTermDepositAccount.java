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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingTermDepositMaturityInstructions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Term Deposit Account Description")
public abstract class BankingTermDepositAccount<T> {
  @Schema(description = "The lodgement date of the original deposit", required = true,
      type = "string")
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

  @Schema(description = "Maturity date for the term deposit", required = true)
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

  @Schema(
      description = "Amount to be paid upon maturity. If absent it implies the amount to paid is variable and cannot currently be calculated",
      type = "string")
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

  @Schema(description = "Maturity Amount Currency", type = "string")
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

  @Schema(description = "Current instructions on action to be taken at maturity", required = true)
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
