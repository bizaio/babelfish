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
package io.biza.babelfish.cdr.models.payloads.banking.account.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.enumerations.BankingTransactionStatus;
import io.biza.babelfish.cdr.enumerations.BankingTransactionType;
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
@Schema(description = "Banking Transaction Brief Definition", name = "BankingTransaction")
public class BankingTransactionV1 extends io.biza.babelfish.cdr.abstracts.models.banking.BankingTransactionV1 {
  @Schema(description = "ID of the account for which transactions are provided", required = true)
  @NotNull
  @JsonProperty("accountId")
  String accountId;

  @Schema(
      description = "A unique ID of the transaction adhering to the standards for ID permanence. ")
  @NotNull
  @JsonProperty("transactionId")
  String transactionId;

  @Schema(
      description = "True if extended information is available using the transaction detail end point. False if extended data is not available",
      required = true)
  @NotNull
  @JsonProperty("isDetailAvailable")
  Boolean isDetailAvailable;

  @Schema(description = "The type of the transaction", required = true)
  @NotNull
  @JsonProperty("type")
  BankingTransactionType type;

  @Schema(
      description = "Status of the transaction whether pending or posted. Note that there is currently no provision in the standards to guarantee the ability to correlate a pending transaction with an associated posted transaction",
      required = true)
  @NotNull
  @JsonProperty("status")
  BankingTransactionStatus status;

  @Schema(description = "The transaction description as applied by the financial institution",
      required = true)
  @NotNull
  @JsonProperty("description")
  String description;

  @Schema(
      description = "The time the transaction was posted. This field is Mandatory if the transaction has status POSTED.  This is the time that appears on a standard statement",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("postingDateTime")
  OffsetDateTime postingDateTime;

  @Schema(
      description = "Date and time at which assets become available to the account owner in case of a credit entry, or cease to be available to the account owner in case of a debit transaction entry",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("valueDateTime")
  OffsetDateTime valueDateTime;

  @Schema(
      description = "The time the transaction was executed by the originating customer, if available",
      format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("executionDateTime")
  OffsetDateTime executionDateTime;

  @Schema(
      description = "The value of the transaction. Negative values mean money was outgoing from the account",
      required = true, type = "string")
  @NotNull
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonProperty("amount")
  BigDecimal amount;

  @Schema(description = "The currency for the transaction amount. AUD assumed if not present",
      type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  @JsonProperty("currency")
  Currency currency;

  @Schema(
      description = "The reference for the transaction provided by the originating institution. Empty string if no data provided",
      required = true)
  @NotNull
  @JsonProperty("reference")
  String reference;

  @Schema(description = "Name of the merchant for an outgoing payment to a merchant")
  @JsonProperty("merchantName")
  String merchantName;

  @Schema(description = "The merchant category code (or MCC) for an outgoing payment to a merchant")
  @JsonProperty("merchantCategoryCode")
  String merchantCategoryCode;

  @Schema(description = "BPAY Biller Code for the transaction (if available)")
  @JsonProperty("billerCode")
  String billerCode;

  @Schema(description = "Name of the BPAY biller for the transaction (if available)")
  @JsonProperty("billerName")
  String billerName;

  @Schema(description = "BPAY CRN for the transaction (if available)")
  @JsonProperty("crn")
  String crn;

  @Schema(description = "6 Digit APCA number for the initiating institution")
  @JsonProperty("apcaNumber")
  String apcaNumber;

}
