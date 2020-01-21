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
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
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

@Schema(description = "Representation of a Direct Debit Authorisation")
public abstract class BankingDirectDebit<T> {
  @Schema(description = "A unique ID of the account adhering to the standards for ID permanence.",
      required = true)
  @NonNull
  @NotNull
  @JsonProperty("accountId")
  String accountId;

  public String accountId() {
    return getAccountId();
  }

  @SuppressWarnings("unchecked")
  public T accountId(String accountId) {
    setAccountId(accountId);
    return (T) this;
  }

  @Schema(required = true)
  @NonNull
  @NotNull
  @JsonProperty("authorisedEntity")
  BankingAuthorisedEntity<?> authorisedEntity;

  public BankingAuthorisedEntity<?> authorisedEntity() {
    return getAuthorisedEntity();
  }

  @SuppressWarnings("unchecked")
  public T authorisedEntity(BankingAuthorisedEntity<?> authorisedEntity) {
    setAuthorisedEntity(authorisedEntity);
    return (T) this;
  }

  @Schema(description = "The date and time of the last debit executed under this authorisation",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastDebitDateTime")
  OffsetDateTime lastDebitDateTime;

  public OffsetDateTime lastDebitDateTime() {
    return getLastDebitDateTime();
  }

  @SuppressWarnings("unchecked")
  public T lastDebitDateTime(OffsetDateTime lastDebitDateTime) {
    setLastDebitDateTime(lastDebitDateTime);
    return (T) this;
  }

  @Schema(description = "The amount of the last debit executed under this authorisation")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("lastDebitAmount")
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
