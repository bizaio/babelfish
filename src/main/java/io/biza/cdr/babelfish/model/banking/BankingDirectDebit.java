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
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Representation of a Direct Debit Authorisation")
public interface BankingDirectDebit {

  @BabelFishModelProperty(
      description = "A unique ID of the account adhering to the standards for ID permanence.",
      required = true)
  @JsonGetter("accountId")
  public String getAccountId();

  @JsonSetter("accountId")
  public void setAccountId(@NotNull String accountId);

  public default BankingDirectDebit accountId(@NotNull String accountId) {
    setAccountId(accountId);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("authorisedEntity")
  public BankingAuthorisedEntity getAuthorisedEntity();

  @JsonSetter("authorisedEntity")
  public void setAuthorisedEntity(@NotNull BankingAuthorisedEntity authorisedEntity);

  public default BankingDirectDebit authorisedEntity(
      @NotNull BankingAuthorisedEntity authorisedEntity) {
    setAuthorisedEntity(authorisedEntity);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time of the last debit executed under this authorisation",
      dataType = "java.lang.String")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("lastDebitDateTime")
  public LocalDateTime getLastDebitDateTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("lastDebitDateTime")
  public void setLastDebitDateTime(LocalDateTime lastDebitDateTime);

  public default BankingDirectDebit lastDebitDateTime(@NotNull LocalDateTime lastDebitDateTime) {
    setLastDebitDateTime(lastDebitDateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "The amount of the last debit executed under this authorisation")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("lastDebitAmount")
  public BigDecimal getLastDebitAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("lastDebitAmount")
  public void setLastDebitAmount(BigDecimal lastDebitAmount);

  public default BankingDirectDebit lastDebitAmount(BigDecimal lastDebitAmount) {
    setLastDebitAmount(lastDebitAmount);
    return this;
  }
}
