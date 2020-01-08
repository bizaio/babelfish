/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
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

@BabelFishModel(description = "Representation of a Direct Debit Authorisation")
public abstract class BankingDirectDebit<T> {
  @BabelFishModelProperty(
      description = "A unique ID of the account adhering to the standards for ID permanence.",
      required = true)
  @NonNull
  @NotNull
  String accountId;

  public String accountId() {
    return getAccountId();
  }

  @SuppressWarnings("unchecked")
  public T accountId(String accountId) {
    setAccountId(accountId);
    return (T) this;
  }

  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  BankingAuthorisedEntity<?> authorisedEntity;

  public BankingAuthorisedEntity<?> authorisedEntity() {
    return getAuthorisedEntity();
  }

  @SuppressWarnings("unchecked")
  public T authorisedEntity(BankingAuthorisedEntity<?> authorisedEntity) {
    setAuthorisedEntity(authorisedEntity);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The date and time of the last debit executed under this authorisation",
      dataType = "java.lang.String")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  LocalDateTime lastDebitDateTime;

  public LocalDateTime lastDebitDateTime() {
    return getLastDebitDateTime();
  }

  @SuppressWarnings("unchecked")
  public T lastDebitDateTime(LocalDateTime lastDebitDateTime) {
    setLastDebitDateTime(lastDebitDateTime);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The amount of the last debit executed under this authorisation")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  BigDecimal lastDebitAmount;

  public BigDecimal lastDebitAmount() {
    return getLastDebitAmount();
  }

  @SuppressWarnings("unchecked")
  public T lastDebitAmount(BigDecimal lastDebitAmount) {
    setLastDebitAmount(lastDebitAmount);
    return (T) this;
  }
}
