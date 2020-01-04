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
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "Banking Transaction Brief Definition")
public abstract class BankingTransaction {
  @BabelFishModelProperty(description = "ID of the account for which transactions are provided",
      required = true)
  @NonNull
  @NotNull
  String accountId;

  @BabelFishModelProperty(
      description = "A unique ID of the transaction adhering to the standards for ID permanence. ")
  @NonNull
  @NotNull
  String transactionId;

  @BabelFishModelProperty(
      description = "True if extended information is available using the transaction detail end point. False if extended data is not available",
      required = true)
  @NonNull
  @NotNull
  Boolean isDetailAvailable;

  @BabelFishModelProperty(description = "The type of the transaction", required = true)
  @NonNull
  @NotNull
  BankingTransactionType type;

  @BabelFishModelProperty(
      description = "Status of the transaction whether pending or posted. Note that there is currently no provision in the standards to guarantee the ability to correlate a pending transaction with an associated posted transaction",
      required = true)
  @NonNull
  @NotNull
  BankingTransactionStatus status;

  @BabelFishModelProperty(
      description = "The transaction description as applied by the financial institution",
      required = true)
  @NonNull
  @NotNull
  String description;

  @BabelFishModelProperty(
      description = "The time the transaction was posted. This field is Mandatory if the transaction has status POSTED.  This is the time that appears on a standard statement")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime postingDateTime;

  @BabelFishModelProperty(
      description = "Date and time at which assets become available to the account owner in case of a credit entry, or cease to be available to the account owner in case of a debit transaction entry")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime valueDateTime;

  @BabelFishModelProperty(
      description = "The time the transaction was executed by the originating customer, if available")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime executionDateTime;

  @BabelFishModelProperty(
      description = "The value of the transaction. Negative values mean money was outgoing from the account",
      required = true, dataType = "java.lang.String")
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal amount;

  @BabelFishModelProperty(
      description = "The currency for the transaction amount. AUD assumed if not present",
      dataType = "java.lang.String")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency currency;

  @BabelFishModelProperty(
      description = "The reference for the transaction provided by the originating institution. Empty string if no data provided",
      required = true)
  @NonNull
  @NotNull
  String reference;

  @BabelFishModelProperty(
      description = "Name of the merchant for an outgoing payment to a merchant")
  String merchantName;

  @BabelFishModelProperty(
      description = "The merchant category code (or MCC) for an outgoing payment to a merchant")
  String merchantCategoryCode;

  @BabelFishModelProperty(description = "BPAY Biller Code for the transaction (if available)")
  String billerCode;

  @BabelFishModelProperty(
      description = "Name of the BPAY biller for the transaction (if available)")
  String billerName;

  @BabelFishModelProperty(description = "BPAY CRN for the transaction (if available)")
  String crn;

  @BabelFishModelProperty(description = "6 Digit APCA number for the initiating institution")
  String apcaNumber;

  @AssertTrue(message = "Posting Date and Time must be set when status is POSTED")
  private boolean isPostedDateDefined() {
    return BankingTransactionStatus.POSTED.equals(status) ? (postingDateTime != null ? true : false)
        : true;
  }
}
