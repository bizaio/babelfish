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
package io.biza.babelfish.cdr.models.payloads.banking.account.balance;

import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
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
@Schema(description = "An Australian Bank Account Purse Balance Representation",
    name = "BankingBalancePurseV1")
public class BankingBalancePurseV1 {
  @Schema(description = "The balance available for this additional currency purse", required = true, type = "string")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @NotNull
  @JsonProperty("amount")
  BigDecimal amount;

  @Schema(description = "The currency for the purse", type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("currency")
  Currency currency;
}
