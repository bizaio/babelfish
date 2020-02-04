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
package io.biza.babelfish.cdr.models.payloads.banking.account.directdebit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a Direct Debit Authorisation", name = "BankingDirectDebit")
public class BankingDirectDebitV1 {
  @Schema(description = "A unique ID of the account adhering to the standards for ID permanence.",
      required = true)
  @NotNull
  @JsonProperty("accountId")
  String accountId;
  
  @Schema(required = true)
  @NotNull
  @JsonProperty("authorisedEntity")
  BankingAuthorisedEntityV1 authorisedEntity;
  
  @Schema(description = "The date and time of the last debit executed under this authorisation",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastDebitDateTime")
  OffsetDateTime lastDebitDateTime;

  @Schema(description = "The amount of the last debit executed under this authorisation")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("lastDebitAmount")
  BigDecimal lastDebitAmount;


  @AssertTrue(message = "If lastDebitDateTime set then lastDebitAmount should be PRESENT")
  private boolean isLastDebitAmountPresent() {
    if (lastDebitDateTime() == null) {
      return true;
    } else {
      return lastDebitAmount() != null;
    }
  }

  @AssertTrue(message = "If lastDebitAmount set then lastDebitDateTime should be PRESENT")
  private boolean isLastDebitDateTimePresent() {
    if (lastDebitAmount() == null) {
      return true;
    } else {
      return lastDebitDateTime() != null;
    }
  }
}
