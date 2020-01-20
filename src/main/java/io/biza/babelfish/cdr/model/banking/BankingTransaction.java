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
import java.time.LocalDateTime;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.AmountStringToBigDecimalConverter;
import io.biza.babelfish.cdr.converters.BigDecimalToAmountStringConverter;
import io.biza.babelfish.cdr.converters.CurrencyToStringConverter;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.StringToCurrencyConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingTransactionStatus;
import io.biza.babelfish.cdr.v1.enumerations.BankingTransactionType;
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

@Schema(description = "Banking Transaction Brief Definition")
public abstract class BankingTransaction<T> {
  @Schema(description = "ID of the account for which transactions are provided", required = true)
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

  @Schema(
      description = "A unique ID of the transaction adhering to the standards for ID permanence. ")
  @NonNull
  @NotNull
  String transactionId;

  public String transactionId() {
    return getTransactionId();
  }

  @SuppressWarnings("unchecked")
  public T transactionId(String transactionId) {
    setTransactionId(transactionId);
    return (T) this;
  }

  @Schema(
      description = "True if extended information is available using the transaction detail end point. False if extended data is not available",
      required = true)
  @NonNull
  @NotNull
  Boolean isDetailAvailable;

  public Boolean isDetailAvailable() {
    return getIsDetailAvailable();
  }

  @SuppressWarnings("unchecked")
  public T isDetailAvailable(Boolean isDetailAvailable) {
    setIsDetailAvailable(isDetailAvailable);
    return (T) this;
  }

  @Schema(description = "The type of the transaction", required = true)
  @NonNull
  @NotNull
  BankingTransactionType type;

  public BankingTransactionType type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(BankingTransactionType type) {
    setType(type);
    return (T) this;
  }

  @Schema(
      description = "Status of the transaction whether pending or posted. Note that there is currently no provision in the standards to guarantee the ability to correlate a pending transaction with an associated posted transaction",
      required = true)
  @NonNull
  @NotNull
  BankingTransactionStatus status;

  public BankingTransactionStatus status() {
    return getStatus();
  }

  @SuppressWarnings("unchecked")
  public T status(BankingTransactionStatus status) {
    setStatus(status);
    return (T) this;
  }

  @Schema(description = "The transaction description as applied by the financial institution",
      required = true)
  @NonNull
  @NotNull
  String description;

  public String description() {
    return getDescription();
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    setDescription(description);
    return (T) this;
  }

  @Schema(
      description = "The time the transaction was posted. This field is Mandatory if the transaction has status POSTED.  This is the time that appears on a standard statement")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime postingDateTime;

  public LocalDateTime postingDateTime() {
    return getPostingDateTime();
  }

  @SuppressWarnings("unchecked")
  public T postingDateTime(LocalDateTime postingDateTime) {
    setPostingDateTime(postingDateTime);
    return (T) this;
  }

  @Schema(
      description = "Date and time at which assets become available to the account owner in case of a credit entry, or cease to be available to the account owner in case of a debit transaction entry")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime valueDateTime;

  public LocalDateTime valueDateTime() {
    return getValueDateTime();
  }

  @SuppressWarnings("unchecked")
  public T valueDateTime(LocalDateTime valueDateTime) {
    setValueDateTime(valueDateTime);
    return (T) this;
  }

  @Schema(
      description = "The time the transaction was executed by the originating customer, if available")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private LocalDateTime executionDateTime;

  public LocalDateTime executionDateTime() {
    return getExecutionDateTime();
  }

  @SuppressWarnings("unchecked")
  public T executionDateTime(LocalDateTime executionDateTime) {
    setExecutionDateTime(executionDateTime);
    return (T) this;
  }

  @Schema(
      description = "The value of the transaction. Negative values mean money was outgoing from the account",
      required = true, type = "string")
  @NonNull
  @NotNull
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  private BigDecimal amount;

  public BigDecimal amount() {
    return getAmount();
  }

  @SuppressWarnings("unchecked")
  public T amount(BigDecimal amount) {
    setAmount(amount);
    return (T) this;
  }

  @Schema(description = "The currency for the transaction amount. AUD assumed if not present",
      type = "string")
  @JsonSerialize(converter = CurrencyToStringConverter.class)
  @JsonDeserialize(converter = StringToCurrencyConverter.class)
  Currency currency;

  public Currency currency() {
    return getCurrency();
  }

  @SuppressWarnings("unchecked")
  public T currency(Currency currency) {
    setCurrency(currency);
    return (T) this;
  }

  @Schema(
      description = "The reference for the transaction provided by the originating institution. Empty string if no data provided",
      required = true)
  @NonNull
  @NotNull
  String reference;

  public String reference() {
    return getReference();
  }

  @SuppressWarnings("unchecked")
  public T reference(String reference) {
    setReference(reference);
    return (T) this;
  }

  @Schema(description = "Name of the merchant for an outgoing payment to a merchant")
  String merchantName;

  public String merchantName() {
    return getMerchantName();
  }

  @SuppressWarnings("unchecked")
  public T merchantName(String merchantName) {
    setMerchantName(merchantName);
    return (T) this;
  }

  @Schema(description = "The merchant category code (or MCC) for an outgoing payment to a merchant")
  String merchantCategoryCode;

  public String merchantCategoryCode() {
    return getMerchantCategoryCode();
  }

  @SuppressWarnings("unchecked")
  public T merchantCategoryCode(String merchantCategoryCode) {
    setMerchantCategoryCode(merchantCategoryCode);
    return (T) this;
  }

  @Schema(description = "BPAY Biller Code for the transaction (if available)")
  String billerCode;

  public String billerCode() {
    return getBillerCode();
  }

  @SuppressWarnings("unchecked")
  public T billerCode(String billerCode) {
    setBillerCode(billerCode);
    return (T) this;
  }

  @Schema(description = "Name of the BPAY biller for the transaction (if available)")
  String billerName;

  public String billerName() {
    return getBillerName();
  }

  @SuppressWarnings("unchecked")
  public T billerName(String billerName) {
    setBillerName(billerName);
    return (T) this;
  }

  @Schema(description = "BPAY CRN for the transaction (if available)")
  String crn;

  public String crn() {
    return getCrn();
  }

  @SuppressWarnings("unchecked")
  public T crn(String crn) {
    setCrn(crn);
    return (T) this;
  }

  @Schema(description = "6 Digit APCA number for the initiating institution")
  String apcaNumber;

  public String apcaNumber() {
    return getApcaNumber();
  }

  @SuppressWarnings("unchecked")
  public T apcaNumber(String apcaNumber) {
    setApcaNumber(apcaNumber);
    return (T) this;
  }

}
